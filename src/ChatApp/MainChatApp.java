package ChatApp;

import ChatApp.Server.ChatServer;
import ChatApp.Client.ChatApp;

import java.util.Scanner;

public class MainChatApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Start Server");
        System.out.println("2. Start Client");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                ChatServer.main(args);
                ;
                break;
            case 2:
                ChatApp.main(args);
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
        }
    }
}
