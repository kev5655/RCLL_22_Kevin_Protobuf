package refBox;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.Cfg;
import main.LogFact;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.ExplorationInfoProtos.ExplorationInfo;
import org.robocup_logistics.llsf_msgs.ExplorationInfoProtos.ExplorationSignal;
import org.robocup_logistics.llsf_msgs.ExplorationInfoProtos.ExplorationZone;
import org.robocup_logistics.llsf_msgs.MachineDescriptionProtos.CSOp;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.Machine;
import org.robocup_logistics.llsf_msgs.MachineInfoProtos.MachineInfo;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.MachineSide;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareInstructionBS;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareInstructionCS;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareInstructionDS;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareInstructionRS;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareInstructionSS;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.PrepareMachine;
import org.robocup_logistics.llsf_msgs.MachineInstructionProtos.ResetMachine;
import org.robocup_logistics.llsf_msgs.ProductColorProtos.BaseColor;
import org.robocup_logistics.llsf_msgs.ProductColorProtos.RingColor;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;
import org.robocup_logistics.llsf_msgs.TimeProtos.Time;

/**
 *
 * @author alain
 */
public class Msg
{
    // Listen von den Nachrichteninhalten
    private static List<ExplorationZone> zoneList; //Liste mit den zwölf, dem Team zugeteilten, Zonen.
    private static List<ExplorationSignal> signalList; //Liste mit den Lichtmustern für die einzelnen aufgestellten Maschinen (MPS).
    //private static List<Order> orderList; //Liste mit den Bestellungen.
    private static List<Machine> machineList; //Liste mit den Maschinen (MPS) in der Produktionsphase.

    // Spielzustand
    public static Map<String, String> lightPatternMap = new HashMap<>(); //Map mit dem Lichtmuster jeder Maschine (MPS).

    public static org.apache.log4j.Logger rBLog;//Logger

    static
    {
        rBLog = LogFact.get().mainLog;
    }

    /**
     * Empfängt das Lebenszeichen der Refbox. Aus der Nachricht kann die aktuelle Zeit, die
     * Sequenznummer, die Roboternummer, der Teamname, der Robotername, die Teamfarbe oder die
     * aktuelle Position abgerufen werden.
     *
     * @param array
     * @param in_msg
     * @return
     */
    public static BeaconSignal recvBeacon(byte[] array)
    {
        BeaconSignal beaconSignal = null;
        Time t;
        try
        {
            beaconSignal = BeaconSignal.parseFrom(array);
            t = beaconSignal.getTime();
            //LogFact.getMain().debug("Beaconsignal erhalten");
        } catch (InvalidProtocolBufferException e)
        {
            rBLog.fatal("Fault while parsing BeaconSignal. Message: " + e.getMessage());
        }
        return beaconSignal;
    }

    /**
     * Handelt die Zonen und das Lichtsignalmuster in der Explophase. Die Zonen des eigenen Teams
     * werden aus der Liste in ein Array gelesen. Das Array wird zu einem String geparst und über
     * den Broker an den ExploController gesendet. Das Lichtsignalmuster wird in einer HashMap
     * gespeichert. Das Muster ist dabei der Schlüssel und der TypeString der Wert. Mit dem
     * erkannten Lichtmuster kann der entsprechende TypeString aus der HashMap gelesen und an die
     * Refbox gesendet werden.
     *
     * @see #sendMachines(fieldcommander.Mps)
     * @param in_msg
     */
    public static String recvExploInfo(byte[] array, Team teamColor)
    {
        ExplorationInfo info;
        String sendTxt = "";
        //LogFact.getMain().debug("Exploration info received:");
        try
        {
            info = ExplorationInfo.parseFrom(array);

            zoneList = info.getZonesList();
            int[] zarray = new int[12];
            // In der Liste stehen jeweils alle Zonen beider Teams.
            // Für jede Zone steht auch die dazugehörige Teamfarbe.
            // Die Reihenfolge ist sortiert und fängt mit CYAN an.
            switch (teamColor)
            {
                // Falls die Teamfarbe CYAN ist, werden die ersten zwölf Zeilen (0-11)
                // aus der Liste ausgelesen und in das Array gespeichert.
                case CYAN:
                    for (int i = 0; i < 12; i++)
                    {
                        zarray[i] = zoneList.get(i).getZone().getNumber();
                    }
                    break;
                // Falls die Teamfarbe MAGENTA ist, werden die Zeilen 12-23 
                // aus der Liste ausgelesen und in das gleiche Array gespeichert.
                case MAGENTA:
                    for (int i = 0; i < 12; i++)
                    {
                        zarray[i] = zoneList.get(i + 12).getZone().getNumber();
                    }
                    break;
                default:
                    rBLog.debug("No Teamname to set the Zones in Explo.");
                    break;
            }
            // Danach wird versucht die Zonen als String in der Form "1-Z2-Z3" einmalig auf den Broker zu senden.
            sendTxt = zarray[0] + "-Z" + zarray[1] + "-Z" + zarray[2] + "-Z" + zarray[3] + "-Z" + zarray[4]
                + "-Z" + zarray[5] + "-Z" + zarray[6] + "-Z" + zarray[7] + "-Z" + zarray[8] + "-Z" + zarray[9]
                + "-Z" + zarray[10] + "-Z" + zarray[11];

            signalList = info.getSignalsList(); // Die Liste mit den Lichtmustern wird aus der Nachricht in eine eigene Liste gespeichert.

            //  Für jeden TypeString werden die Lampenzustände in einen String verpackt, mit der Form "000", "RedOrangeGreen".
            for (int i = 0; i < 6; i++)
            {
                ExplorationSignal m0 = signalList.get(i);
                String lightPatternString = "" + m0.getLights(0).getState().getNumber() //String Lampenmuster "011".
                    + m0.getLights(1).getState().getNumber()
                    + m0.getLights(2).getState().getNumber();
                lightPatternMap.put(lightPatternString, m0.getType()); //Eintrag in die Map; "011", "kFd$a6xb".
            }
//          rBLog.debug("Lichtmuster: " + lightPatternMap.toString());
        } catch (InvalidProtocolBufferException ex)
        {
            rBLog.fatal("Error while parsing explo signal. Message: " + ex);
        }
        return sendTxt;
    }

