package dictiontary;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Dictionary {
    private final HashMap<String, String> dictionaryWords;

    public Dictionary() {
        this.dictionaryWords = new HashMap<>();
    }

    public void addWord(String word, String wordToTranslate) {
        if (checkForWord(word)) {
            System.out.println("Word already exists in dictionary");
        } else {
            dictionaryWords.put(word,wordToTranslate);
        }
    }

    public void removeWord(String word) {
        if(checkForWord(word)) {
            dictionaryWords.remove(word);
            System.out.println(word + " has removed from dictionary");
        } else {
            System.out.println(word + " not in dictionary");
        }
    }

    public boolean checkForWord(String wordToSearch) {
        for(String word : dictionaryWords.keySet()) {
            if(wordToSearch.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void listFullDictionary() {
        if (dictionaryWords.size() != 0) {
            System.out.println("-------------------------------------");

            Map<String, String> sortedMap = dictionaryWords
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
            sortedMap.forEach((key, val) -> System.out.println(key + " -> " + val));

            System.out.println("-------------------------------------");
        } else {
            System.out.println("Dictionary is empty");
        }
    }

    public void searchWord(String wordToSearch) {
        if(checkForWord(wordToSearch)) {
            System.out.println(wordToSearch + " translates to " + dictionaryWords.get(wordToSearch));
        } else {
            System.out.println("Sorry, no such word in dictionary");
        }
    }

}
