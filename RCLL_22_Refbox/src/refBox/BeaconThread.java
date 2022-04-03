package refBox;

import main.Cfg;
import main.LogFact;
import org.robocup_logistics.llsf_comm.ProtobufBroadcastPeer;
import org.robocup_logistics.llsf_comm.ProtobufMessage;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos;
import org.robocup_logistics.llsf_msgs.Pose2DProtos;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;
import org.robocup_logistics.llsf_msgs.TimeProtos;
import org.robocup_logistics.llsf_utils.NanoSecondsTimestampProvider;

/**
 *
 * @author alain
 */
public class BeaconThread extends Thread
{
    ProtobufBroadcastPeer peer;
    Team teamColor;

    /**
     * Der Roboter sendet alle 2 Sekunden ein Lebenszeichen an die Refbox. Wenn das Signal bei der
     * Refbox ankommt, erscheint Rechts auf der Seite bei der Shell der Robotername mit der IP. Der
     * Thread wird gestartet, sobald die Teamfarbe in der GameState Nachricht gesetzt wurde.
     *
     * @see #gameState(java.nio.ByteBuffer)
     */
    public BeaconThread(ProtobufBroadcastPeer peer, Team teamColor)
    {
        this.peer = peer;
        this.teamColor = teamColor;
    }

    @Override
    public void run()
    {
        Thread.currentThread().setName(this.getClass().getName());
        LogFact.get().mainLog.debug("Beacon Thread started!");

        int seq = 1;
        while (true)
        {
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e)
            {
                LogFact.get().mainLog.fatal("Error in BeaconThread while thread sleep.");
            }

            NanoSecondsTimestampProvider nstp = new NanoSecondsTimestampProvider();

            long ms = System.currentTimeMillis();
            long ns = nstp.currentNanoSecondsTimestamp();

            int sec = (int) (ms / 1000);
            long nsec = ns - (ms * 1000000L);

            TimeProtos.Time t = TimeProtos.Time.newBuilder().setSec(sec).setNsec(nsec).build();

            Pose2DProtos.Pose2D pos = Pose2DProtos.Pose2D.newBuilder()
                .setX(0)
                .setY(0)
                .setOri(0)
                .setTimestamp(t)
                .build();

            BeaconSignalProtos.BeaconSignal beaconSignal = BeaconSignalProtos.BeaconSignal.newBuilder().
                setTime(t).
                setSeq(seq++).
                setNumber(Cfg.roboNr). //Robo-Nr(Jersey)
                setPeerName("R-" + Cfg.roboNr). //Robo-Name
                setTeamName(Cfg.teamName).
                setTeamColor(teamColor).
                setPose(pos).
                build();
            ProtobufMessage msg = new ProtobufMessage(2000, 1, beaconSignal);
            peer.enqueue(msg);
        }
    }
}
