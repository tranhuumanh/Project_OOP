
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DoVuiGame {

    private static String[] questions;
    private static String[] answers;
    private static int currentQuestionIndex; // Đưa biến này lên cấp độ lớp


    public static void choiGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chào mừng bạn đến với Trò chơi Đỏ Vui!");

        do {
            chonCauHoiNgauNhien();
            choiVong();

            System.out.print("Bạn có muốn chơi lại không? (có/không): ");
            String luaChonChoiLai = scanner.nextLine().toLowerCase();

            if (!luaChonChoiLai.equals("có")) {
                break;
            }
        } while (true);

        System.out.println("Cảm ơn bạn đã tham gia Trò chơi Đỏ Vui!");

        scanner.close();
    }

    private static void chonCauHoiNgauNhien() {
        try {
            File file = new File("CauHoiGame.txt");
            Scanner fileScanner = new Scanner(file);

            int numberOfLines = 0;

            while (fileScanner.hasNextLine()) {
                numberOfLines++;
                fileScanner.nextLine();
            }

            questions = new String[numberOfLines];
            answers = new String[numberOfLines];

            fileScanner = new Scanner(file);

            for (int i = 0; i < numberOfLines; i++) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 2) {
                    questions[i] = parts[0];
                    answers[i] = parts[1];
                }
            }

            Random random = new Random();
            currentQuestionIndex = random.nextInt(numberOfLines);
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy tệp tin: CauHoiGame.txt");
            e.printStackTrace();
        }
    }

    private static void choiVong() {
        Scanner scanner = new Scanner(System.in);

        String currentQuestion = questions[currentQuestionIndex];
        System.out.println("Câu hỏi: " + currentQuestion);

        String answer = answers[currentQuestionIndex].toLowerCase();
        char[] answerChars = answer.toCharArray();
        char[] revealedChars = new char[answerChars.length];

        for (int i = 0; i < revealedChars.length; i++) {
            if (Character.isWhitespace(answerChars[i])) {
                revealedChars[i] = ' ';
            } else {
                revealedChars[i] = '_';
            }
        }

        boolean daDoanDung = false;
        int soLanThu = 0;
        int soLanToiDa = 3;

        while (!daDoanDung && soLanThu < soLanToiDa) {
            System.out.println("Tiến trình hiện tại: " + new String(revealedChars));
            System.out.print("Nhập một chữ cái hoặc dấu cách: ");
            String duDoanInput = scanner.nextLine().toLowerCase();

            if (duDoanInput.length() == 1) {
                char duDoan = duDoanInput.charAt(0);
                boolean coChuTimThay = false;

                for (int i = 0; i < answerChars.length; i++) {
                    if (Character.toLowerCase(answerChars[i]) == duDoan && revealedChars[i] == '_') {
                        revealedChars[i] = answerChars[i];
                        coChuTimThay = true;
                    }
                }

                if (!coChuTimThay) {
                    System.out.println("Sai! Còn lại số lần thử: " + (soLanToiDa - soLanThu - 1));
                    soLanThu++;
                }

                daDoanDung = kiemTraDaDoanDung(revealedChars);
            } else if (duDoanInput.equals(answer)) {
                daDoanDung = true;
            } else {
                System.out.println("Sai! Còn lại số lần thử: " + (soLanToiDa - soLanThu - 1));
                soLanThu++;
            }
        }

        hienThiKetQua(daDoanDung, revealedChars, answer);

        // Chuyển sang câu hỏi tiếp theo
        if (currentQuestionIndex < questions.length) {
            // Bạn có thể thêm một thông báo trước khi chuyển sang vòng mới
            System.out.println("Chuyển sang câu hỏi tiếp theo...");
        } else {
            System.out.println("Chúc mừng! Bạn đã hoàn thành tất cả các câu hỏi.");
        }
    }

    private static void hienThiKetQua(boolean daDoanDung, char[] revealedChars, String answer) {
        if (daDoanDung) {
            System.out.println("Chính xác! Bạn đã bắt được hình và từ: " + new String(revealedChars));
        } else {
            System.out.println("Rất tiếc, bạn đã hết số lần thử. Đáp án đúng là: " + answer);
        }
    }

    private static boolean kiemTraDaDoanDung(char[] kyTuHienThi) {
        for (char c : kyTuHienThi) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }
}
