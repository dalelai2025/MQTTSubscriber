package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Parse u-level color message

public class MQTTMsgColorStatusParser {

    public static void parse(String message) {
        System.out.println("== Color Message");
        if(message.length() <= 8 ){
            System.out.println("== Error Color Set CMD String : " + message);
            return;
        }

        String msgID = message.substring(0,2);
        String gatewayID = message.substring(2, 10);        
        String colourCMDSetResult = message.substring(10, 12);
        String setColourCmdCode = message.substring(12, 14);
        String rs485Addr = message.substring(14, 16);

        int LOC12 = MQTTTools.LOC12;

        System.out.println(" ".repeat(LOC12-msgID.length())  + msgID  + ": Message ID");
        System.out.println(" ".repeat(LOC12-gatewayID.length())  + gatewayID  + ": Gateway ID");
        System.out.println(" ".repeat(LOC12-colourCMDSetResult.length())  + colourCMDSetResult  + ": Colour Set Cmd Result (A0 - failure, A1 - Success)");
        System.out.println(" ".repeat(LOC12-setColourCmdCode.length())  + setColourCmdCode  + ": Colour Set Cmd Code");
        System.out.println(" ".repeat(LOC12-rs485Addr.length())  + rs485Addr  + ": Addr ID");

        int numColourSet = ( message.length() - 8 ) / 2; 
        int offset = 16;
        int sectionLength = 4;

        System.out.println(" ".repeat(LOC12) + ": U Position|Color");
        System.out.print(" ".repeat(LOC12)   + "  ");

        for (int i = 0; i < numColourSet; i++) {
            int start = offset + i * sectionLength;
            int end = start + sectionLength;

            if (end > message.length()) break;

            String section = message.substring(start, end);         
            String strPosition = section.substring(0, 2);
            String colourCode = section.substring(2, 4);
            System.out.print("" + strPosition  + "|");
            System.out.print("" + colourCode   + " ");
        }

        System.out.println(" ");
        
    }
}