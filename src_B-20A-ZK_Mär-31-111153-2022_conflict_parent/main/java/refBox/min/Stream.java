package refBox.min;

import java.io.IOException;
import java.nio.channels.UnresolvedAddressException;
import org.robocup_logistics.llsf_comm.ProtobufClient;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.GameStateProtos.GameState;
import org.robocup_logistics.llsf_msgs.RobotInfoProtos;
import org.robocup_logistics.llsf_msgs.RobotInfoProtos.RobotInfo;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;
import org.robocup_logistics.llsf_msgs.VersionProtos.VersionInfo;

public class Stream
{
    private final static String TEAM_NAME = "Solidus";
    private final static String ENCRYPTION_KEY = "randomkey";
    private static Team TEAM_COLOR = Team.CYAN;
    private final static String IP = "172.26.1.1";
    //private final static String IP = "192.168.56.101";

    private static ProtobufClient client;

    public static void main(String[] args)
    {
        //Send and receive message via broadcast
        //IMPORTANT: If you want to connect to a remote refbox, set both ports to 4444.
        //If you want to communicate with a local refbox (as in this example), you need to
        //set the send port to 4445 and edit the refbox's config.yaml as described in the
        //Configuration tutorial. This is required, because your refbox and your Java program
        //cannot listen on the same ports. After changing the configuration, your local refbox
        //will listen on port 4445 and send to port 4444, so your send and receive ports have
        //to be inverted.
        //See the Usage tutorial for more information: https://trac.fawkesrobotics.org/wiki/LLSFRefBox/Java/Usage
        //peerPublic = new ProtobufBroadcastPeer("x.x.x.255", 4445, 4444);

        ProtobufClient client = new ProtobufClient(IP, 4444);
        client.<RobotInfo>add_message(RobotInfo.class);
        client.<BeaconSignal>add_message(BeaconSignal.class);
        client.<VersionInfo>add_message(VersionInfo.class);
        client.<GameState>add_message(GameState.class);
        
        try
        {
            client.connect();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (UnresolvedAddressException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        client.<RobotInfoProtos.RobotInfo>add_message(RobotInfoProtos.RobotInfo.class);
        Handler handler = new Handler();
        client.register_handler(handler);

        while (true)
        {
            try
            {
                Thread.sleep(100);
            } catch (InterruptedException ex)
            {
            }
        }
    }
}