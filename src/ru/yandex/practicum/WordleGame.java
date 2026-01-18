package ru.yandex.practicum;

import java.util.*;

public class WordleGame {

    private String answer;

    private int steps;

    private final WordleDictionary dictionary;

    private final List<String> userAnswers = new ArrayList<>();

    private final char[] rightChars = new char[]{'*', '*', '*', '*', '*'};

    private final Set<Character> charsInWord = new HashSet<>();

    private final Set<Character> charsNotInWord = new HashSet<>();

    Scanner scanner = new Scanner(System.in);


    public static final int WORD_LENGTH = 5;

    public WordleGame(WordleDictionary dictionary) {
        this.dictionary = dictionary;
        this.answer = dictionary.getRandom();

        this.steps = 6;
    }

    public String checkAnswer() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.isEmpty()) {
                input = dictionary.getHint(rightChars, charsInWord, charsNotInWord);
                System.out.println(input);
            }

            String validationError = validateInput(input);
            if (validationError != null) {
                System.out.println(validationError);
                continue; // Возвращаемся к чтению ввода
            }

            userAnswers.add(input);
            steps--;

            if (input.equals(answer)) {
                steps = -1;
                return "Победа";
            }

            return updateGameState(input);
        }
    }

    private String validateInput(String input) {
        if (input.length() != WORD_LENGTH) {
            return "В слове должно быть ровно " + WORD_LENGTH + " букв";
        }
        if (!input.matches("[а-яё]+")) {
            return "Разрешены только строчные буквы русского алфавита";
        }
        if (userAnswers.contains(input)) {
            return "Вы уже вводили это слово";
        }
        if (!dictionary.contains(input)) {
            return "Такого слова нет в словаре";
        }
        return null;
    }

    private String updateGameState(String userAnswer) {
        String result = WordleDictionary.compareWordsByChar(userAnswer, answer);


        for (int i = 0; i < WORD_LENGTH; i++) {
            char charI = userAnswer.charAt(i);
            switch (result.charAt(i)) {
                case '+':
                    rightChars[i] = charI;
                    charsInWord.add(charI);
                    break;
                case '^':
                    charsInWord.add(charI);
                    break;
                default:
                    charsNotInWord.add(charI);
            }
        }
        return result;
    }


    public int getSteps() {
        return steps;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


}

