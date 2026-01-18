package ru.yandex.practicum;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class WordleDictionaryLoader {

    PrintWriter printWriter;

    public WordleDictionaryLoader(PrintWriter printWriter) {
        this.printWriter = printWriter;
    }

    public WordleDictionary loadFromFile(String path) throws IOException {
        List<String> words = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() == WordleGame.WORD_LENGTH) {
                    words.add(line.toLowerCase().replace('ё', 'e'));
                }
            }
        }
        return new WordleDictionary(words);
    }
}