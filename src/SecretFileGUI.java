import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SecretFileGUI extends JFrame implements ActionListener {

    // Instance fields for buttons and text fields
    private final JButton encodeButton;
    private final JButton decodeButton;
    private final JButton exitButton;
    private final JTextArea messageField;
    private final JButton selectFileButton;
    private final JTextArea encodeResultMsgField = new JTextArea();
    private final JTextArea codeResultMessageField = new JTextArea();

    public SecretFileGUI(GUI_Type guiType)
    {
        this.setLayout(new FlowLayout());

        // Assign buttons and add action listeners
        encodeButton = new JButton("Encode a new message to a file");
        encodeButton.addActionListener(this);

        decodeButton = new JButton("Decode a message from file");
        decodeButton.addActionListener(this);

        exitButton = new JButton("Exit");
        exitButton.addActionListener(this);

        messageField = new JTextArea("Enter message (Can only contain letters A-Z, periods, and spaces)");

        selectFileButton = new JButton("Select file");
        selectFileButton.addActionListener(this);

        encodeResultMsgField.setEditable(false);
        codeResultMessageField.setEditable(false);

        switch (guiType) // Add buttons/widgets to GUI based on selection
        {
            case MAIN ->
            {
                this.add(encodeButton);
                this.add(decodeButton);
                this.add(exitButton);
                this.pack();
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
            case TYPE_MESSAGE ->
            {
                this.setSize(500, 350);
                this.add(messageField);
                this.add(selectFileButton);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
            case ENCODE_RESULT ->
            {
                this.setSize(500, 350);
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            }
        }

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        SecretFileGUI result; // Result GUI

        if (e.getSource() == encodeButton) // Click encode button on main GUI
        {
            new SecretFileGUI(GUI_Type.TYPE_MESSAGE); // Open new GUI to allow user to type in a message and start selecting a file

        } else if (e.getSource() == decodeButton) // Click decode button on main GUI
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); // Default directory when choosing a file

            int response = fileChooser.showOpenDialog(null); // Select file to open

            if (response == JFileChooser.APPROVE_OPTION) // User has chosen their file
            {
                File chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());

                result = new SecretFileGUI(GUI_Type.ENCODE_RESULT); // Create a GUI that will show the decoded message

                if (!isTxtFile(chosenFile)) // If the file is not a .txt file, end the method here
                {
                    encodeResultMsgField.setText("File type is not supported. Please only select .txt files");
                    result.add(encodeResultMsgField);
                    return;
                }

                Integer[] messageCode = SecretCode.encode(SecretCodeWriter.decodeFromFile(chosenFile)); // Get the encoding of the message from the file
                String message = SecretCode.decode(messageCode); // Decode the message into a String

                // Add widgets to GUI to display the decoded message + the code of the message
                encodeResultMsgField.setText("Encoded message: " + message);
                result.add(encodeResultMsgField);
                JScrollPane scrollPane = new JScrollPane(encodeResultMsgField);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(250, 50));
                result.add(scrollPane);

            }
        } else if (e.getSource() == exitButton) // Click to terminate program at any time
        {
            this.dispose();
        } else if (e.getSource() == selectFileButton) // Click select file button on message type screen
        {
            String messageInput = messageField.getText();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(".")); // Default directory when choosing a file

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) // User has chosen a file to write the encoded the message to
            {
                File chosenFile = new File(fileChooser.getSelectedFile().getAbsolutePath());

                result = new SecretFileGUI(GUI_Type.ENCODE_RESULT);

                if (!isTxtFile(chosenFile)) // If the file is not a .txt file, end the method here
                { // Add widgets to result GUI to show incorrect file type
                    encodeResultMsgField.setText("File type is not supported. Please only select .txt files");
                    result.add(encodeResultMsgField);
                    return;
                }

                Integer[] messageCode = SecretCode.encode(messageInput); // Encode the message into an integer array
                SecretCodeWriter.encodeToFile(chosenFile, messageCode); // Write the encoded message to the chosen file

                // Add widgets to result GUI to show user message was encoded
                encodeResultMsgField.setText("Encoded message \"" + messageInput + "\" to file " + chosenFile.getName());
                result.add(encodeResultMsgField);
                JScrollPane scrollPane = new JScrollPane(encodeResultMsgField);
                scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                scrollPane.setPreferredSize(new Dimension(250, 50));
                result.add(scrollPane);
            }
        }
    }

    /**
     * Returns if the file is a .txt file
     * @param file The file to check
     * @return true if the file is a .txt, false if otherwise
     */
    private boolean isTxtFile(File file)
    {
        return file.getName().contains(".txt");
    }

    public enum GUI_Type
    {
        /**
         * The main menu for navigation
         */
        MAIN,

        /**
         * The menu used to type in a message to encode to a file that will be selected
         */
        TYPE_MESSAGE,

        /**
         * The menu used to display the result of encoding/decoding a message
         */
        ENCODE_RESULT
    }
}
