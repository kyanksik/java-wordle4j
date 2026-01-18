package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Wordle {
    public static void main(String[] args) {
        try (PrintWriter writer = createLogger()) {
            writer.println("Запуск игры.");

            WordleDictionaryLoader loader = new WordleDictionaryLoader(writer);
            WordleDictionary dictionary = loadDictionary(loader, writer);

            if (dictionary == null) {
                writer.println("Словарь пуст");
                return;
            }

            WordleGame wordleGame = new WordleGame(dictionary);
            System.out.println("Игра началась");

            playGame(wordleGame, writer);

            writer.println("Игра завершена");
        } catch (IOException e) {
            System.err.println("Ошибка создания файла лога: " + e.getMessage());
        }
    }

    private static PrintWriter createLogger() throws IOException {
        FileWriter fWrite = new FileWriter("logger.txt", StandardCharsets.UTF_8, true);
        return new PrintWriter(fWrite);
    }

    private static WordleDictionary loadDictionary(
            WordleDictionaryLoader loader,
            PrintWriter writer
    ) {
        try {
            return loader.loadFromFile("words_ru.txt");
        } catch (IOException e) {
            writer.println("Не удалось прочитать файл: " + e.getMessage());
            System.err.println("Работа программы завершена");
            return null;
        }
    }

    private static void playGame(WordleGame game, PrintWriter writer) {
        while (game.getSteps() > 0) {
            int i = 1;
            System.out.println(
                    "Введите слово из " + WordleGame.WORD_LENGTH + " букв. Для подсказки нажмите Enter"
            );

            String message = game.checkAnswer();
            System.out.println(message);
                writer.println("Попыток осталось: " + game.getSteps() + " " + message);
        }

        if (game.getSteps() == 0) {
            System.out.println("У вас закончились попытки.");
            System.out.println("Правильный ответ: " + game.getAnswer());
            writer.println("Закончились попытки");
            writer.println("Ответ: " + game.getAnswer());
        }
    }
}