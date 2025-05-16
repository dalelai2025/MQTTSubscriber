
package dig.tool;

// Created: 2025.03.18, Author: Dale
// Description: Provide public functions

public class MQTTTools {

    public static final int LOC12 = 12;
    public static final String MSGID_HBEAT1      = "CB"; //when module startup
    public static final String MSGID_HBEAT2      = "CC";
    public static final String MSGID_TAG_STAT    = "BB";
    public static final String MSGID_TEMP_HUM1   = "01";
    public static final String MSGID_TEMP_HUM2   = "02";
    public static final String MSGID_TEMP_HUM3   = "03";
    public static final String MSGID_TEMP_HUM4   = "04";
    public static final String MSGID_TEMP_HUM5   = "05";

    public static final String MSGID_DOOR_STAT   = "BA";
    public static final String MSGID_COLOUR_STAT = "AA";
    public static final String CMD_CODE = "E1";
    

    public static final String MSGID_INFO        = "EF";
    public static final String INFO_TYPE_GATEWAY = "01";
    public static final String INFO_TYPE_MODULE  = "02";


    // Method to convert hex string to decimal value and return as a string
    public static String hexToDec(String hexValue) {

        // Convert the hex string to a decimal (integer)
        //long decimalValue = hexToUnsignedDecimal(hexValue);
   
        // Return the decimal value as a string
        //return String.valueOf(decimalValue);
        return hexValue;

    }

        

    // Method to convert 4-byte hex string to IP address
    public static String hexToIP(String hex) {
        // Check if the hex string has exactly 8 characters
        if (hex.length() != 8) {
            throw new IllegalArgumentException("Hex string must be 8 characters long.");
        }

        // Split the hex string into 4 pairs of 2 characters
        String[] bytes = new String[4];
        bytes[0] = hex.substring(0, 2);
        bytes[1] = hex.substring(2, 4);
        bytes[2] = hex.substring(4, 6);
        bytes[3] = hex.substring(6, 8);

        // Convert each hex pair to decimal and create the IP address
        StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int decimalValue = Integer.parseInt(bytes[i], 16); // Convert hex to decimal
            ipAddress.append(decimalValue);
            if (i < 3) {
                ipAddress.append("."); // Add dot separator for each byte except the last one
            }
        }

        return ipAddress.toString(); // Return the IP address as a string
    }


     // Method to convert a 6-byte hex string to MAC address string format
    public static String hexToMacAddr(String hex) {
        // Check if the hex string has exactly 12 characters (6 bytes)
        if (hex.length() != 12) {
            throw new IllegalArgumentException("Hex string must be exactly 12 characters long (6 bytes).");
        }

        // Create a StringBuilder to construct the MAC address string
        StringBuilder macAddress = new StringBuilder();

        // Loop through the hex string in steps of 2 characters (1 byte)
        for (int i = 0; i < hex.length(); i += 2) {
            // Get the next 2 characters (1 byte in hex)
            String byteHex = hex.substring(i, i + 2);

            // Append the byte to the MAC address with a colon
            if (i > 0) {
                macAddress.append(":");
            }
            macAddress.append(byteHex);
        }

        // Return the resulting MAC address string
        return macAddress.toString();
    }   

    
}
