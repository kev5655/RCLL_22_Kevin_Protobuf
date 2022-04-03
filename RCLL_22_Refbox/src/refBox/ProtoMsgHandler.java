package refBox;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import comm.TPC;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import main.Cfg;
import main.LogFact;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.robocup_logistics.llsf_comm.ProtobufMessageHandler;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.GameStateProtos.GameState;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.MachineInfo;
import org.robocup_logistics.llsf_msgs.NavigationChallengeProtos.NavigationRoutes;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos.OrderInfo;
import org.robocup_logistics.llsf_msgs.RingInfoProtos.RingInfo;
import org.robocup_logistics.llsf_msgs.RobotInfoProtos.RobotInfo;

/**
 * Handelt die, von der Refbox, ankommenden Nachrichten. Für jede Nachricht steht eine eigene
 * Methode zur Verfügung. Zuerst wird entschieden, um welche Nachricht es sich handelt, danach wird
 * die entsprechende Methode aufgerufen.
 */
public class ProtoMsgHandler implements ProtobufMessageHandler
{
    public static GameState game; //Game Status aus der GameState Nachricht.

    private String lastGamePhase; //Vorherige Spielphase.
    private String lastGameState; //Vorheriger Spielstatus.
    private byte[] lastOrderInfoByteArray;
    private byte[] lastMachineInfoByteArray;
    private byte[] lastRingInfoByteArray;
    private byte[] lastGameInfoByteArray;
    private boolean cryptoInitFlag = false; //Flag für das Öffnen des privaten Kanals, sobald die Teamfarbe von der Refbox gesendet wird.
    private ComRefBox comRefBox;
    private byte[] lastRobotInfoByteArray;
    private byte[] lastNavChallengeByteArray;

    public static org.apache.log4j.Logger rBLog;//Logger

    static
    {
        rBLog = LogFact.get().mainLog;
    }

    public ProtoMsgHandler(ComRefBox comRefBox)
    {
        this.comRefBox = comRefBox;        
    }

    /**
     * Handeln der ankommenden Nachrichten von Refbox
     *
     * @param in_msg
     * @param msg
     */
    @Override
    public void handle_message(ByteBuffer in_msg, GeneratedMessage msg)
    {
        //LogFact.getMain().info(msg.getDescriptorForType().getFullName()); //Namen der ankommenden Nachricht->Logfile.

        byte[] array = new byte[in_msg.capacity()];
        in_msg.rewind();
        in_msg.get(array);

        if (msg instanceof BeaconSignal)                        //Keep-Alive (publicPeer)
        {
            Msg.recvBeacon(array);
        } else if (msg instanceof OrderInfo)                    //Bestellungen in Productionphase (publicPeer)
        {
            if (!Arrays.equals(array, lastOrderInfoByteArray))
            {
                try
                {
                    comRefBox.getLocalBroker().send(TPC.RB_ORDERS, array);
                    lastOrderInfoByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    ComRefBox.rBLog.error("Error while send the Orders to Broker: " + ex.getMessage());
                }
            }
        } else if (msg instanceof RingInfo)                    //Bestellungen in Productionphase (privatePeer)
        {
            if (!Arrays.equals(array, lastRingInfoByteArray))
            {
                try
                {
                    comRefBox.getLocalBroker().send(TPC.RB_RING_INFO, array);
                    lastRingInfoByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    ComRefBox.rBLog.error("Error while send the RingInfo to Broker: " + ex.getMessage());
                }
            }
        } else if (msg instanceof GameState)                    //Spielphase, Spielstatus, Spielzeit&Punkte.
        {
            if (!Arrays.equals(array, lastGameInfoByteArray))
            {
                try
                {
                    comRefBox.getLocalBroker().send(TPC.GAME, array);
                    lastGameInfoByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    ComRefBox.rBLog.error("Error while send the GameInfo to Broker: " + ex.getMessage());
                }
            }
            gameState(array);
        } else if (msg instanceof MachineInfo)                  //Status und Zonen von MPS (Productionphase)
        {
            if (!Arrays.equals(array, lastMachineInfoByteArray))
            {
                //Msg.recvMachines(array);  //Auswertung wird momentan nicht gebraucht
                try
                {
                    comRefBox.getLocalBroker().send(TPC.RB_MACHINES, array);
//                    if (StartUp.cfg.roboNr > 0)
//                    {
//                        comRefBox.getMasterBroker().send(TPC.MACHINES, array);
//                    }
                    lastMachineInfoByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    rBLog.error("Error while send the Machines to Broker: " + ex.getMessage());
                }
            }
        } else if (msg instanceof RobotInfo)
        {
            if (!Arrays.equals(array, lastRobotInfoByteArray))
            {
                try
                {
                    comRefBox.getLocalBroker().send(TPC.ROBO_INFO, array);   //Local Broker from Master
                    lastRobotInfoByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    rBLog.error("Error while send the Robots to Broker: " + ex.getMessage());
                }
            }
        }
        else if (msg instanceof NavigationRoutes)
        {
            if (!Arrays.equals(array, lastNavChallengeByteArray))
            {
                try
                {
                    comRefBox.getLocalBroker().send(TPC.NAV_CHALLENGE_ZONES, array);   //Local Broker from Master
                    lastNavChallengeByteArray = Arrays.copyOf(array, array.length);
                } catch (MqttException ex)
                {
                    rBLog.error("Error while send the Robots to Broker: " + ex.getMessage());
                }
            }
        }
    }

