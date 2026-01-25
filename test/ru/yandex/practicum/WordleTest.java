package ru.yandex.practicum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    @BeforeEach
    void setUp() {
        WordleDictionary dictionary = new WordleDictionary(Arrays.asList("кошка", "мышка", "ложка", "брошь"));
        WordleGame game = new WordleGame(dictionary);
        game.setAnswer("кошка"); //
    }

    @Test
    void testCompareWordsByChar_ExactMatch() {
        String result = WordleDictionary.compareWordsByChar("кошка", "кошка");
        assertEquals("+++++", result);
    }

    @Test
    void testCompareWordsByChar_PartialMatch() {
        String result = WordleDictionary.compareWordsByChar("мышка", "кошка");
        assertEquals("--+++", result);
    }

    @Test
    void testCompareWordsByChar_NoMatch() {
        String result = WordleDictionary.compareWordsByChar("время", "кошка");
        assertEquals("-----", result);
    }

    @Test
    void testCompareWordsByChar_RepeatedChars() {
        String result = WordleDictionary.compareWordsByChar("кабак", "кошка");
        assertEquals("+^-^^", result);
    }
}

