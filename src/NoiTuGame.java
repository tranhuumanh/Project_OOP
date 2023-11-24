import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
public class NoiTuGame {
     public static void playGame(List<String> words) {
        Set<String> usedWords = new HashSet<>();
        Set<String> usedPlayerWords = new HashSet<>(); // Thêm tập hợp mới cho từ đã sử dụng bởi người chơi

        String currentWord = getRandomWord(words);
        usedWords.add(currentWord);

        boolean gameOver = false; // Biến để kiểm tra trạng thái trò chơi

        while (!gameOver) {
            System.out.println("Từ hiện tại: " + currentWord);

            String playerWord = getPlayerInput(currentWord, usedWords, usedPlayerWords, words);
            usedWords.add(playerWord);
            usedPlayerWords.add(playerWord); // Thêm từ đã sử dụng bởi người chơi vào tập hợp

            if (isEndWord(playerWord, words)) {
                System.out.println("Từ bạn nhập không có trong danh sách từ. Bạn thua!");
                // Khi người chơi thua, đặt trạng thái trò chơi thành kết thúc
                gameOver = true;
                break;
            }

            String computerWord = getComputerWord(currentWord, usedWords, words);
            usedWords.add(computerWord);

            System.out.println("Từ máy nhập: " + computerWord);

            if (isEndWord(computerWord, words)) {
                System.out.println("Máy chiến thắng!");
                gameOver = true;
                break;
            }

            currentWord = computerWord;
        }
    }

     public static List<String> readWordList(String filePath) {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] wordArray = line.split("\\s+");
                for (String word : wordArray) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return words;
    }

    private static String getRandomWord(List<String> words) {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    private static String getPlayerInput(String currentWord, Set<String> usedWords, Set<String> usedPlayerWords, List<String> words) {
        Scanner scanner = new Scanner(System.in);
        String playerWord;

        while (true) {
            System.out.print("Nhập từ của bạn: ");
            playerWord = scanner.nextLine().trim().toLowerCase();

            if (!words.contains(playerWord)) {
                System.out.println("Từ bạn nhập không có trong danh sách từ. Bạn thua!");
                System.exit(0);
            } else if (usedWords.contains(playerWord)) {
                System.out.println("Từ này đã được sử dụng, hãy nhập từ khác.");
            } else if (usedPlayerWords.contains(playerWord)) {
                System.out.println("Bạn đã sử dụng từ này trước đó, hãy nhập từ khác.");
            } else if (playerWord.charAt(0) != currentWord.charAt(currentWord.length() - 1)) {
                System.out.println("Từ bạn nhập không đúng điều kiện. Bạn thua!");
                System.exit(0);
            } else {
                break;
            }
        }

        return playerWord;
    }

    private static boolean isEndWord(String word, List<String> words) {
        return !words.contains(word);
    }

    private static String getComputerWord(String currentWord, Set<String> usedWords, List<String> words) {
        char lastLetter = Character.toLowerCase(currentWord.charAt(currentWord.length() - 1));

        List<String> validWords = new ArrayList<>();
        for (String word : words) {
            if (!usedWords.contains(word) && Character.toLowerCase(word.charAt(0)) == lastLetter) {
                validWords.add(word);
            }
        }

        if (!validWords.isEmpty()) {
            // Lọc danh sách các từ có chữ cái cuối giống với chữ cái đầu của từ hiện tại
            List<String> validComputerWords = new ArrayList<>();
            for (String validWord : validWords) {
                if (validWord.charAt(0) == lastLetter) {
                    validComputerWords.add(validWord);
                }
            }

            if (!validComputerWords.isEmpty()) {
                Random random = new Random();
                String chosenWord = validComputerWords.get(random.nextInt(validComputerWords.size()));
                usedWords.add(chosenWord);
                return chosenWord;
            }
        }

        // Nếu không có từ nào thỏa mãn, máy chọn một từ bất kỳ chưa được sử dụng
        List<String> unusedWords = new ArrayList<>(words);
        unusedWords.removeAll(usedWords);
        if (!unusedWords.isEmpty()) {
            Random random = new Random();
            String chosenWord = unusedWords.get(random.nextInt(unusedWords.size()));
            usedWords.add(chosenWord);
            return chosenWord;
        } else {
            return ""; // Trường hợp dự phòng, không bao giờ xảy ra nếu danh sách từ không rỗng
        }
    }
}
