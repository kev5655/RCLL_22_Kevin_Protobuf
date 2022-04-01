package refBox.min;

import java.io.IOException;
import java.nio.ByteBuffer;

import org.robocup_logistics.llsf_comm.ProtobufMessageHandler;
import org.robocup_logistics.llsf_msgs.BeaconSignalProtos.BeaconSignal;
import org.robocup_logistics.llsf_msgs.GameStateProtos.GameState;
import org.robocup_logistics.llsf_msgs.TimeProtos.Time;

import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.InvalidProtocolBufferException;
import org.robocup_logistics.llsf_msgs.VersionProtos.VersionInfo;

/**
 *
 * @author alain
 *
 *
 */
public class Handler implements ProtobufMessageHandler
{
    @Override
    public void handle_message(ByteBuffer in_msg, GeneratedMessage msg)
    {
        System.out.println("MSG_Print: " + msg.getClass().getSimpleName());
        if (msg instanceof BeaconSignal)
        {
            byte[] array = new byte[in_msg.capacity()];
            in_msg.rewind();
            in_msg.get(array);
            BeaconSignal bs = null;
            Time t;

            try
            {
                bs = BeaconSignal.parseFrom(array);
                t = bs.getTime();
                System.out.println(bs);
            } catch (InvalidProtocolBufferException e)
            {
                e.printStackTrace();
            }
        } else if (msg instanceof GameState)
        {
            byte[] array = new byte[in_msg.capacity()];
            in_msg.rewind();
            in_msg.get(array);
            GameState state;

            try
            {
                state = GameState.parseFrom(array);
                System.out.println(state);
                User.gameStateReceived(state);
            } catch (InvalidProtocolBufferException e)
            {
                e.printStackTrace();
            }
        } else if (msg instanceof VersionInfo)
        {
            byte[] array = new byte[in_msg.capacity()];
            in_msg.rewind();
            in_msg.get(array);
            VersionInfo ver;

            try
            {
                ver = VersionInfo.parseFrom(array);
                System.out.println(ver);

            } catch (InvalidProtocolBufferException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void connection_lost(IOException e)
    {
        System.out.println("Connection Lost!!!");
    }

    @Override
    public void timeout()
    {
        System.out.println("Timeout!!!");
    }
}