    /**
     * Wenn die Verbindung abbricht wird eine Infomeldung ins Log-File geschrieben.
     *
     * @param e
     */
    @Override
    public void connection_lost(IOException e)
    {
        rBLog.info("Handler: Connection lost. Message: " + e.getMessage());
    }

    /**
     * Gibt die aktuelle Spielphase und den aktuellen Spielstatus als String aus. Bei einer
     * Änderung, wird die Phase und der Zustand auf den Broker gesendet. Bei der ersten Nachricht
     * wird die Teamfarbe gesetzt und der verschlüsselte private Kanal wird aufgebaut. Der Kanal
     * wird einem Handler übergeben. Der Handler entscheidet dann, um welche Nachricht es sich
     * handelt.
     *
     * @param array
     */
    public void gameState(byte[] array)
    {
        try
        {
            game = GameState.parseFrom(array);
            //LogFact.getMain().debug(game.toString());
            String gamePhase = game.getPhase().name(); //Die aktuelle Spielphase wird aus der Nachricht ausgelesen.
            String gameState = game.getState().name(); //Der aktuelle Spielstatus wird aus der Nachricht ausgelesen.
            try
            {
                comRefBox.getLocalBroker().send(TPC.PHASE, gamePhase);

                if (lastGamePhase != null && !lastGamePhase.equalsIgnoreCase(gamePhase))
                {
                    rBLog.info("Current GamePhase: [" + gamePhase + "]");
                }
                lastGamePhase = gamePhase;
                comRefBox.getLocalBroker().send(TPC.STATE, gameState);

                if (lastGameState != null && !lastGameState.equalsIgnoreCase(gameState))
                {
                    rBLog.info("Current GameState: [" + gameState + "]");
                }
                lastGameState = gameState;
            } catch (MqttException ex)
            {
                rBLog.error(ex.getMessage());
            }
            // Es wird darauf gewartet, bis bei der Refbox die Farbe für das Team gesetzt wurde.
            if (!cryptoInitFlag) // Mit diesem Flag wird das Setzen der Teamfarbe nur einmal durchgeführt.
            {
                cryptoInitFlag = true;
                if (Cfg.roboNr > 0) //???
                {
                    comRefBox.setPeerPrivate(Cfg.team, true); //PeerPrivate für den Robi (mit Beacon)
                } else
                {
                    comRefBox.setPeerPrivate(Cfg.team, false); //PeerPrivate für den Master (ohne Beacon)
                }
            }
        } catch (InvalidProtocolBufferException e)
        {
            rBLog.fatal("Error while parsing game state or not send to the Broker. Message: " + e.getMessage());
        }
    }

    /**
     * Wenn die Verbindungszeit überschritten wird, wird eine Infomeldung ins Log-File geschrieben.
     */
    @Override
    public void timeout()
    {
        rBLog.info("Handler: Timeout");
    }
}
