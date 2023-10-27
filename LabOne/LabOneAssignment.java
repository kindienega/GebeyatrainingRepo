import java.util.*;
import java.io.*;

public class LabOneAssignment {
    private static final String PRIVATE_CHAT_FILE = "C:\\Users\\kindie\\Desktop\\javaAss\\Privat.txt";
    private static final String PUBLIC_CHAT_FILE = "C:\\Users\\kindie\\Desktop\\javaAss\\Public.txt";
    private static final String FRIENDS_LIST_FILE = "C:\\Users\\kindie\\Desktop\\javaAss\\FriendList.txt";

    private List<String> privateChat = new ArrayList<>();
    private List<String> publicChat = new ArrayList<>();
    private List<String> friendsList = new ArrayList<>();

    public LabOneAssignment() {
        loadChatLogs();
        loadFriendsList();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Read Private Chat");
            System.out.println("2. Read Public Chat");
            System.out.println("3. Write to Private Chat");
            System.out.println("4. Write to Public Chat");
            System.out.println("5. View Friends List");
            System.out.println("6. Add Friend");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    displayChat(privateChat);
                    break;
                case 2:
                    displayChat(publicChat);
                    break;
                case 3:
                    writeChat(privateChat, "Private");
                    break;
                case 4:
                    writeChat(publicChat, "Public");
                    break;
                case 5:
                    viewFriendsList();
                    break;
                case 6:
                    addFriend();
                    break;
                case 7:
                    saveChatLogs();
                    saveFriendsList();
                    System.out.println("Exiting the chat application.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void loadChatLogs() {
        try {
            
            BufferedReader privateChatReader = new BufferedReader(new FileReader(PRIVATE_CHAT_FILE));
            String privateChatLine;
            while ((privateChatLine = privateChatReader.readLine()) != null) {
                privateChat.add(privateChatLine);
            }
            privateChatReader.close();
    
            
            BufferedReader publicChatReader = new BufferedReader(new FileReader(PUBLIC_CHAT_FILE));
            String publicChatLine;
            while ((publicChatLine = publicChatReader.readLine()) != null) {
                publicChat.add(publicChatLine);
            }
            publicChatReader.close();
        } catch (IOException e) {
            System.out.println("Error loading chat logs: " + e.getMessage());
        }
    }
    
    private void loadFriendsList() {
       
    }

    private void displayChat(List<String> chat) {
        for (String message : chat) {
            System.out.println(message);
        }
    }

    private void writeChat(List<String> chat, String chatType) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your message for the " + chatType + " Chat:");
        String message = scanner.nextLine();
        chat.add(message);
    }

    private void viewFriendsList() {
        System.out.println("Friends List:");
        for (String friend : friendsList) {
            System.out.println(friend);
        }
    }

    private void addFriend() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the friend to add:");
       String friendName = scanner.nextLine();
        friendsList.add(friendName);
        System.out.println(friendName + " has been added to your friends list.");
    }

    private void saveChatLogs() {

    }

    private void saveFriendsList() {

    }

    public static void main(String[] args) {
        LabOneAssignment chatApp = new LabOneAssignment();
        chatApp.start();
    }
}
