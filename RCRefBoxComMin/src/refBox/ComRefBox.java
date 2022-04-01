package refBox;

import comm.MqttCom;
import comm.TPC;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import main.*;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.robocup_logistics.llsf_comm.ProtobufBroadcastPeer;
import org.robocup_logistics.llsf_comm.ProtobufMessage;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.ExplorationInfoProtos.ExplorationInfo;
import org.robocup_logistics.llsf_msgs.GameStateProtos.GameState;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.MachineInfo;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareMachine;
import org.robocup_logistics.llsf_msgs.MachineReportProtos.MachineReport;
import org.robocup_logistics.llsf_msgs.NavigationChallengeProtos.NavigationRoutes;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos.OrderInfo;
import org.robocup_logistics.llsf_msgs.RingInfoProtos.RingInfo;
import org.robocup_logistics.llsf_msgs.RobotInfoProtos.RobotInfo;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;
import org.robocup_logistics.llsf_msgs.VersionProtos.VersionInfo;

/**
 * Die Klasse ComRefBox handelt die Kommunikation zur Schiedsrichterbox (Refbox). Die Nachrichten
 * von der Refbox werden empfangen und verarbeitet. Das BeaconSignal, die entdeckten Maschinen (MPS)
 * in der Explorationsphase und die erledigten Bestellungen werden an die Refbox geschickt. Der
 * Spielzustand, die Explorationsinfo, die Maschineninfo und die Bestellungen werden an den Broker
 * gesendet.
 *
 * @author Florian Gehrig, Alain Rohr
 * @version 2.0
 */
public class ComRefBox implements MqttCallback, Runnable
{
    // Variablen für die Kommunikation mit dem Broker.
    private MqttCom brokerMaster; //Broker der lokal auf dem Robotino läuft.
    private MqttCom broker; //Broker der lokal auf dem Robotino läuft.
    private BlockingDeque transmitQueue = new LinkedBlockingDeque();

    // Verbindungen zur Refbox
    private static ProtobufBroadcastPeer peerPublic; //Zum Öffnen des öffentlichen Kanals.
    private static ProtobufBroadcastPeer peerPrivate; //Zum Öffnen des privaten verschlüsselten Kanals.

    private final static int PUBLIC_SENDPORT = 4445; //Port zum Senden auf dem öffentlichen Kanal.
    private final static int PUBLIC_RECVPORT = 4444; //Port zum Empfangen auf dem öffentlichen Kanal.

    private final static int CYAN_SENDPORT = 4446; //Port zum Senden auf dem privaten Kanal des blauen Teams.
    private final static int CYAN_RECVPORT = 4441; //Port zum Empfangen auf dem privaten Kanal des blauen Teams.

    private final static int MAGENTA_SENDPORT = 4447; //Port zum Senden auf dem privaten Kanal des roten Teams.
    private final static int MAGENTA_RECVPORT = 4442; //Port zum Empfangen auf dem privaten Kanal des roten Teams.

    private static final boolean LOCAL = false; //Wenn nicht alles auf localhost (false) für Senden&Empfangen gleicher Port.
    public static org.apache.log4j.Logger rBLog;//Logger

    static
    {
        rBLog = LogFact.get().mainLog;
    }

    /**
     * Im Konstruktor wird der Logger gesetzt, die Verbindung zum Broker hergestellt und die
     * Verbindung zum öffentlichen Kanal wird aufgebaut. Es wird zudem ein Scheduler gestartet, mit
     * dem die run-Methode alle 2 Sekunden aufgerufen wird.
     */
    public ComRefBox()
    {
        comBroker();                            // Verbindung zum Broker herstellen
        if (Cfg.roboNr > 0)
        {
            setPeerPublic(false);
        } else
        {
            setPeerPublic(true);                        // Den öffentlichen unverschlüsselten Kanal aufbauen
        }

        Thread thread = new Thread(this);
        thread.start();
        //StartUp.exSrv.scheduleAtFixedRate(this, 0, 1000, TimeUnit.MILLISECONDS); // Scheduler für run-Methode
    }

