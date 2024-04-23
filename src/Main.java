import java.util.Scanner;

public class Main {

    private static final String ENCODE_KEY_WORD = "encode";
    private static final String DECODE_KEY_WORD = "decode";
    private static final String QUIT_KEY_WORD = "quit";

    public static void main(String[] args) {

        while (true)
        {
            displayIntro();

            Scanner inputScanner = new Scanner(System.in);
            String input = inputScanner.nextLine();

            if (input.equalsIgnoreCase(ENCODE_KEY_WORD))
            {
                System.out.println("Please type the message you'd like to store below");
                Scanner messageScanner = new Scanner(System.in);

                String message = messageScanner.nextLine();
                System.out.println("Encoding message...");
                SecretCode.encode(message);

            } else if (input.equalsIgnoreCase(DECODE_KEY_WORD))
            {
                System.out.println("decode stored message");
            } else if (input.equalsIgnoreCase(QUIT_KEY_WORD))
            {
                System.out.println("Exiting program. Goodbye!");
                break;
            } else
            {
                System.out.println("Not a valid input option.");
                System.out.println("Please choose a valid input option");

            }
            System.out.println(" ");
        }
    }

    private static void displayIntro()
    {
        System.out.println("Welcome! Please type an option below to continue:");
        System.out.println(" ");
        System.out.println("Encode new message (type: " + ENCODE_KEY_WORD + ")");
        System.out.println("Decode a current message (type: " + DECODE_KEY_WORD + ")");
        System.out.println("Quit the program (type: " + QUIT_KEY_WORD + ")");
    }
}