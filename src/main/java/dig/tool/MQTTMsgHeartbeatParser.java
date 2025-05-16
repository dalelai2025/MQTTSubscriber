package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Parse heart beat message

public class MQTTMsgHeartbeatParser {

    public static void parse(String message) {
        System.out.println("== Heartbeat Message");

        int LOC12 = MQTTTools.LOC12;
        int sectionLength = 12; // 6 bytes = 12 hex chars
        int numSections = 5; //V5008 support max 5 racks
        int offset = 2;

        //print Message ID
        System.out.println(" ".repeat(LOC12-2) +  message.substring(0,2)  + ": Message ID");
        System.out.println(" ".repeat(LOC12) + ": Addr ID|Module ID|U capacity");
        System.out.print(" ".repeat(LOC12)   + "  " );


        for (int i = 0; i < numSections; i++) {
            int start = offset + i * sectionLength;
            int end = start + sectionLength;

            if (end > message.length()) break;

            String section = message.substring(start, end);
            String rs485Addr = section.substring(0, 2);
            String stripId = section.substring(2, 10);
            String numModules = section.substring(10, 12);

            System.out.print("" +  rs485Addr  + "|");
            System.out.print("" +  stripId    + "|");
            System.out.print("" + numModules  + " ");

        }

        System.out.println();
    }
}