    /**
     * Stellt die Verbindung zum Broker her. Über das setCallback() wird ein Topic als Feed
     * abonniert. Bei jeder Änderung auf dem entsprechenden Topic, wird die Methode messageArrived()
     * aufgerufen.
     *
     * @see #messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    private void comBroker()
    {
        try
        {
            if (Cfg.roboNr > 0)
            {
                brokerMaster = new MqttCom("tcp://" + Cfg.ipMaster + ":1883", "ComRefBox" + Cfg.roboNr);
            }
            broker = new MqttCom("tcp://" + Cfg.mqttBrokerIp + ":1883", "ComRefBox" + Cfg.roboNr);
            broker.setCallback(this);
            broker.subscribe(TPC.REFMACHINE); //Es wird auf den Topic für die entdeckten Maschinen (MPS) gehört.
            broker.subscribe(TPC.REFSETUP);   //Topic für die Machine-Setup-Meldungen.
        } catch (MqttException ex)
        {
            rBLog.fatal("+++ComRefBox+++ Error while set the Callback to Broker. Message: " + ex);
        }
    }

    public MqttCom getLocalBroker()
    {
        return broker;
    }

    public MqttCom getMasterBroker()
    {
        return brokerMaster;
    }

    /**
     * Baut den öffentlichen Kanal zur Refbox auf. Der Kanal wird einem Handler übergeben. Der
     * Handler entscheidet dann, um welche Nachricht es sich handelt.
     */
    private void setPeerPublic(boolean isMasterFlag)
    {
        peerPublic = new ProtobufBroadcastPeer(Cfg.refBoxIp, LOCAL ? PUBLIC_SENDPORT : PUBLIC_RECVPORT, PUBLIC_RECVPORT);
        try
        {
            if (isMasterFlag)
            {
                peerPublic.<OrderInfo>add_message(OrderInfo.class);     //wird auf Master empfangen
                //peerPublic.<RingInfo>add_message(RingInfo.class);       //wird auf Master empfangen
            }
            peerPublic.<GameState>add_message(GameState.class);
            peerPublic.<ExplorationInfo>add_message(ExplorationInfo.class);  ////wird auf Master empfangen
            peerPublic.<VersionInfo>add_message(VersionInfo.class);
            peerPublic.<RobotInfo>add_message(RobotInfo.class);
            ProtoMsgHandler handler = new ProtoMsgHandler(this);
            peerPublic.register_handler(handler);
            peerPublic.start();
        } catch (IOException e)
        {
            rBLog.fatal("Error while starting public peer. Message: " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     *
     * @param team
     * @param enableBeacon Das Beacon wird nur aktiviert, wenn der Vorgang auf einem Robotino statt
     * findet.
     */
    public void setPeerPrivate(Team team, boolean enableBeacon)
    {
        // Sobald die Teamfarbe gesetzt wurde, wird der private Peer mit den entsprechenden Ports aufgebaut.
        switch (team)
        {
            case CYAN:
                peerPrivate = new ProtobufBroadcastPeer(Cfg.refBoxIp, LOCAL ? CYAN_SENDPORT : CYAN_RECVPORT, CYAN_RECVPORT, true, 2, Cfg.encKey);
                break;
            case MAGENTA:
                peerPrivate = new ProtobufBroadcastPeer(Cfg.refBoxIp, LOCAL ? MAGENTA_SENDPORT : MAGENTA_RECVPORT, MAGENTA_RECVPORT, true, 2, Cfg.encKey);
                break;
        }

        if (peerPrivate != null)
        {
            if (enableBeacon)
            {
                peerPrivate.<BeaconSignal>add_message(BeaconSignal.class);
            }
            //Die Nachrichten werden registriert.
            peerPrivate.<RingInfo>add_message(RingInfo.class);         //wird auf Master empfangen
            peerPrivate.<MachineInfo>add_message(MachineInfo.class);
            peerPrivate.<NavigationRoutes>add_message(NavigationRoutes.class);
            // peerPrivate.<OrderInfo>add_message(OrderInfo.class);    //kommt eben nicht auf private (Doku-Fehler)

            // Ein neuer Handler wird erstellt und die Nachrichten übergeben.   
            ProtoMsgHandler handler = new ProtoMsgHandler(this);
            peerPrivate.register_handler(handler);

            try
            {
                peerPrivate.start();
            } catch (IOException e)
            {
                rBLog.fatal("Exception while start private peer. Message: " + e.getMessage());
            }

            if (enableBeacon)
            {
                //Sobald der Peer aufgebaut ist wird der unabhängige BeaconThread gestartet.
                BeaconThread beaconThread = new BeaconThread(peerPrivate, team);
                beaconThread.setName("ComRefBox_Beacon_Thread");
                beaconThread.start();
            }

        }
    }

    /**
     * Erzwungene Methode von org.eclipse.paho.client.mqttv3.MqttCallback. Es wird eine Meldung ins
     * Log-File geschrieben.
     *
     * @param thrwbl
     */
    @Override
    public void connectionLost(Throwable thrwbl)
    {
        System.out.println("MQTT Connection lost... trying to reconnect...");
        broker.reconnect(true); //Reconnect Methode aufrufen
        rBLog.info("Broker ComRefBox: Connection lost");
    }

    /**
     * Ankommende Nachrichten vom Broker werden ausgewertet. Erzwungene Methode von
     * org.eclipse.paho.client.mqttv3.MqttCallback. In der Nachricht wird der Topic und die Message
     * geschickt.
     *
     * @param topic
     * @param message
     * @throws Exception Wird geworfen, wenn die Nachricht nicht vollständig angekommen ist.
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception
    {
        //System.out.println(topic + ":" + message.toString());
        ByteArrayInputStream b = new ByteArrayInputStream(message.getPayload());

        if (topic.equals(TPC.REFMACHINE))
        {
            rBLog.debug("Message Machine is arrived on ComRefBox!");
            ObjectInputStream o = new ObjectInputStream(b);
            Serializable d = (Serializable) o.readObject();

            transmitQueue.add(d);   //Das ankommende Objekt wird in der BlockingQueue gespeichert.
            /*synchronized (stationLock){
             discovMachine = (Station) d;
             sendMachine = true;          //In der run-Methode wird die Methode sendMachines() aufgerufen.
            }*/
            System.out.println("geblocked");
        }
        if (topic.equals(TPC.REFSETUP))
        {
            ObjectInputStream o = new ObjectInputStream(b);
            Serializable d = (Serializable) o.readObject();
            transmitQueue.add(d);
        }
    }

    /**
     * Erzwungene Methode von org.eclipse.paho.client.mqttv3.MqttCallback.
     *
     * @param imdt
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken imdt)
    {
    }

    /**
     * Erzwungene Methode vom Interface Runnable. Sobald ein neues Objekt Mps auf dem Broker ankommt
     * wird die Methode sendMachines() aufgerufen. In der Explorationsphase wird die Nachricht
     * Maschinenreport über den privaten Peer gesendet. Die Methode wird vom Scheduler im
     * Konstruktor alle 2 Sekunden aufgerufen.
     */
    @Override
    public void run()
    {
        Thread.currentThread().setName(this.getClass().getName());
        MachineReport mr = null;
        MachineInfo mi = null;
        PrepareMachine pm = null;
        while (true)
        {
            try
            {
                Object obj = transmitQueue.take();
                if (obj instanceof MachineInfo)  //wird wahrscheinlich nicht gebraucht (mit Tim abklären)
                {
                    mi = (MachineInfo) obj;

                    // Senden der Machineninfo über den privaten Kanal.
                    if (mi != null)
                    {
                        ProtobufMessage machineInfo = new ProtobufMessage(2000, 13, mi);
                        peerPrivate.enqueue(machineInfo); //Die Nachricht wird über den privaten Peer gesendet.
                        rBLog.debug("MachineInfo sent");
                    }
                } else if (obj instanceof PrepareMachine)       //Setup-Messages für Maschinen
                {
                    pm = (PrepareMachine) obj;

                    // Senden der Machineninfo über den privaten Kanal.
                    if (pm != null)
                    {
                        ProtobufMessage prepareMachine = new ProtobufMessage(2000, 101, pm);
                        peerPrivate.enqueue(prepareMachine); //Die Nachricht wird über den privaten Peer gesendet.
                        rBLog.debug("PrepareMachine sent");
                    }
                } else
                {
                    rBLog.debug("No Refbox Send");
                }
                /*
                 if (ProtoMsgHandler.game == null)
                 {
                 System.out.println("no game state yet");
                 try
                 {
                 Thread.sleep(1000);
                 } catch (InterruptedException ex)
                 {
                 }
                 } else
                 switch (ProtoMsgHandler.game.getPhase())
                 {
                 case EXPLORATION:
                 break;
                 case PRODUCTION:
                 break;
                 default:
                 break;
                 }*/
            } catch (InterruptedException ex)
            {
            }
        }
    }

    /**
     * Main-Methode für den lokalen Gebrauch. Die main-Methode wird verwendet, um das Programm lokal
     * auf dem Computer zu starten. Auf einem Robotino wird die Klasse in der StateMachine
     * instanziiert.
     *
     * @param args
     */
    public static void main(String[] args)
    {
        ComRefBox r1 = new ComRefBox();
    }
}
