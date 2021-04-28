package dictiontary;

import java.util.HashMap;

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
            dictionaryWords.forEach((key, value) -> System.out.println(key + " -> " + value));
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
