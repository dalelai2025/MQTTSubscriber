package dig.tool;


// Created: 2025.03.18, Author: Dale
// Description: Parse gateway and module info message

public class MQTTGatewayAndModuleInfoParser {

    public static void parse(String message) {
        System.out.println("== Gateway and Module Info");

        String msgID = message.substring(0,2);
        String typeID  = message.substring(2, 4);

        int LOC12 = MQTTTools.LOC12;        

        System.out.println(" ".repeat(LOC12-msgID.length())      + msgID      + ": Message ID");              
        System.out.println(" ".repeat(LOC12-typeID.length())      + typeID      + ": Message Type(01-Gateway, 02-Module)");             

        if(typeID.equals(MQTTTools.INFO_TYPE_GATEWAY)){ //"01"

            String gatewayType = message.substring(4, 8);
            String gateFirmwareVer = message.substring(8, 16);
            String gatewayIPAddr = message.substring(16, 24);
            String subnetMask = message.substring(24, 32);
            String defaultIPAddr = message.substring(32, 40);
            String macAddr = message.substring(40, 52);

            System.out.println(" ".repeat(LOC12-gatewayType.length())      + gatewayType + ": Gateway Type: " + MQTTTools.hexToDec(gatewayType));            
            System.out.println(" ".repeat(LOC12-gateFirmwareVer.length())  + gateFirmwareVer + ": Gateway Firmware Version: " + MQTTTools.hexToDec(gateFirmwareVer));            
            System.out.println(" ".repeat(LOC12-gatewayIPAddr.length())    + gatewayIPAddr   + ": Gateway IP Addr: " + MQTTTools.hexToIP(gatewayIPAddr) + "");            
            System.out.println(" ".repeat(LOC12-subnetMask.length())       + subnetMask      + ": Gateway Subnet Mask: " + MQTTTools.hexToIP(subnetMask) + "");            
            System.out.println(" ".repeat(LOC12-defaultIPAddr.length())    + defaultIPAddr   + ": Gateway Default IP: " + MQTTTools.hexToIP(defaultIPAddr) + "");            
            System.out.println(" ".repeat(LOC12-macAddr.length())          + macAddr         + ": Gateway Mac Addr: " + MQTTTools.hexToMacAddr(macAddr) + "");            
         
        }else if(typeID.equals(MQTTTools.INFO_TYPE_MODULE)){ //"02"

            int offset = 4;
            int sectionLength = 14;

            System.out.println(" ".repeat(LOC12) + ": Addr|Module Firmware Version");
            System.out.print(" ".repeat(LOC12) + "  ");                 
           
            while (offset + sectionLength <= message.length()) {
                
                String section = message.substring(offset, offset + sectionLength);
                String rs485Addr = section.substring(0, 2);
                String moduleFirmwareVer = message.substring(2, 14);

                System.out.print("" + rs485Addr  + "|");            
                System.out.print("" + moduleFirmwareVer + " ");            

                offset += sectionLength;
            }

            System.out.println("");


        }       


    }
}