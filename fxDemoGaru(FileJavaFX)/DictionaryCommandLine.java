
package com.example.demogaru;
import com.example.demogaru.GoogleTranslateAPI;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
public class DictionaryCommandLine extends Dictionary {

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
//                    LookUpCommandLine();
                    break;
                case "6":
                    SearchCommandLine();
                    break;
                case "7":
                    //
                    break;
                case "8":
                    ImportFromFileCommandLine();
                    break;
                case "9":
                    ExportToFileCommandLine();
                    break;
                case "10":
                    //HistoryCommandLine();
                case "11":
                    //
                case "12":
                    try{
                        Translate();
                    }
                    catch (IOException e){
                    }
                    break;


                default:
                    System.out.println("Không khả dụng yêu cầu nhập lại!");
            }
        }
    }


    public static String LookUpCommandLine(String abc) { //Hàm tìm kiếm các từ tiếng Anh
        ImportFromFileCommandLine();
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhập từ cần tìm: ");
//        String worded = sc.nextLine();

        for (Word word : Dictionary.words) {
            if ((word.getWord_target()).equals(abc)) {

                  (Dictionary.SaveHistoryWord).add(word.getWord_target()); //Lưu lại lịch sử tìm kiếm
//
//                System.out.println(" English    | Vietnamese");
//                System.out.println("----------------------------");
//
//                System.out.printf(" %-10s | %-10s%n", word.getWord_target(), word.getWord_explain());
                return word.getWord_explain();
            }
        }
//        System.out.println("Không tìm thấy từ cần tìm");
        return "Không tìm thấy từ!";
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

    public String HistoryCommandLine() {
          if (Dictionary.SaveHistoryWord.isEmpty()) {
//              System.out.println("Không có từ nào tìm kiếm gần đây");
//              return;
                return "Không có từ nào tìm kiếm gần đây";
          }
//          System.out.println("Tìm kiếm gần đây");
          int n = Dictionary.SaveHistoryWord.size();
          int no = 1;
        StringBuilder content = new StringBuilder();
        for(int i = n-1 ; i >= 0 ; i--) {
//            System.out.println(Dictionary.SaveHistoryWord.get(i));
            content.append(Dictionary.SaveHistoryWord.get(i));
            content.append("\n");
            no++;
        }
//        System.out.println("Số kết quả đã tìm kiếm là:" + no);
        return content.toString();
//        return "Số kết quả đã tìm kiếm là:" + no;
    }


    public static void ImportFromFileCommandLine() {
        try {
            File file = new File("Dictionary.txt");

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
    public static void main(String[] args) {
        DictionaryCommandLine dictionaryApp = new DictionaryCommandLine();
        dictionaryApp.dictionaryBasic();
    }
}
