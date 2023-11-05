import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class ChatApplication {
    private static final String PRIVATE_CHAT_FILE = "Donut[AFK].log";
    private static final String PUBLIC_CHAT_FILE = "Eukarte.log";
    private static final String FRIENDS_LIST_FILE = "friends.list";

    private List<String> privateChatMessages;
    private List<String> publicChatMessages;
    private Map<String, String> friendsList;

    public ChatApplication() {
        privateChatMessages = new ArrayList<>();
        publicChatMessages = new ArrayList<>();
        friendsList = new HashMap<>();
    }

    public void readPrivateChatFile() {
        try (Scanner scanner = new Scanner(new File(PRIVATE_CHAT_FILE))) {
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                privateChatMessages.add(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePrivateChatFile(String message) {
        try (FileWriter writer = new FileWriter(PRIVATE_CHAT_FILE, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readPublicChatFile() {
        try (Scanner scanner = new Scanner(new File(PUBLIC_CHAT_FILE))) {
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                publicChatMessages.add(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePublicChatFile(String message) {
        try (FileWriter writer = new FileWriter(PUBLIC_CHAT_FILE, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFriendsListFile() {
        try (Scanner scanner = new Scanner(new File(FRIENDS_LIST_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0];
                    String fullName = parts[1];
                    friendsList.put(username, fullName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToFriendsList(String username, String fullName) {
        friendsList.put(username, fullName);
        try (FileWriter writer = new FileWriter(FRIENDS_LIST_FILE, true)) {
            writer.write(username + "," + fullName + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printPrivateChat() {
        System.out.println("Private Chat Messages:");
        for (String message : privateChatMessages) {
            System.out.println(message);
        }
    }

    public void printPublicChat() {
        System.out.println("Public Chat Messages:");
        for (String message : publicChatMessages) {
            System.out.println(message);
        }
    }

    public void printFriendsList() {
        System.out.println("Friends List:");
        for (Map.Entry<String, String> entry : friendsList.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        ChatApplication chatApp = new ChatApplication();
        chatApp.readPrivateChatFile();
        chatApp.readPublicChatFile();
        chatApp.readFriendsListFile();

        chatApp.runChatApplication();
    }

    public void runChatApplication() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Send private chat message");
            System.out.println("2. Send public chat message");
            System.out.println("3. Add friend");
            System.out.println("4. View private chat messages");
            System.out.println("5. View public chat messages");
            System.out.println("6. View friends list");
            System.out.println("7. Exit");
            System.out.print("Enter your choice (1-7): ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter private chat message: ");
                    String privateMessage = scanner.nextLine();
                    writePrivateChatFile(privateMessage);
                    break;
                case 2:
                    System.out.print("Enter public chat message: ");
                    String publicMessage = scanner.nextLine();
                    writePublicChatFile(publicMessage);
                    break;
                case 3:
                    System.out.print("Enter friend's username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter friend's full name: ");
                    String fullName = scanner.nextLine();
                    addToFriendsList(username, fullName);
                    break;
                case 4:
                    printPrivateChat();
                    break;
                case 5:
                    printPublicChat();
                    break;
                case 6:
                    printFriendsList();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
        }
    } 
}
