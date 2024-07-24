package lesson101;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordCounter{

    public static void main(String[] args) {
        String[] words = {
                "солнце", "ольха", "", "еж", "дерево", "карамель",
                "карамель", "небо", "солнце", "птица", "трава", "вода", "птица",
                "дерево", "дерево", "небо", "карандаш", "вода"
        };

        // Считаем количество вхождений каждого слова
        Map<String, Integer> wordCounts = new HashMap<>();
        for (String word : words) {
            if (wordCounts.containsKey(word)) {
                wordCounts.put(word, wordCounts.get(word) + 1);
            } else {
                wordCounts.put(word, 1);
            }
        }

        // Выводим уникальные слова без дубликатов
        Set<String> uniqueWords = new HashSet<>();
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            if (entry.getValue() == 1) {
                uniqueWords.add(entry.getKey());
            }
        }

        System.out.println("Уникальные слова (без дубликатов):");
        for (String word : uniqueWords) {
            System.out.println(word);
        }

        System.out.println("\nКоличество вхождений каждого слова:");
        for (Map.Entry<String, Integer> entry : wordCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}