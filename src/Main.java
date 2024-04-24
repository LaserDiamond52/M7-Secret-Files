import java.io.File;
import java.io.IOException;

public class Main {

    public static final String FILE_NAME = "SecretMessage.txt";
    private static final File SECRET_CODE_FILE = new File(FILE_NAME);

    public static void main(String[] args) {

        // Create a file within the project to store a secret message
        // This is just for quick access to test everything
        if (!SECRET_CODE_FILE.exists())
        {
            System.out.println("The file for storing/reading a secret message does not exist");
            System.out.println("Creating file...");
            try {
                if (SECRET_CODE_FILE.createNewFile())
                {
                    System.out.println("File for secret messages created!");
                } else
                {
                    System.out.println("File for secret messages already exists");
                }
            } catch (IOException e)
            {
                System.out.println("There was an error accessing the file! :(");
                e.printStackTrace();
            }
        }

        new SecretFileGUI(SecretFileGUI.GUI_Type.MAIN);
    }
}