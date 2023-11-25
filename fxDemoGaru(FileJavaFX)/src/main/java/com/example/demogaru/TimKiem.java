package com.example.demogaru;
import java.util.List;

public class TimKiem {
    public static int min(int a, int b) {
        return Math.min(a, b);
    }

    public static int compare(String keyword, String word_target) {
        int minLength = min(keyword.length(), word_target.length());

        for (int i = 0; i < minLength; i++) {
            int charComparison = Character.compare(keyword.charAt(i), word_target.charAt(i));
            if (charComparison != 0) {
                return charComparison;
            }
        }

        return Integer.compare(keyword.length(), word_target.length());
    }

    public static int binarySearchWords(List<String> list, String keyword, int x, int y) {
        while (x <= y) {
            int mid = x + (y - x) / 2;
            int comparison = compare(keyword, list.get(mid));

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                y = mid - 1;
            } else {
                x = mid + 1;
            }
        }

        return -1;
    }

    public static boolean condition(List<String> list, String keyword) {
        return (binarySearchWords(list, keyword, 0, list.size() - 1) != -1);
    }

    public static int start(List<String> list, String keyword, int x, int y) {
        while (x <= y) {
            int mid = x + (y - x) / 2;
            int comparison = compare(keyword, list.get(mid));

            if (comparison == 0) {
                y = mid - 1;
            } else if (comparison < 0) {
                y = mid - 1;
            } else {
                x = mid + 1;
            }
        }

        return x;
    }

    public static int finish(List<String> list, String keyword, int x, int y) {
        while (x <= y) {
            int mid = x + (y - x) / 2;
            int comparison = compare(keyword, list.get(mid));

            if (comparison == 0) {
                x = mid + 1;
            } else if (comparison < 0) {
                x = mid + 1;
            } else {
                y = mid - 1;
            }
        }

        return y;
    }
}
