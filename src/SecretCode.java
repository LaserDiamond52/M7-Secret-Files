import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SecretCode {

    private static final String FILE_DOES_NOT_EXIST_MSG = "File does not exist :(";

    /**
     * Encodes a new message and saves it to the SecretMessage.txt file. Anything saved to the file will be wiped and replaced with the encoded message
     * <p>Unsupported characters will be saved as a space
     * <p>Supported Characters:
     * <li>
     * Letters A-Z (Saved as uppercase. lowercase letters will automatically be saved as uppercase)
     * </li>
     * <li>
     * Space
     * </li>
     * <li>
     * Period
     * </li>
     * @param file The file to encode the message into
     * @param message The message to save to the SecretMessage.txt file
     */
    public static void encode(File file, String message)
    {
        message = message.toUpperCase(); // Set all letters in message to upper-case
        int messageLength = message.length();

        if (!file.exists()) // Check if file exists
        {
            System.out.println(FILE_DOES_NOT_EXIST_MSG);
            return;
        }
        try {
            FileWriter messageWriter = new FileWriter(file, false);
            messageWriter.flush();

            messageWriter.write(messageLength + "\n"); // Write message length to the first line of the file, then start a new line

            for (int i = 0; i < messageLength; i++)
            {
                char letter = message.charAt(i);
                Integer letterMapValue = letterEncodeMap.get(letter);
                if (letterMapValue == null || !letterEncodeMap.containsKey(letter)) // If there is no char stored for the char in the string
                {
                    letterMapValue = 27; // Set the char to a space in the encoder
                }
                //messageIntList.add(i, letterMapValue);
                //messageIntArray[i] = letterMapValue;

                messageWriter.write(letterMapValue + " ");
            }

            messageWriter.close();
        } catch (IOException e)
        {
            System.out.println("ERROR WHILE WRITING MESSAGE TO FILE");
            System.out.println(" ");
            e.printStackTrace();
        }
    }

    /**
     * Decodes and returns the message stored in the file
     * @param file The file to get the message from
     * @return The message stored in the file
     */
    public static String decode(File file)
    {
        String message = "";
        if (!file.exists()) // Check if file exists
        {
            System.out.println(FILE_DOES_NOT_EXIST_MSG);
            return message;
        }
        try {

            Scanner savedMessageScanner = new Scanner(file);
            StringBuilder messageBuilder = new StringBuilder();
            int i = 0;
            while (savedMessageScanner.hasNext())
            {
                String intLetterKeyStr = savedMessageScanner.next();
                i++;
                if (i == 1) // Skip over first saved int (as that is the length of the message)
                {
                    continue;
                }
                try {
                    int intLetterKey = Integer.parseInt(intLetterKeyStr);
                    Character letter = integerDecodeMap.get(intLetterKey);
                    if (letter != null && integerDecodeMap.containsKey(intLetterKey))
                    {
                        messageBuilder.append(letter);
                    }
                } catch (NumberFormatException e)
                {
                    System.out.println("ERROR READING CODE FROM FILE");
                    e.printStackTrace();
                }

            }

            savedMessageScanner.close();
            message = messageBuilder.toString();

        } catch (IOException e)
        {
            System.out.println("ERROR TRYING TO READ FROM FILE");
            e.printStackTrace();
        }

        return message;
    }

    /**
     * Gets the code that the message is stored as.
     * @param message The message encoded in the file (as a String)
     * @return an int array of the message code
     */
    public static Integer[] getMessageCode(String message)
    {
        message = message.toUpperCase();
        int messageLength = message.length();
        Integer[] messageIntArray = new Integer[messageLength];

        for (int i = 0; i < messageIntArray.length; i++)
        {
            Character letter = message.charAt(i);
            messageIntArray[i] = letterEncodeMap.get(letter);
            if (messageIntArray[i] == null || !letterEncodeMap.containsKey(letter))
            {
                messageIntArray[i] = 27;
            }
        }

        return messageIntArray;
    }

    /**
     * Gets the message code that the message is stored as as a String variable
     * @param message The message to get the code of
     * @return The code of the message as a string
     */
    public static String getMessageCodeString(String message)
    {
        message = message.toUpperCase();
        StringBuilder encodeString = new StringBuilder("Message Code: ");
        Integer[] encodedMessage = SecretCode.getMessageCode(message);
        for (int k : encodedMessage) {
            encodeString.append(k).append(" ");
        }
        return encodeString.toString();
    }

    /**
     * HashMap that contains Character-Integer mappings for secret messages
     * <p>Contains all uppercase letters (no lowercase), space, and period
     */
    private static final HashMap<Character, Integer> letterEncodeMap = new HashMap<>();
    static
    {
        letterEncodeMap.put('A', 1);
        letterEncodeMap.put('B', 2);
        letterEncodeMap.put('C', 3);
        letterEncodeMap.put('D', 4);
        letterEncodeMap.put('E', 5);
        letterEncodeMap.put('F', 6);
        letterEncodeMap.put('G', 7);
        letterEncodeMap.put('H', 8);
        letterEncodeMap.put('I', 9);
        letterEncodeMap.put('J', 10);
        letterEncodeMap.put('K', 11);
        letterEncodeMap.put('L', 12);
        letterEncodeMap.put('M', 13);
        letterEncodeMap.put('N', 14);
        letterEncodeMap.put('O', 15);
        letterEncodeMap.put('P', 16);
        letterEncodeMap.put('Q', 17);
        letterEncodeMap.put('R', 18);
        letterEncodeMap.put('S', 19);
        letterEncodeMap.put('T', 20);
        letterEncodeMap.put('U', 21);
        letterEncodeMap.put('V', 22);
        letterEncodeMap.put('W', 23);
        letterEncodeMap.put('X', 24);
        letterEncodeMap.put('Y', 25);
        letterEncodeMap.put('Z', 26);
        letterEncodeMap.put(' ', 27);
        letterEncodeMap.put('.', 28);
    }

    /**
     * HashMap that contains Integer-Character mappings for secret messages
     * <p>Contains all uppercase letters (no lowercase), space, and period
     */
    private static final HashMap<Integer, Character> integerDecodeMap = new HashMap<>();
    static
    {
        integerDecodeMap.put(1, 'A');
        integerDecodeMap.put(2, 'B');
        integerDecodeMap.put(3, 'C');
        integerDecodeMap.put(4, 'D');
        integerDecodeMap.put(5, 'E');
        integerDecodeMap.put(6, 'F');
        integerDecodeMap.put(7, 'G');
        integerDecodeMap.put(8, 'H');
        integerDecodeMap.put(9, 'I');
        integerDecodeMap.put(10, 'J');
        integerDecodeMap.put(11, 'K');
        integerDecodeMap.put(12, 'L');
        integerDecodeMap.put(13, 'M');
        integerDecodeMap.put(14, 'N');
        integerDecodeMap.put(15, 'O');
        integerDecodeMap.put(16, 'P');
        integerDecodeMap.put(17, 'Q');
        integerDecodeMap.put(18, 'R');
        integerDecodeMap.put(19, 'S');
        integerDecodeMap.put(20, 'T');
        integerDecodeMap.put(21, 'U');
        integerDecodeMap.put(22, 'V');
        integerDecodeMap.put(23, 'W');
        integerDecodeMap.put(24, 'X');
        integerDecodeMap.put(25, 'Y');
        integerDecodeMap.put(26, 'Z');
        integerDecodeMap.put(27, ' ');
        integerDecodeMap.put(28, '.');
    }
}
