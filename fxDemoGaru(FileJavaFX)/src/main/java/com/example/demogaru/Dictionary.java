package com.example.demogaru;
import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> words = new ArrayList();

    public static ArrayList<String> SaveHistoryWord = new ArrayList();

    public ArrayList<Word> getWords() {
        return words;
    }

    public ArrayList<String> getSaveHistoryWord() {
        return SaveHistoryWord;

    }

}
