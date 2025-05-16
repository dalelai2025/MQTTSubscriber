package dig.tool;

import org.eclipse.paho.client.mqttv3.*;

// Created: 2025.03.18, Author: Dale
// Description: A MQTT Client
// 


public class MQTTSubscriber {

    public static void main(String[] args) {
        String brokerIP = null;
        String brokerPort = null;
        String topic = null;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-IP":
                    if (i + 1 < args.length) brokerIP = args[++i];
                    break;
                case "-Port":
                    if (i + 1 < args.length) brokerPort = args[++i];
                    break;
                case "-Topic":
                    if (i + 1 < args.length) topic = args[++i];
                    break;
            }
        }

        // Validate inputs
        if (brokerIP == null || brokerPort == null || topic == null) {
            System.out.println("\nUsage: java -jar MQTTSubscriber.jar -IP <broker_ip> -Port <port_number> -Topic <topic_name>");
            System.exit(1);
        }

        String brokerUrl = "tcp://" + brokerIP + ":" + brokerPort;
        String clientId = MqttClient.generateClientId();

        try {

            MqttClient client = new MqttClient(brokerUrl, clientId, null);
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);

            System.out.println("Connecting to broker: " + brokerUrl);
            client.connect(options);
            System.out.println("Connected");

            client.subscribe(topic, (topicName, message) -> {
                
                byte[] payload = message.getPayload();
                String hex = bytesToHex(payload);
                System.out.println("== Message Received [" + topicName + "]: " + hex);

                //parse the MQTT message
                MQTTMsgParser.parse(hex);

            });

            System.out.println("Subscribed to topic: " + topic);
            System.out.println("Listening for messages... Press Ctrl+C to exit.");

            
            
            // Add shutdown hook
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    System.out.println("\nDisconnecting from broker...");
                    client.disconnect();
                    System.out.println("Disconnected cleanly.");
                } catch (MqttException e) {
                    System.err.println("Error during disconnect: " + e.getMessage());
                }
            }));

        } catch (MqttException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
            
    }



    // Converts byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    // Converts hex string to ASCII string
    private static String hexToAscii(String hex) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String str = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(str, 16));
        }
        return sb.toString();
    }






}
