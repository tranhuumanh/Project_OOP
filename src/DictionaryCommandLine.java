import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
        System.out.println("[10] Search History");
        System.out.println("[11] Sound");
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

        for (int i = 0; i < n; i++) {
            System.out.print("Điền từ tiếng Anh: ");
            String englishWord = scanner.nextLine();

            System.out.print("Điền nghĩa tiếng Việt: ");
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
                    removeCommandLine();
                    break;
                case "3":
                    UpdateCommandLine();
                    break;
                case "4":
                    showAllWords();
                    break;
                case "5":
                    LookUpCommandLine();
                    break;
                case "6":
                    SearchCommandLine();
                    break;
                case "7":
                    GameCommandLine();
                    break;
                case "8":
                    ImportToFileCommandLine();
                    break;
                case "9":
                    ExportToFileCommandLine();
                    break;
                case "10":
                    HistoryCommandLine();
                case "11":
                    SoundCommandLine();

                default:
                    System.out.println("Không khả dụng yêu cầu nhập lại!");
            }
        }
    }


    public void LookUpCommandLine() { //Hàm tìm kiếm các từ tiếng Anh
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ cần tìm: ");
        String worded = sc.nextLine();
        for (Word word : Dictionary.words) {
            if ((word.getWord_target()).equals(worded)) {

                (Dictionary.SaveHistoryWord).add(word.getWord_target()); //Lưu lại lịch sử tìm kiếm

                System.out.println(" English    | Vietnamese");
                System.out.println("----------------------------");

                System.out.printf(" %-10s | %-10s%n", word.getWord_target(), word.getWord_explain());
                return;
            }
        }
        System.out.println("Không tìm thấy từ cần tìm");

    }

    public void SearchCommandLine() { //Hàm tìm kiếm các từ tiếng Anh có tiền tố là
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập các tiền tố cần tìm: ");
        String worded = sc.nextLine();

        System.out.println("No| English    | Vietnamese");
        System.out.println("----------------------------");

        int i = 1;
        for (Word word : Dictionary.words) {
            if (word.getWord_target().startsWith(worded)) {

                (Dictionary.SaveHistoryWord).add(word.getWord_target()); //Lưu lại lịch sử tìm kiếm

                System.out.printf("%d | %-10s | %-10s%n", i, word.getWord_target(), word.getWord_explain());
                i++;
            }
        }
        if (i == 1) {
            System.out.println("Không tìm thấy từ nào có tiền tố này");
        }

    }

    public void removeCommandLine() { // Xóa từ khỏi từ điển
        Scanner sc = new Scanner(System.in);
        String worded = sc.nextLine();
        for (Word word : Dictionary.words) {
            if ((word.getWord_target()).equals(worded)) {
                Dictionary.words.remove(word);
                System.out.println("Xóa thành công");
                return;
            }

        }
        System.out.println("Không tìm thấy từ cần xóa");
    }

    public void UpdateCommandLine() { // Sửa từ

        Scanner sc = new Scanner(System.in);
        System.out.print("Điền từ cần sửa: ");
        String update = sc.nextLine();

        for (Word word : Dictionary.words) {

            if ((word.getWord_target()).equals(update)) {

                System.out.println("[1] Sửa từ tiếng Anh");
                System.out.println("[2] Sửa nghĩa của từ tiếng Anh");
                System.out.print("Nhâp lựa chọn của bạn: ");

                int n = sc.nextInt();
                sc.nextLine();

                if (n == 1) {
                    System.out.print("Nhập từ tiếng Anh thay thế: ");
                    String re = sc.nextLine();
                    word.setWord_target(re);
                    return;
                } else if (n == 2) {
                    System.out.print("Nhập nghĩa của từ tiếng Anh cần sửa: ");
                    String re = sc.nextLine();
                    word.setWord_explain(re);
                    return;
                } else {
                    System.out.println("Lựa chọn không hợp lệ!");
                    return;
                }
            }
        }
    }

    public void ExportToFileCommandLine() { // Hàm nhập ArrayList words vào file Dictionary.txt
        try {
            if ((Dictionary.words).isEmpty()) {
                System.out.println("Không có dữ liệu để Export!");
                return;
            }

            File file = new File("Dictionary.txt");
            // Kiểm tra nếu file không tồn tại thì in ra thông báo
            if (!file.exists()) {
                System.out.println("File không tồn tại!");
            }

            // Tạo đối tượng FileWriter
            FileWriter input = new FileWriter(file);

            // Tạo đối tượng BufferedWriter để ghi dữ liệu hiệu quả hơn
            BufferedWriter bufferedWriter = new BufferedWriter(input);

            // Ghi từng phần tử của ArrayList vào file, mỗi phần tử trên một dòng
            for (Word word : Dictionary.words) {
                bufferedWriter.write(word.getWord_target() + " " + word.getWord_explain());
                bufferedWriter.newLine(); // Thêm dòng mới sau mỗi đối tượng
            }

            // Đóng các luồng
            bufferedWriter.close();
            input.close();

            System.out.println("Đã ghi vào file thành công.");

        }
        catch(IOException check) {
            System.out.println("LỖI HÀM EXPORT TO FILE");
        }
    }

    public void HistoryCommandLine() {
        if (Dictionary.SaveHistoryWord.isEmpty()) {
            System.out.println("Không có từ nào tìm kiếm gần đây");
            return;
        }
        System.out.println("Tìm kiếm gần đây");
        int n = Dictionary.SaveHistoryWord.size();
        int no = 1;
        for(int i = n-1 ; i >= 0 ; i--) {
            System.out.println(Dictionary.SaveHistoryWord.get(i));
            no++;
        }
        System.out.println("Số kết quả đã tìm kiếm là:" + no);
    }
    public static void main(String[] args) {
        DictionaryCommandLine dictionaryApp = new DictionaryCommandLine();
        dictionaryApp.dictionaryBasic();
    }

    public void SoundCommandLine() {

        System.out.print("Nhập từ tiếng Anh muốn nghe phát âm: ");
        Scanner sc = new Scanner(System.in);
        String worded = sc.nextLine();
        Sound.Vocal(worded);
        return;
    }

    public void ImportToFileCommandLine() {
        for (Word word : Dictionary.words) {


            //Đang build dở :))

        }

    }

    public void GameCommandLine() {
        // Cập nhật đường dẫn tới tệp từ vựng của bạn
        String filePath = "src/TestGame.txt";

        // Đọc danh sách từ vựng từ tệp
        List<String> words = NoiTuGame.readWordList(filePath);

        // Kiểm tra nếu đọc thành công
        if (words != null) {
            // Bắt đầu trò chơi
            NoiTuGame.playGame(words);
        } else {
            System.out.println("Không thể đọc danh sách từ.");
        }
    }






}
