import ChatApp.Server.ChatServer;

import java.util.Scanner;

import ChatApp.Client.ChatApp;

public class App {
    public static void main(String[] args) {
        // Khởi động server trong một luồng riêng
        Thread serverThread = new Thread(() -> {
            ChatServer.main(args);
        });
        serverThread.start();

        System.out.println("Server started. Press Enter to start client...");

        // Vòng lặp để mở nhiều client khi người dùng nhấn Enter
        Scanner scanner = new Scanner(System.in);
        while (true) {
            scanner.nextLine(); // Đợi người dùng nhấn Enter

            // Khởi động client trong một luồng riêng
            Thread clientThread = new Thread(() -> {
                ChatApp.main(args);
            });
            clientThread.start();

            System.out.println("Client opened. Press Enter to opent new Client...");
        }
    }
}
