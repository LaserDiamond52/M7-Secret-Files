import java.util.HashMap;

public class SecretCode {

    public static void encode(String message)
    {
        message = message.toUpperCase(); // Set all letters in message to upper-case

        for (int i = 0; i < message.length(); i++)
        {
            char letter = message.charAt(i);
            Integer letterMapValue = letterEncodeMap.get(letter);
            if (letterMapValue == null) // If there is no char stored for the char in the string
            {
                letterMapValue = 27; // Set the char to a space in the encoder
            }
        }
    }

    public static void decode()
    {

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
}
