package refBox.min;

import java.io.IOException;

import org.robocup_logistics.llsf_comm.ProtobufBroadcastPeer;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.GameStateProtos.GameState;
import org.robocup_logistics.llsf_msgs.OrderInfoProtos.OrderInfo;
import org.robocup_logistics.llsf_msgs.TeamProtos.Team;

public class Public
{
    private final static String TEAM_NAME = "Solidus";
    private final static String ENCRYPTION_KEY = "randomkey";
    private static Team TEAM_COLOR = Team.CYAN;
    //private final static String IP = "172.26.1.1";
    private final static String IP = "127.0.0.1";

    private static ProtobufBroadcastPeer peerPublic;
    private static ProtobufBroadcastPeer peerPrivate;

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
        peerPublic = new ProtobufBroadcastPeer(IP, 4444, 4444);
        peerPublic.<BeaconSignal>add_message(BeaconSignal.class);
        peerPublic.<GameState>add_message(GameState.class);
        peerPublic.<OrderInfo>add_message(OrderInfo.class);
        try
        {
            peerPublic.start();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Handler handler = new Handler();
        peerPublic.register_handler(handler);
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
