import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class DictionaryCommandLine {
    private List<Word> dictionary = new ArrayList<>();

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

    public void dictionaryAdvanced() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Your action: ");
            String input = scanner.nextLine();

            switch (input) {
                case "0":
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                case "1":
                    // Implement the Add function
                    break;
                case "2":
                    // Implement the Remove function
                    break;
                case "3":
                    // Implement the Update function
                    break;
                case "4":
                    // Implement the Display function
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
        dictionaryApp.dictionaryAdvanced();
    }
}
