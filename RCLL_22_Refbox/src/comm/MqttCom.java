package comm;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Simple MQTT-Facade-Class to demonstrate the simplest Features of MQTT needs mqtt-client-0.4.0.jar
 * The Subscriber class has to implements MqttCallback to set with method setCallback(..,)
 *
 * @author alain.rohr
 */
public class MqttCom
{
    private final MqttAsyncClient client;

    public static final int QOS_DEF = 1;
    public static final int QOS_2 = 2;
    public static final boolean RETAINED_DEF = false;
    public static final String STATUS = "status/";

    public MqttCom(String brokerAddr, String clientId)
    {
        this(brokerAddr, clientId, STATUS + clientId, "offline", true);
    }
    
    public MqttCom(String brokerAddr, String clientId, String lastWillMsgTopic, String lastWillMsg, boolean lastWillRetained){
        try
        {
            client = new MqttAsyncClient(brokerAddr, clientId+java.util.UUID.randomUUID().toString().substring(0,8), new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setWill(lastWillMsgTopic, lastWillMsg.getBytes(), 1, lastWillRetained);
            client.connect(options).waitForCompletion();
            client.setManualAcks(false);
            this.send(STATUS + clientId, "online", 1, true);
        } catch (MqttException e)
        {
            throw new IllegalArgumentException("no mqtt con");  //muss nicht abgefangen werden --> Runtime
        }
    }

    public boolean isConnected(){
        return client.isConnected();
    }
    public void reconnect(boolean clean)
    {
        System.out.println("reconnect aufgerufen");
        //client.reconnect();  //nicht mehr nötig, da AutomaticReconnect auf true
    }

    /**
     * set the Receive Handler for this topic
     *
     * @param receiver Class witch recieves the messages
     */
    public void setCallback(MqttCallback receiver)
    {
        client.setCallback(receiver);
    }

    public void subscribe(String topic) throws MqttException
    {
        client.subscribe(topic, QOS_DEF);
    }

    public void unsubscribe(String topic) throws MqttException
    {
        client.unsubscribe(topic);
    }

    public void sendArray(String topic, byte[] arr, int qoS, boolean retained) throws MqttException
    {
        MqttMessage message = new MqttMessage(arr);
        message.setQos(qoS);
        message.setRetained(retained);
        IMqttDeliveryToken token = client.publish(topic, message);  //ev. bei Blocking waitForCompletion();
        token.waitForCompletion();
    }

    public void sendArray(String topic, byte[] arr) throws MqttException
    {
        sendArray(topic, arr, QOS_DEF, RETAINED_DEF);
    }

    /**
     * Send serializeable object on spezified topic and with quality of service and possibility to
     * retain the last message
     *
     * @param msg
     * @param topic
     * @param qos
     * @param retained
     * @throws MqttException
     */
    public void send(String topic, Serializable msg, int qoS, boolean retained) throws MqttException
    {
        if (msg instanceof String)
        {
            sendArray(topic, ((String) msg).getBytes(), qoS, retained);
        } else
        {
            ObjectOutputStream o = null;
            try
            {
                ByteArrayOutputStream b = new ByteArrayOutputStream();
                o = new ObjectOutputStream(b);
                o.writeObject(msg);
                sendArray(topic, b.toByteArray(), qoS, retained);
            } catch (IOException ex)
            {
                System.out.println(ex);
            } finally
            {
                try
                {
                    o.close();
                } catch (IOException ex)
                {
                }
            }
        }
    }

    public void send(String topic, Serializable msg, boolean retained) throws MqttException
    {
        send(topic, msg, QOS_DEF, retained);
    }

    public void send(String topic, Serializable msg, int qoS) throws MqttException
    {
        send(topic, msg, qoS, RETAINED_DEF);
    }

    /**
     * Send serializeable object on spezified topic
     *
     * @param topic
     * @param obj serializable
     * @throws MqttException
     */
    public void send(String topic, Serializable msg) throws MqttException
    {
        send(topic, msg, QOS_DEF, RETAINED_DEF);
    }
}