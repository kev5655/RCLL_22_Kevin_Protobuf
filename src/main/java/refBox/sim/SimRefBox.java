package refBox.sim;

import comm.MqttCom;
import comm.TPC;
import java.util.Scanner;

import org.eclipse.paho.client.mqttv3.MqttException;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.Machine;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.MachineInfo;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos.Order;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos.OrderInfo;
import org.robocup_logistics.llsf_msgs.ProductColorProtos;

/**
 * Klasse für Refboxsimulation. Während dem PoE war die ComRefBox für eine Kommunikation noch nicht
 * soweit fertig. Deshalb wurde temporär diese Klasse für die Tests aufgebaut.
 *
 * @author Lukas Hofmann
 * @version 1.0
 * @see refBox.ComRefBox
 *
 */
public class SimRefBox
{
    private static final int JERSEY_NR = 987;
    private final String BROKERUSER = "SimRefBox" + JERSEY_NR;  //Brokerbenutzername mit Roboternummer.
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
    }

    public OrderInfo generateOrders()
    {
        Order o = Order.newBuilder()
            .setBaseColor(ProductColorProtos.BaseColor.BASE_RED)
            .build();
        OrderInfo oInfo = OrderInfo.newBuilder()
            .addOrders(o)
            .build();
        return oInfo;
    }

    public MachineInfo generateMachines()
    {
        //List<Machine> mList;
        Machine m = Machine.newBuilder()
            .addRingColors(ProductColorProtos.RingColor.RING_BLUE)
            .build();
        MachineInfo mInfo = MachineInfo.newBuilder()
            .addMachines(m)
            .build();
        return mInfo;
        /*
         mList = mInfo.getMachinesList();
       
         for (Machine mc : mList)
         {
         System.out.println("Name: " + mc.getName() + "-> Type: " + mc.getType()
         + "-> Status:" + mc.getState() + "-> Zone:" + mc.getZone());
         }*/
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
            System.out.println("1) Set State Running");
            System.out.println("2) Set State Pause");
            System.out.println("3) Set EXPLORATION Phase");
            System.out.println("4) Set PRODUCTION Phase");
            System.out.println("5) Send MachineInfo");
            System.out.println("6) Send OrderInfo");

            String userInput = in.nextLine();

            switch (userInput)
            {
                case "1":
                    brokerLocal.send(TPC.STATE, "RUNNING");
                    break;
                case "2":
                    brokerLocal.send(TPC.STATE, "PAUSED");
                    break;
                case "3":
                    brokerLocal.send(TPC.PHASE, "EXPLORATION");
                    break;
                case "4":
                    brokerLocal.send(TPC.PHASE, "PRODUCTION");
                    break;
                case "5":
                    brokerLocal.send(TPC.RB_MACHINES, generateMachines());
                    break;
                case "6":
                    brokerLocal.send(TPC.RB_ORDERS, generateOrders());
                    break;
            }
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex)
            {
            }
        }
    }

    /**
     * Main-Methode für den lokalen Gebrauch. Die main-Methode wird verwendet, um das Programm lokal
     * auf dem Computer zu starten.
     *
     * @param args
     */
    public static void main(String[] args) throws MqttException
    {
        new SimRefBox().start();
    }
}
