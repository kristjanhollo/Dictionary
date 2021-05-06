package dictionary;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DictionaryMap {
    private final HashMap<String, String> entries;
    private final Scanner scanner = new Scanner(System.in);

    public DictionaryMap() {
        this.entries = new HashMap<>();
    }

    public HashMap<String, String> getEntries() {
        return entries;
    }

    public static DictionaryMap builder(String fileName) {
        DictionaryMap dictionaryMap = new DictionaryMap();
        try (Scanner readLines = new Scanner(Path.of("DictionariesCollection\\" + fileName + ".txt"))) {
            while (readLines.hasNext()) {
                String[] lines = readLines.nextLine().toLowerCase().split(" ");
                dictionaryMap.addWord(lines[0], lines[1]);
            }
            System.out.println("Dictionary " + fileName + " has been successfully loaded");
            return dictionaryMap;
        } catch (IOException e) {
            return new DictionaryMap();
        }
    }

    public void addWord(String word, String wordToTranslate) {
        if (checkForWord(word)) {
            System.out.println(word + " already exists in dictionary");
        } else {
            entries.put(word,wordToTranslate);
        }
    }

    public void removeWord(String word) {
        if(checkForWord(word)) {
            entries.remove(word);
            System.out.println(word + " has been removed from dictionary");
        } else {
            System.out.println(word + " not in dictionary");
        }
    }

    public boolean checkForWord(String wordToSearch) {
        for(String word : entries.keySet()) {
            if(wordToSearch.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public Map<String, String> showAllEntries() {
        Map<String, String> sortedMap = entries;
        if (entries.size() != 0) {
            System.out.println("――――――――――――――――――――――");
            sortedMap = entries
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
            sortedMap.forEach((key, val) -> System.out.println(key + " -> " + val));
            System.out.println("――――――――――――――――――――――");
        } else {
            System.out.println("Dictionary is empty");
        }
        return sortedMap;
    }

    public String searchWord(String wordToSearch) {
        if(checkForWord(wordToSearch)) {
            return entries.get(wordToSearch);
        }
        return null;
    }

    public static void translateWord(DictionaryMap dictionary, Scanner scanner) {
        System.out.print("Type the word to translate:");
        dictionary.translateWord(scanner.nextLine());
        boolean isTrue = true;
        while (isTrue) {
            System.out.print("Would you like to translate another word? (y/n): ");
            if (scanner.nextLine().toLowerCase().equals("y")) {
                System.out.print("Type the word to translate:");
                dictionary.translateWord(scanner.nextLine());
            } else {
                isTrue = false;
                break;
            }
        }
    }

    private void translateWord(String wordToTranslate) {
        if(checkForWord(wordToTranslate.toLowerCase())) {
            System.out.println(wordToTranslate + " -> " + searchWord(wordToTranslate));
        } else {
            System.out.println(wordToTranslate + " not in dictionary");
        }
    }
}
