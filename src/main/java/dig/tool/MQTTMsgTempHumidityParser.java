package dig.tool;



public class MQTTMsgTempHumidityParser {

    public static void parse(String message) {
        System.out.println("== Temperature & Humidity Message");

        String rs485Addr = message.substring(0, 2);
        String stripId = message.substring(2, 10);

        int LOC12 = MQTTTools.LOC12;
        System.out.println(" ".repeat(LOC12-rs485Addr.length())  + rs485Addr  + ": Addr ID");      
        System.out.println(" ".repeat(LOC12-stripId.length())    + stripId    + ": Module ID");          

        System.out.println(" ".repeat(LOC12) + ": Addr ID|Temperature|Humidity");
        System.out.print(" ".repeat(LOC12) + "  ");        

        int offset = 10;
        int sectionLength = 10;
        int sectionIndex = 1;

        while (offset + sectionLength <= message.length()) {
            String section = message.substring(offset, offset + sectionLength);

            String sensorAddr = section.substring(0, 2);
            String tempInt = section.substring(2, 4);
            String tempFrac = section.substring(4, 6);
            String humInt = section.substring(6, 8);
            String humFrac = section.substring(8, 10);
           
            System.out.print("" + sensorAddr +"|");
            System.out.print("" + Integer.parseInt(tempInt, 16) + "." + Integer.parseInt(tempFrac, 16)+"|");
            System.out.print("" + Integer.parseInt(humInt, 16)  + "." + Integer.parseInt(humFrac, 16) +"  ");

            offset += sectionLength;
            sectionIndex++;
        }

        System.out.println("");

    }
}