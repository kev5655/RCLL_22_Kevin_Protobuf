package refBox.sim;

import comm.MqttCom;
import comm.TPC;
import dataobjects.Field;
import fieldcommander.StationBS;
import fieldcommander.StationControl;
import fieldcommander.StationRS;
import java.util.Scanner;
import main.Cfg;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.robocup_logistics.llsf_msgs.MachineDescriptionProtos.CSOp;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.Machine;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.MachineSide;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareMachine;
import org.robocup_logistics.llsf_msgs.ProductColorProtos;
import org.robocup_logistics.llsf_msgs.ProductColorProtos.BaseColor;
import org.robocup_logistics.llsf_msgs.ProductColorProtos.RingColor;
import org.robocup_logistics.llsf_msgs.TeamProtos;
import org.robocup_logistics.llsf_msgs.ZoneProtos;
import refBox.Msg;

/**
 * Simuliert die Meldungen von entdeckten Machinen in der Explophase und dann die Setup-Meldungen
 * während der Produktionsphase
 *
 * @author Alain Rohr
 * @version 1.0
 * @see refBox.ComRefBox
 *
 */
public class SimMachineInfo
{
    private final String BROKERUSER = "SimRefBox" + Cfg.roboNr;  //Brokerbenutzername mit Roboternummer.
    Scanner in = new Scanner(System.in);

    // Variablen für die Kommunikation mit dem Broker.
    private MqttCom brokerLocal; //Broker der lokal auf dem Robotino läuft.
    //private MqttCom brokerCentral; //Broker der zentral auf einem RaspberryPi läuft.

    /**
     * Stellt die Verbindung zum Broker her. Über das setCallback() wird ein Topic als Feed
     * abonniert. Bei jeder Änderung auf dem entsprechenden Topic, wird die Methode messageArrived()
     * aufgerufen.
     *
     * @see #messageArrived(java.lang.String, org.eclipse.paho.client.mqttv3.MqttMessage)
     */
    private void comBroker()
    {
        brokerLocal = new MqttCom("tcp://172.26.107.151:1883", BROKERUSER); //Verbindung zum lokalen Broker.
        //brokerLocal = new MqttCom("tcp://127.0.0.1:1883", BROKERUSER); //Verbindung zum lokalen Broker.

//brokerLocal = new MqttCom("tcp://127.0.0.1:1883", BROKERUSER); //Verbindung zum lokalen Broker.
    }

    /**
     * Kommunikation mit dem Broker. Status, Phase und die Zonen werden temporär manuell erzeugt und
     * auf den Broker gesendet.
     *
     * @throws MqttException Wird geworfen, wenn ein Fehler bei der Brokerkommunikation auftritt.
     */
    public void start() throws MqttException
    {
        comBroker();
        while (true)
        {
            System.out.println("1) MachineReport Cyan BS Message 1 (Zone)");
            System.out.println("2) MachineReport Cyan BS Message 2 (Lamp)");
            System.out.println("3) MachineReport Cyan DS (Zone 4");
            System.out.println("4) Machine Info from Refbox");
            System.out.println("5) Set MachineInfo C-BS");
            System.out.println("6) Set MachineInfo C-RS1");
            System.out.println("7) Set MachineInfo C-CS1");
            System.out.println("8) Set MachineInfo C-DS");
            System.out.println("9) Set Game paused\n");
            System.out.println("0) Exit");

            String userInput = in.nextLine();

            switch (userInput)
            {
                case "1":
                    Field.generateZones();
                    StationControl.addStation("C52", 0, 2, "");
                    //brokerLocal.send(TPC.REFMACHINE, StationControl.getPreviousStation());
                    //brokerLocal.send(TPC.REFMACHINE, new StationBS("C-CS", stationZoneName, 45, 161, 162));
                    //brokerLocal.send(TPC.REFMACHINE, new StationBS("M-BS","M53",0,161,162));
                    break;
                case "2":
                    brokerLocal.send(TPC.REFMACHINE, new StationBS("M-CS1", "C66", 45, 161, 162));
                case "3":
                    //brokerLocal.send(TPC.REFMACHINE, new StationDS(null, null, null, 49, 50, "C-DS", "4"));
                    brokerLocal.send(TPC.REFMACHINE, new StationBS("M-CS2", "C67", 45, 161, 162));
                    break;
                case "4":
                    Machine m = Machine.newBuilder().setName("C-BS").setZone(ZoneProtos.Zone.C_Z63).build();
                    brokerLocal.send(TPC.RB_MACHINES, m);
                case "5":
                    Cfg.team = TeamProtos.Team.valueOf("CYAN");
                    PrepareMachine pBS = Msg.prepareMachineInfoBS("C-BS", BaseColor.BASE_RED, MachineSide.INPUT);
                    brokerLocal.send(TPC.REFSETUP, pBS);
                    break;
                case "6":
                    PrepareMachine pRS = Msg.prepareMachineInfoRS("C-RS1", RingColor.RING_BLUE);
                    brokerLocal.send(TPC.REFSETUP, pRS);
                    break;
                case "7":
                    PrepareMachine pCS = Msg.prepareMachineInfoCS("C-CS1", CSOp.MOUNT_CAP);
                    brokerLocal.send(TPC.REFSETUP, pCS);
                    break;
                case "8":
                    PrepareMachine pDS = Msg.prepareMachineInfoDS("C-DS", 1, 1);
                    brokerLocal.send(TPC.REFSETUP, pDS);
                    break;
                case "9":
                    brokerLocal.send(TPC.STATE, "PAUSED");
                    break;
                case "0":
                    System.exit(0);
                    break;
            }
        }
    }

    public static void main(String[] args) throws MqttException
    {
        //StateMachine sm=new StateMachine();
        new SimMachineInfo().start();
    }
}
