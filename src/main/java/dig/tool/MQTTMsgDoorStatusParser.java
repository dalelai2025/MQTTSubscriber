package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Parse cabinet door status message

public class MQTTMsgDoorStatusParser {

    public static void parse(String message) {
        System.out.println("== Door Status Message");

        String msgID = message.substring(0,2);
        String rs485Addr = message.substring(2, 4);
        String stripId = message.substring(4, 12);
        String status = message.substring(12, 14);

        int LOC12 = MQTTTools.LOC12;

        System.out.println(" ".repeat(LOC12-msgID.length())      + msgID      + ": Message ID");        
        System.out.println(" ".repeat(LOC12-rs485Addr.length())  + rs485Addr  + ": Addr ID");
        System.out.println(" ".repeat(LOC12-stripId.length())    + stripId    + ": Module ID");
        System.out.println(" ".repeat(LOC12-status.length())     + status     + ": Cabinet Door Status (01-Open, 00-Close, 01-default)");

    }
}