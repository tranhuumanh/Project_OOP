import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryCommandLine {

    public void displayMenu() {
        System.out.println("Welcome to My Application!");
        System.out.println("[0] Exit");
        System.out.println("[1] Add");
        System.out.println("[2] Remove");
        System.out.println("[3] Update");
        System.out.println("[4] Display");
        System.out.println("[5] Lookup");
        System.out.println("[6] Search");
        System.out.println("[7] Game");
        System.out.println("[8] Import from file");
        System.out.println("[9] Export to file");
    }

    public void showAllWords() {
        System.out.println("No| English    | Vietnamese");
        System.out.println("----------------------------");

        for (int i = 0; i < (Dictionary.words).size(); i++) {
            Word word = Dictionary.words.get(i);
            System.out.printf("%d | %-10s | %-10s%n", i + 1, word.getWord_target(), word.getWord_explain());
        }
    }

    public void insertFromCommandLine() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số từ: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for(int i = 0 ; i < n ; i++) {
            System.out.print("Enter English word: ");
            String englishWord = scanner.nextLine();

            System.out.print("Enter Vietnamese meaning: ");
            String vietnameseMeaning = scanner.nextLine();

            Word newWord = new Word(englishWord, vietnameseMeaning);
            Dictionary.words.add(newWord);

        }
        System.out.println("Word added successfully!");
    }

    public void dictionaryBasic() {
        while (true) {
            displayMenu();
            System.out.print("Your action: ");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "0":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                case "1":
                    insertFromCommandLine();
                    break;
                case "2":
                    // Implement the Remove function
                    break;
                case "3":
                    // Implement the Update function
                    break;
                case "4":
                    showAllWords();
                    break;
                case "5":
                    // Implement the Lookup function
                    break;
                case "6":
                    // Implement the Search function
                    break;
                case "7":
                    // Implement the Game function
                    break;
                case "8":
                    // Implement the Import function
                    break;
                case "9":
                    // Implement the Export function
                    break;
                default:
                    System.out.println("Action not supported. Please enter a valid option.");
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCommandLine dictionaryApp = new DictionaryCommandLine();
        dictionaryApp.dictionaryBasic();
    }
}