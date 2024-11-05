import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class SecretCodeWriter {

    private static final String FILE_DOES_NOT_EXIST_MSG = "File does not exist :(";

    /**
     * Writes the message code to a file. Anything previously in the file will be wiped
     * @param file The file to write to
     * @param messageCode The message code that the file will store
     */
    public static void encodeToFile(File file, Integer[] messageCode)
    {
        if (!file.exists()) // Check if file to write to exists
        {
            System.out.println(FILE_DOES_NOT_EXIST_MSG);
            return; // Does not exist, end method
        }

        try {
            FileWriter messageWriter = new FileWriter(file, false); // messageWriter will write messageCode to the file
            messageWriter.flush(); // Wipe everything on the file

            messageWriter.write(messageCode.length + "\n"); // Write the length of messageCode to the first line of the file, then move to the next line

            for (Integer charCode : messageCode)
            {
                messageWriter.write(charCode + " "); // Write the charCode followed by a space to the file
            }

            messageWriter.close(); // End the messageWriter
        } catch (IOException e)
        {
            System.out.println("ERROR WHILE WRITING MESSAGE TO FILE");
            e.printStackTrace();
        }
    }

    /**
     * Decodes and directly returns the message stored in the file
     * @param file The file to get the message from
     * @return The message stored in the file in all uppercase
     */
    public static String decodeFromFile(File file)
    {
        String message = "";
        if (!file.exists()) // Check if file exists
        {
            System.out.println(FILE_DOES_NOT_EXIST_MSG);
            return message; // File does not exist, end method here
        }
        try {

            Scanner savedMessageScanner = new Scanner(file); // Gather data saved to file
            StringBuilder messageBuilder = new StringBuilder(); // StringBuilder used to build the message stored in the file
            int i = 0;
            while (savedMessageScanner.hasNext()) // Use while loop to loop through and gather data in file
            {
                String intLetterKeyStr = savedMessageScanner.next();
                i++;
                if (i == 1) // Skip over first saved integer (as that is the length of the message)
                {
                    continue;
                }
                try { // Attempt to get integers stored in file from String and convert them to an int
                    int intCharKey = Integer.parseInt(intLetterKeyStr);
                    Character c = getKeyByValue(intCharKey);
                    if (c != null) // If there is a code stored for the letter the number is mapped to, append the letter to the messageBuilder
                    {
                        messageBuilder.append(c);
                    } else // Otherwise, append a space
                    {
                        messageBuilder.append(" ");
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
     * HashMap that contains Character-Integer mappings for secret messages
     * <p>Contains all uppercase letters (no lowercase), space, and period
     */
    public static final HashMap<Character, Integer> letterEncodeMap = new HashMap<>();
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
     * Gets the character mapped to the integer value in the letterEncodeMap when passed through
     * @param value The integer value to pass in
     * @return The character value mapped to it. Returns null if no character is mapped to the value entered.
     */
    public static Character getKeyByValue(Integer value)
    {
        for (Map.Entry<Character, Integer> entry : letterEncodeMap.entrySet())
        {
            if (Objects.equals(value, entry.getValue()))
            {
                return entry.getKey();
            }
        }
        return null;
    }

    public static <T, E> T getKeyByValue(Map<T, E> map, E value)
    {
        for (Map.Entry<T, E> entry : map.entrySet())
        {
            if (Objects.equals(value, entry.getValue()))
            {
                return entry.getKey();
            }
        }
        return null;
    }

//    /**
//     * HashMap that contains Integer-Character mappings for secret messages
//     * <p>Contains all uppercase letters (no lowercase), space, and period
//     */
//    public static final HashMap<Integer, Character> integerDecodeMap = new HashMap<>();
//    static
//    {
//        integerDecodeMap.put(1, 'A');
//        integerDecodeMap.put(2, 'B');
//        integerDecodeMap.put(3, 'C');
//        integerDecodeMap.put(4, 'D');
//        integerDecodeMap.put(5, 'E');
//        integerDecodeMap.put(6, 'F');
//        integerDecodeMap.put(7, 'G');
//        integerDecodeMap.put(8, 'H');
//        integerDecodeMap.put(9, 'I');
//        integerDecodeMap.put(10, 'J');
//        integerDecodeMap.put(11, 'K');
//        integerDecodeMap.put(12, 'L');
//        integerDecodeMap.put(13, 'M');
//        integerDecodeMap.put(14, 'N');
//        integerDecodeMap.put(15, 'O');
//        integerDecodeMap.put(16, 'P');
//        integerDecodeMap.put(17, 'Q');
//        integerDecodeMap.put(18, 'R');
//        integerDecodeMap.put(19, 'S');
//        integerDecodeMap.put(20, 'T');
//        integerDecodeMap.put(21, 'U');
//        integerDecodeMap.put(22, 'V');
//        integerDecodeMap.put(23, 'W');
//        integerDecodeMap.put(24, 'X');
//        integerDecodeMap.put(25, 'Y');
//        integerDecodeMap.put(26, 'Z');
//        integerDecodeMap.put(27, ' ');
//        integerDecodeMap.put(28, '.');
//    }
}
