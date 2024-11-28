package ChatApp.Client;

import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatApp {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    private Socket socket;
    private PrintWriter writer;
    private JTextArea chatArea;
    private JTextField messageInput;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatApp().start());
    }

    public void start() {
        // Main frame
        JFrame frame = new JFrame("Chat Application");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main layout
        frame.setLayout(new BorderLayout());

        // Chat area
        JPanel chatPanel = new JPanel(new BorderLayout());
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);

        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);

        // Message input area
        JPanel inputPanel = new JPanel(new BorderLayout());
        messageInput = new JTextField();
        JButton sendButton = new JButton("Send");

        inputPanel.add(messageInput, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        // Add action listener for the send button
        sendButton.addActionListener(e -> sendMessage());

        // Add Enter key listener for the message input
        messageInput.addActionListener(e -> sendMessage());

        // Add components to the main fram
        frame.add(chatPanel, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        new Thread(this::connectToServer).start();

        frame.setVisible(true);
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String message;
            while ((message = reader.readLine()) != null) {
                chatArea.append("Phat: " + message + "\n");
            }
        } catch (IOException e) {
            chatArea.append("Error connecting to server: " + e.getMessage());
        }
    }

    private void sendMessage() {
        String message = messageInput.getText().trim();
        if (!message.isEmpty()) {
            writer.println(message);
            messageInput.setText("");
        }
    }
}
