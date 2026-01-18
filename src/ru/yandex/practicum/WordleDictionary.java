package ru.yandex.practicum;

import java.util.*;

public class WordleDictionary {

    List<String> words;
    private final Set<String> wordsSet;


    public WordleDictionary(List<String> words) {
        this.words = words;
        this.wordsSet = new HashSet<>(words);
    }

    public static String compareWordsByChar(String userAnswer, String answer) {
        char[] result = new char[WordleGame.WORD_LENGTH];

        for (int i = 0; i < WordleGame.WORD_LENGTH; i++) {
            char userChar = userAnswer.charAt(i);
            char answerChar = answer.charAt(i);

            if (userChar == answerChar) {
                result[i] = '+';
            } else if (answer.indexOf(userChar) != -1) {
                result[i] = '^';
            } else {
                result[i] = '-';
            }
        }

        return new String(result);
    }

    public String getRandom() {
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public boolean contains(String word) {
        return wordsSet.contains(word);
    }

    public String getHint(char[] rightChars, Set<Character> charsInWord, Set<Character> charsNotInWord) {
        while (true) {
            String hint = getRandom();

            if (matchesConstraints(hint, rightChars, charsInWord, charsNotInWord)) {
                return hint;
            }
        }
    }

    public boolean matchesConstraints(
            String hint,
            char[] rightChars,
            Set<Character> charsInWord,
            Set<Character> charsNotInWord
    ) {
        for (int i = 0; i < WordleGame.WORD_LENGTH; i++) {
            if (rightChars[i] != '*' && hint.charAt(i) != rightChars[i]) {
                return false;
            }
        }

        for (char ch : charsInWord) {
            if (hint.indexOf(ch) == -1) {
                return false;
            }
        }

        for (char ch : charsNotInWord) {
            if (hint.indexOf(ch) != -1) {
                return false;
            }
        }

        return true;
    }

}
