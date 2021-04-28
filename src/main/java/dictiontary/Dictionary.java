package dictiontary;

import java.util.Comparator;
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
        if (wordExist(word)) {
            System.out.println("Word already exists");
        } else {
            dictionaryWords.put(word,wordToTranslate);
        }
    }

    public boolean wordExist(String wordToSearch) {
        for(String word : dictionaryWords.keySet()) {
            if(wordToSearch.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void listAllEntries() {
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

//    public void replaceWord(String oldEntry, String newEntry) { // To-do
//        if(wordExist(oldEntry)) {
//            dictionaryWords.replace(oldEntry,newEntry);
//        } else {
//            System.out.println("No such entry in dictionary");
//        }
//    }

    public void searchWord(String wordToSearch) {
        if(wordExist(wordToSearch)) {
            System.out.println(wordToSearch + " translates to " + dictionaryWords.get(wordToSearch));
        } else {
            System.out.println("Sorry , no such word in dictionary");
        }
    }

}
