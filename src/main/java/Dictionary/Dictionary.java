package Dictionary;

import java.util.HashMap;

public class Dictionary {
    private String firstWord;
    private String secondWord;
    private HashMap<String, String> dictionaryWords;

    public Dictionary() {
        this.dictionaryWords = new HashMap<>();
    }

    public void addWord(String word, String wordToTranslate) {
        dictionaryWords.put(word,wordToTranslate);
    }

    public boolean wordExist(String wordToSearch) {
        for(String word : dictionaryWords.keySet()) {
            if(wordToSearch.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public void searchWord(String wordToSearch) {
        if(wordExist(wordToSearch)) {
            System.out.println(wordToSearch + " translates to " + dictionaryWords.get(wordToSearch));
        } else {
            System.out.println("Sorry , no such word in dictionary");
        }
    }
}
