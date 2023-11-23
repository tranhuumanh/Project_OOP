import java.util.Scanner;

public class DictionaryManagement {
    private Word[] words; // mảng các từ
    private int size; // số lượng từ

    // Hàm khởi tạo
    public DictionaryManagement() {
        words = new Word[1000]; // giả sử có tối đa 1000 từ
        size = 0; // ban đầu chưa có từ nào
    }

    // Hàm nhập liệu từ bàn phím
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in); // tạo đối tượng Scanner để đọc dữ liệu
        System.out.println("Nhập số lượng từ vựng:");
        int n = sc.nextInt(); // đọc số lượng từ vựng
        sc.nextLine(); // đọc bỏ dòng trống
        for (int i = 0; i < n; i++) {
            System.out.println("Nhập từ tiếng Anh:");
            String word_target = sc.nextLine(); // đọc từ tiếng Anh
            System.out.println("Nhập giải thích bằng tiếng Việt:");
            String word_explain = sc.nextLine(); // đọc giải thích tiếng Việt
            words[size] = new Word(word_target, word_explain); // tạo đối tượng Word và thêm vào mảng
            size++; // tăng số lượng từ lên 1
        }
        sc.close(); // đóng đối tượng Scanner
    }

    // Hàm hiển thị danh sách các từ
    public void showAllWords() {
        System.out.println("Danh sách các từ:");
        for (int i = 0; i < size; i++) {
            System.out.println(words[i].getWord_target() + " : " + words[i].getWord_explain());
        }
    }
    //adu
}