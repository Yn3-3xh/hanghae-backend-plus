package org.example.junitinaction3.chapter02.parameterized;

public class WordCounter {
    public int countWords(String sentence) {
        return sentence.split(" ").length;
    }
}