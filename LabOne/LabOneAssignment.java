import java.util.*;
import java.io.*;

public class LabOneAssignment {
    private static final String PRIVATE_CHAT_FILE = "Private.txt";
    private static final String PUBLIC_CHAT_FILE = "Public.txt";
    private static final String FRIENDS_LIST_FILE = "FriendList.txt";

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
            displayOptions();
            
            if (scanner.hasNextInt()) {
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
                writeChat(privateChat, PRIVATE_CHAT_FILE);
                break;
            case 4:
                writeChat(publicChat, PUBLIC_CHAT_FILE);
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
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // clear invalid input
            }
        }
    }

    private void displayOptions() {
        System.out.println("Select an option:");
        System.out.println("1. Read Private Chat");
        System.out.println("2. Read Public Chat");
        System.out.println("3. Write to Private Chat");
        System.out.println("4. Write to Public Chat");
        System.out.println("5. View Friends List");
        System.out.println("6. Add Friend");
        System.out.println("7. Exit");
    }
    private void loadChatLogs() {
        loadChatLogFromFile(PRIVATE_CHAT_FILE, privateChat);
        loadChatLogFromFile(PUBLIC_CHAT_FILE, publicChat);
    }

    private void loadChatLogFromFile(String file, List<String> chat) {
        try (BufferedReader chatReader = new BufferedReader(new FileReader(file))) {
            String chatLine;
            while ((chatLine = chatReader.readLine()) != null) {
                chat.add(chatLine);
            }
        } catch (IOException e) {
            System.out.println("Error loading " + file + ": " + e.getMessage());
        }
    }

    private void loadFriendsList() {
        try (BufferedReader friendsReader = new BufferedReader(new FileReader(FRIENDS_LIST_FILE))) {
            String friend;
            while ((friend = friendsReader.readLine()) != null) {
                friendsList.add(friend);
            }
        } catch (IOException e) {
            System.out.println("Error loading Friends List: " + e.getMessage());
        }
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
        saveChatLogToFile(PRIVATE_CHAT_FILE, privateChat);
        saveChatLogToFile(PUBLIC_CHAT_FILE, publicChat);
    }

    private void saveChatLogToFile(String file, List<String> chat) {
        try (BufferedWriter chatWriter = new BufferedWriter(new FileWriter(file))) {
            for (String message : chat) {
                chatWriter.write(message);
                chatWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving " + file + ": " + e.getMessage());
        }
    }

    private void saveFriendsList() {
        try (BufferedWriter friendsWriter = new BufferedWriter(new FileWriter(FRIENDS_LIST_FILE))) {
            for (String friend : friendsList) {
                friendsWriter.write(friend);
                friendsWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving Friends List: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        LabOneAssignment chatApp = new LabOneAssignment();
        chatApp.start();
    }
}
