package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Parse Tag status message

public class MQTTMsgTagStatusParser {

    public static void parse(String message) {
        System.out.println("== Tag Status Message");

        String msgID = message.substring(0,2);
        String rs485Addr = message.substring(2, 4);
        String stripId = message.substring(4, 12);
        String reserved = message.substring(12, 14);
        String numModules = message.substring(14, 16);
        String numTagsHex = message.substring(16, 18);
        int numTags = Integer.parseInt(numTagsHex, 16);

        int LOC12 = MQTTTools.LOC12;

        System.out.println(" ".repeat(LOC12-msgID.length())      + msgID      + ": Message ID");
        System.out.println(" ".repeat(LOC12-rs485Addr.length())  + rs485Addr  + ": Addr ID");
        System.out.println(" ".repeat(LOC12-stripId.length())    + stripId    + ": Module ID");
        System.out.println(" ".repeat(LOC12-reserved.length())   + reserved   + ": Reserved");
        System.out.println(" ".repeat(LOC12-numModules.length()) + numModules + ": U capacity [Dec:"+Integer.parseInt(numModules,16)+"]");
        System.out.println(" ".repeat(LOC12-numTagsHex.length())    + numTagsHex    + ": Tag count [Dec:"+numTags+"]");


        System.out.println(" ".repeat(LOC12) + ": U Position|Alarm|Tag ID");
        System.out.print(" ".repeat(LOC12)   + "  ");

        int offset = 18;
        int sectionLength = 12;

        for (int i = 0; i < numTags; i++) {
            int start = offset + i * sectionLength;
            int end = start + sectionLength;

            if (end > message.length()) break;

            String section = message.substring(start, end);
            String seq = section.substring(0, 2);
            String alarm = section.substring(2, 4);
            String tagId = section.substring(4, 12);

            System.out.print("" + seq   + "|");
            System.out.print("" + alarm + "|");
            System.out.print("" + tagId + "  ");
        }

        System.out.println("");
    }
}