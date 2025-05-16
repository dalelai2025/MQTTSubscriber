package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Invoke the MQTT message Parser according to the Message ID

public class MQTTMsgParser {

    public static void parse(String hexMessage) {
        
        if (hexMessage == null || hexMessage.length() < 2) {
            System.out.println("Invalid message.");
            return;
        }
		
		hexMessage = hexMessage.replaceAll("\\s+", ""); // remove all whitespace
        String messageId = hexMessage.substring(0, 2).toUpperCase();

        switch (messageId) {
            case MQTTTools.MSGID_HBEAT1: //CB
            case MQTTTools.MSGID_HBEAT2: //CC
               MQTTMsgHeartbeatParser.parse(hexMessage);
                break;               
            case MQTTTools.MSGID_TAG_STAT://BB
                MQTTMsgTagStatusParser.parse(hexMessage);
                break;
            case MQTTTools.MSGID_TEMP_HUM1://01
            case MQTTTools.MSGID_TEMP_HUM2://02
            case MQTTTools.MSGID_TEMP_HUM3://03
            case MQTTTools.MSGID_TEMP_HUM4://04
            case MQTTTools.MSGID_TEMP_HUM5://05
                MQTTMsgTempHumidityParser.parse(hexMessage);
                break;
            case MQTTTools.MSGID_DOOR_STAT://BA
                MQTTMsgDoorStatusParser.parse(hexMessage);
                break;
            case MQTTTools.MSGID_INFO://EF
                MQTTGatewayAndModuleInfoParser.parse(hexMessage);
                break;
            case MQTTTools.MSGID_COLOUR_STAT://AA
                MQTTMsgColorStatusParser.parse(hexMessage);
                break;                
            default:
                System.out.println("== Unknown Message ID: " + messageId);
        }
    }

}
