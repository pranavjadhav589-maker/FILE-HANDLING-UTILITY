import java.io.*;
import java.util.*;

public class FileHandlingUtility {

    static String fileName = "Example.txt";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== FILE HANDLING MENU =====");
            System.out.println("1. Create File");
            System.out.println("2. Write to File");
            System.out.println("3. Read File");
            System.out.println("4. Modify File");
            System.out.println("5. Show File Info");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            try {
                switch (choice) {
                    case 1:
                        createFile();
                        break;
                    case 2:
                        writeFile();
                        break;
                    case 3:
                        readFile();
                        break;
                    case 4:
                        modifyFile();
                        break;
                    case 5:
                        showFileInfo();
                        break;
                    case 6:
                        System.out.println("Exiting program...");
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Create file
    public static void createFile() throws IOException {
        File file = new File(fileName);

        if (file.createNewFile()) {
            System.out.println("File created: " + file.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    // Write or append to file
    public static void writeFile() throws IOException {
        System.out.print("Enter text to write: ");
        String input = sc.nextLine();

        System.out.print("Append mode? (yes/no): ");
        String mode = sc.nextLine();

        boolean append = mode.equalsIgnoreCase("yes");

        FileWriter writer = new FileWriter(fileName, append);
        writer.write(input + "\n");
        writer.close();

        System.out.println("Data written successfully.");
    }

    // Read file
    public static void readFile() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        System.out.println("\n===== FILE CONTENT =====");

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        reader.close();
    }

    // Modify file
    public static void modifyFile() throws IOException {
        File file = new File(fileName);

        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();

        System.out.print("Enter word to replace: ");
        String oldWord = sc.nextLine();

        System.out.print("Enter new word: ");
        String newWord = sc.nextLine();

        String originalContent = content.toString();

        // Check if word exists
        if (!originalContent.contains(oldWord)) {
            System.out.println("Error: Word not found in file.");
            return;
        }

        String modifiedContent = originalContent.replace(oldWord, newWord);

        FileWriter writer = new FileWriter(file);
        writer.write(modifiedContent);
        writer.close();

        System.out.println("File modified successfully.");
    }

    // Show file info
    public static void showFileInfo() {
        File file = new File(fileName);

        if (file.exists()) {
            System.out.println("\n===== FILE INFO =====");
            System.out.println("File Name: " + file.getName());
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Size: " + file.length() + " bytes");
        } else {
            System.out.println("File does not exist.");
        }
    }
}