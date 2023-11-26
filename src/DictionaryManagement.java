import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement extends Dictionary {

    private static final String path = "Dictionary.txt";

    public void showAllWords() {
        System.out.println("No| English    | Vietnamese");
        System.out.println("----------------------------");

        for (int i = 0; i < (Dictionary.words).size(); i++) {
            Word word = Dictionary.words.get(i);
            System.out.printf("%d | %-10s | %-10s%n", i + 1, word.getWord_target(), word.getWord_explain());
        }
    }
    public void addWord() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số từ: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Collections.sort(Dictionary.words, (w1, w2) -> w1.getWord_target().compareTo(w2.getWord_target()));

        for (int i = 0; i < n; i++) {
            System.out.print("Điền từ tiếng Anh: ");
            String englishWord = scanner.nextLine();

            System.out.print("Điền nghĩa tiếng Việt: ");
            String vietnameseMeaning = scanner.nextLine();

            Word newWord = new Word(englishWord, vietnameseMeaning);
            int insertionIndex = binarySearch(Dictionary.words, englishWord);

            if (insertionIndex < 0) {
                // Chuyển đổi thành index chưa âm để chèn
                insertionIndex = -(insertionIndex + 1);
            }

            Dictionary.words.add(insertionIndex, newWord);
        }
        updateWordToFile();
        System.out.println("Word added successfully!");
    }
    public void LookUpCommandLine() { //Hàm tìm kiếm các từ tiếng Anh
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ cần tìm: ");
        String worded = sc.nextLine();

        Collections.sort(Dictionary.words, (w1, w2) -> w1.getWord_target().compareTo(w2.getWord_target()));

        int index = binarySearch(Dictionary.words, worded);

        if (index >= 0 && index < Dictionary.words.size() && Dictionary.words.get(index).getWord_target().equals(worded)) {
            Word word = Dictionary.words.get(index);

            // Lưu lịch sử tìm kiếm
            Dictionary.SaveHistoryWord.add(word.getWord_target());

            System.out.println(" English    | Vietnamese");
            System.out.println("----------------------------");
            System.out.printf(" %-10s | %-10s%n", word.getWord_target(), word.getWord_explain());
        } else {
            System.out.println("Không tìm thấy từ cần tìm");
        }
    }
    public void SearchCommandLine() { //Hàm tìm kiếm các từ tiếng Anh có tiền tố là
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập các tiền tố cần tìm: ");
        String worded = sc.nextLine();

        System.out.println("No| English    | Vietnamese");
        System.out.println("----------------------------");

        int i = 1;
        int index = binarySearch(words, worded);

        while (index < words.size() && words.get(index).getWord_target().startsWith(worded)) {
            Word word = words.get(index);
            SaveHistoryWord.add(word.getWord_target());

            System.out.printf("%d | %-10s | %-10s%n", i, word.getWord_target(), word.getWord_explain());
            i++;
            index++;
        }

        if (i == 1) {
            System.out.println("Không tìm thấy từ nào có tiền tố này");
        }

    }
    public void removeCommandLine() { // Xóa từ khỏi từ điển
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập từ cần xóa: ");
        String worded = sc.nextLine();

        int index = binarySearch(words, worded);
        if (index != -1 && index < words.size() && words.get(index).getWord_target().equals(worded)) {
            words.remove(index);
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không tìm thấy từ cần xóa");
        }
        updateWordToFile();
    }
    public void UpdateCommandLine() { // Sửa từ

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập từ cần sửa: ");
        String update = sc.nextLine();

        int index = binarySearch(words, update);
        if (index != -1 && index < words.size() && words.get(index).getWord_target().equals(update)) {
            Word word = words.get(index);

            System.out.println("[1] Sửa từ tiếng Anh");
            System.out.println("[2] Sửa nghĩa của từ tiếng Anh");
            System.out.print("Nhập lựa chọn của bạn: ");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.print("Nhập từ tiếng Anh thay thế: ");
                String replacement = sc.nextLine();
                word.setWord_target(replacement);
                System.out.println("Sửa từ thành công!");
            } else if (choice == 2) {
                System.out.print("Nhập nghĩa mới của từ tiếng Anh: ");
                String replacement = sc.nextLine();
                word.setWord_explain(replacement);
                System.out.println("Sửa nghĩa thành công!");
            } else {
                System.out.println("Lựa chọn không hợp lệ!");
            }
        } else {
            System.out.println("Không tìm thấy từ cần sửa");
        }
        updateWordToFile();
    }
    public void ExportToFileCommandLine() { // Hàm nhập ArrayList words vào file Dictionary.txt
        try {
            if (Dictionary.words.isEmpty()) {
                System.out.println("Không có dữ liệu để Export!");
                return;
            }

            File file = new File("Dictionary.txt");
            if (!file.exists()) {
                System.out.println("File không tồn tại!");
            }

            ArrayList<String> wordEntries = new ArrayList<>();

            for (Word word : Dictionary.words) {
                String entry = word.getWord_target() + " " + word.getWord_explain();
                wordEntries.add(entry);
            }
            Collections.sort(wordEntries);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (String entry : wordEntries) {
                bufferedWriter.write(entry);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            fileWriter.close();

            System.out.println("Đã ghi vào file thành công.");

        } catch (IOException e) {
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

    public void SoundCommandLine() {

        System.out.print("Nhập từ tiếng Anh muốn nghe phát âm: ");
        Scanner sc = new Scanner(System.in);
        String worded = sc.nextLine();
        Sound.Vocal(worded);
        return;
    }

    public void ImportFromFileCommandLine() {
        try {
            File file = new File(path);

            if (!file.exists()) {
                System.out.println("File không tồn tại!");
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.trim().split("\\s+", 2);

                if (parts.length == 2) {
                    String wordTarget = parts[0];
                    String wordExplain = parts[1];
                    Dictionary.words.add(new Word(wordTarget, wordExplain));
                } else {
                    System.out.println("Lỗi ở từ: " + line);
                }
            }

            bufferedReader.close();
            fileReader.close();

            System.out.println("Đã đọc từ file thành công.");

        } catch (IOException e) {
            System.out.println("LỖI HÀM IMPORT FROM FILE");
        }
    }
    public static void updateWordToFile() {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Word word : words) {
                bufferedWriter.write(word.getWord_target() + " " + word.getWord_explain() + "\n");
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GameCommandLine() {
        System.out.println("[1] Nối Từ");
        System.out.println("[2] Đố Vui");
        Scanner scanner = new Scanner(System.in);
        int choiceGame = scanner.nextInt();
        scanner.nextLine();

        if(choiceGame == 1){
            String filePath = "10000Wordsforgame.txt";

            // Đọc danh sách từ vựng từ tệp
            List<String> words = NoiTuGame.readWordList(filePath);

            // Kiểm tra nếu đọc thành công
            if (words != null) {
                // Bắt đầu trò chơi
                NoiTuGame.playGame(words);
            }
            else {
                System.out.println("Không thể đọc danh sách từ.");
            }

        }
        else if (choiceGame == 2){
            System.out.println("Đố Vui");
            DoVuiGame.choiGame();

        }

    }
    public static void Translate() throws IOException {
        System.out.println("[1] ENG --> VIE");
        System.out.println("[2] VIE --> ENG");

        Scanner scanner = new Scanner(System.in);
        int choiceTranslate = scanner.nextInt();
        scanner.nextLine();  // Đọc newline character sau khi đọc số

        if (choiceTranslate == 1) {
            System.out.println("ENG --> VIE");
            System.out.print("Nhập văn bản tiếng Anh: ");
            String text = scanner.nextLine();
            String translatedText = GoogleTranslateAPI.googleTranslate("en", "vi", text);
            System.out.println("Nghĩa: " + translatedText);
        } else if (choiceTranslate == 2) {
            System.out.println("VIE --> ENG");
            System.out.print("Nhập văn bản tiếng Việt: ");
            String text = scanner.nextLine();
            String translatedText = GoogleTranslateAPI.googleTranslate("vi", "en", text);
            System.out.println("Translation: " + translatedText);
        } else {
            System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn 1 hoặc 2.");
        }
    }
    public static int binarySearch(ArrayList<Word> words, String target) {
        int left = 0;
        int right = words.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midWord = words.get(mid).getWord_target();

            int compareResult = midWord.compareTo(target);

            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

}