    /**
     * Es werden die Informationen über die Maschinen (MPS) in der Produktionsphase verarbeitet.
     *
     * @param in_msg
     * @return
     */
    public static MachineInfo recvMachines(byte[] array)
    {
        MachineInfo mInfo = null;
        try
        {
            mInfo = MachineInfo.parseFrom(array);
            machineList = mInfo.getMachinesList();
            rBLog.debug("MACHINE INFOS received!");
            for (Machine machine : machineList)
            {
                rBLog.debug("Name: " + machine.getName() + "-> Type: " + machine.getType() + "-> Status:" + machine.getState() + "-> Zone:" + machine.getZone());
            }
        } catch (InvalidProtocolBufferException ex)
        {
            rBLog.fatal("Error while parsing machine info. Message: " + ex);
        }
        return mInfo;
    }

    public static PrepareMachine prepareMachineInfoBS(String name, BaseColor color, MachineSide side)
    {
        PrepareInstructionBS pIBS = PrepareInstructionBS.newBuilder().
            setSide(side).
            setColor(color).
            build();
        PrepareMachine pM = PrepareMachine.newBuilder().
            setTeamColor(Cfg.team).setMachine(name).setInstructionBs(pIBS).build();
        return pM;
    }

    public static PrepareMachine prepareMachineInfoRS(String name, RingColor ringColor)
    {
        PrepareInstructionRS pIRS = PrepareInstructionRS.newBuilder().
            setRingColor(ringColor).
            build();
        PrepareMachine pM = PrepareMachine.newBuilder().
            setTeamColor(Cfg.team).setMachine(name).setInstructionRs(pIRS).build();
        return pM;
    }

    public static PrepareMachine prepareMachineInfoCS(String name, CSOp csOp)
    {
        PrepareInstructionCS pICS = PrepareInstructionCS.newBuilder().
            setOperation(csOp). //MOUNT_CAP / RETRIEVE_CAP
            build();
        PrepareMachine pM = PrepareMachine.newBuilder().
            setTeamColor(Cfg.team).setMachine(name).setInstructionCs(pICS).build();
        return pM;
    }

    public static PrepareMachine prepareMachineInfoDS(String name, int gateNr, int orderID)
    {
        PrepareInstructionDS pIDS = PrepareInstructionDS.newBuilder().
            setGate(gateNr).
            setOrderId(orderID).
            build();
        PrepareMachine pM = PrepareMachine.newBuilder().
            setTeamColor(Cfg.team).setMachine(name).setInstructionDs(pIDS).build();

        return pM;
    }

    public static PrepareMachine prepareMachineInfoSS(String name)
    {
        PrepareInstructionSS pIDS = PrepareInstructionSS.newBuilder().
            //setOperation(MachineDescriptionProtos.SSOp.RETRIEVE).
            build();
        PrepareMachine pM = PrepareMachine.newBuilder().
            setTeamColor(Cfg.team).setMachine(name).setInstructionSs(pIDS).build();
        return pM;
    }

    public static ResetMachine resetMachine(String name)
    {
        ResetMachine rM = ResetMachine.newBuilder().
            setMachine(name).
            setTeamColor(Cfg.team).
            build();
        return rM;
    }
}
