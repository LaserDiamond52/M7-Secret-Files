
public class SecretCode {

    /**
     * Encodes a message into an integer array
     * <p>Unsupported characters will be saved as a space (lowercase letters will be converted to uppercase)
     * <p>Supported characters:
     * <li>Letters A-Z</li>
     * <li>Space</li>
     * <li>Period</li>
     * @param message The message to encode
     * @return The encoded message as an integer array
     */
    public static Integer[] encode(String message)
    {
        message = message.toUpperCase(); // Set all letters in the message to uppercase
        int messageLength = message.length(); // Length of the message
        Integer[] messageCode = new Integer[messageLength]; // Create a new Integer Array to store the encoded message

        for (int i = 0; i < messageLength; i++) // Loop through each character in the message and add them to the messageCode array
        {
            char c = message.charAt(i);
            Integer charCode = SecretCodeWriter.letterEncodeMap.get(c);
            if (charCode == null || !SecretCodeWriter.letterEncodeMap.containsKey(c)) // Check if character is supported
            {
                charCode = 27; // Unsupported character is saved as a space
            }

            messageCode[i] = charCode; // Assign index "i" of the message code array to the char code
        }

        return messageCode;
    }

    /**
     * Decodes the message from the Integer Array to a String
     * @param messageCode The integer array encoding of the message to decode
     * @return The message stored in the file as a string in all uppercase
     */
    public static String decode(Integer[] messageCode)
    {
        StringBuilder messageBuilder = new StringBuilder(); // StringBuilder used to store/append to the message

        for (Integer charCode : messageCode) // Loop through the messageCode and append the character to messageBuilder
        {
            Character c = SecretCodeWriter.integerDecodeMap.get(charCode);
            if (c == null || !SecretCodeWriter.integerDecodeMap.containsKey(charCode)) // Check if char in file is supported
            {
                c = ' '; // char is not supported, it will be saved as a space
            }
            messageBuilder.append(c); // Append char to message
        }

        return messageBuilder.toString();
    }

}
