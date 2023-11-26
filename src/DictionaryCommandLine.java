import java.io.IOException;
import java.util.Collections;
import java.util.Scanner;
public class DictionaryCommandLine extends Dictionary {
    private static final String path = "Dictionary.txt";
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

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
        System.out.println("[10] Search History");
        System.out.println("[11] Sound");
        System.out.println("[12] Google Translate");
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
                    dictionaryManagement.addWord();
                    break;
                case "2":
                    dictionaryManagement.removeCommandLine();
                    break;
                case "3":
                    dictionaryManagement.UpdateCommandLine();
                    break;
                case "4":
                    dictionaryManagement.showAllWords();
                    break;
                case "5":
                    dictionaryManagement.LookUpCommandLine();
                    break;
                case "6":
                    dictionaryManagement.SearchCommandLine();
                    break;
                case "7":
                    dictionaryManagement.GameCommandLine();
                    break;
                case "8":
                    dictionaryManagement.ImportFromFileCommandLine();
                    break;
                case "9":
                    dictionaryManagement.ExportToFileCommandLine();
                    break;
                case "10":
                    dictionaryManagement.HistoryCommandLine();
                    break;
                case "11":
                    dictionaryManagement.SoundCommandLine();
                case "12":
                    try{
                        dictionaryManagement.Translate();
                    }
                    catch (IOException e){
                    }
                    break;


                default:
                    System.out.println("Không khả dụng yêu cầu nhập lại!");
            }
        }
    }
    public static void main(String[] args) {
        DictionaryCommandLine dictionaryApp = new DictionaryCommandLine();
        dictionaryApp.dictionaryManagement.ImportFromFileCommandLine();
        Collections.sort(words, (w1, w2) -> w1.getWord_target().compareTo(w2.getWord_target()));
        dictionaryApp.dictionaryBasic();
    }
}
