import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
            Scanner inputScanner = new Scanner(System.in);
            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Write to a file");
                System.out.println("2. Read from a file");
                System.out.println("3. Exit");
                System.out.print("Enter your choice (1/2/3): ");

                int choice = inputScanner.nextInt();
                inputScanner.nextLine();

                if (choice == 1) {
                    System.out.println("Enter the file name to write to:");
                    String fileName = inputScanner.nextLine();
                    System.out.println("Enter text to write to the file (or type 'exit' to quit writing):");

                    while (true) {
                        String input = inputScanner.nextLine();
                        if (input.equalsIgnoreCase("exit")) {
                            break;
                        }

                        writeToFile(fileName, input);
                    }
                } else if (choice == 2) {
                    System.out.println("Enter the file name to read from:");
                    String fileName = inputScanner.nextLine();
                    readFromFile(fileName);
                } else if (choice == 3) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
                }
            }

            inputScanner.close();
        }

        private static void writeToFile(String fileName, String content) {
            try {
                File file = createFile(fileName);
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter writer = new PrintWriter(fileWriter);
                writer.println(content);
                writer.flush();
                writer.close();
            } catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
                createErrorFile("error.log", e.getMessage());
            }
        }

        private static void readFromFile(String fileName) {
            File file = new File(fileName);
            if (file.exists()) {
                try {
                    Scanner scanner = new Scanner(file);
                    System.out.println("Contents of " + fileName + ":");
                    while (scanner.hasNext()) {
                        System.out.println(scanner.nextLine());
                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    createErrorFile("error.log", e.getMessage());
                }
            } else {
                System.out.println("File does not exist.");
                createErrorFile("error.log", "File not found: " + fileName);
            }
        }

        private static void createErrorFile(String path, String errorMessage) {
            try {
                File errorFile = new File(path);
                errorFile.createNewFile();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String errorEntry = "Error: " + errorMessage + ". Time: " + dateFormat.format(new Date());
                writeToFile(path, errorEntry);
            } catch (IOException e) {
                System.out.println("Error creating error file: " + e.getMessage());
            }
        }

        private static File createFile(String path) {
            try {
                File file = new File(path);
                if (!file.exists()) {
                    file.createNewFile();
                }
                return file;
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
                throw new IllegalStateException(e);
            }
        }
    }

