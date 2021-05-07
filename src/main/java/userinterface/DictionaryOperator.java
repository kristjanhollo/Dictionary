package userinterface;
import dictionary.DictionaryMap;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;


public class DictionaryOperator {
    private static final Scanner scanner = new Scanner(System.in);

    static void menu() {
        boolean isRunning = true;
        while (isRunning) {
            DictionaryMenu.showDictionaryOperationsMenu();
            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    displayFilesInSystemFolder();
                    break;
                case "2":
                    dictionaryToLoad();
                    break;
                case "3":
                    isRunning = false;
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    break;
            }
        }
    }


    private static void loadedDictionariesMenu(String fileToLoad, DictionaryMap dictionary) {
        boolean isRunning = true;
        while (isRunning) {
            DictionaryMenu.showTranslationMenu();
            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    dictionary.showAllEntries();
                    break;
                case "2":
                    DictionaryMap.translateWord(dictionary,scanner);
                    break;
                case "3":
                    DictionaryMap.addWord(dictionary, scanner);
                    saveDictionary(fileToLoad, dictionary);
                    break;
                case "4":
                    System.out.println("Word to remove from dictionary: ");
                    String wordToRemove = scanner.nextLine().toLowerCase();
                    dictionary.removeWord(wordToRemove);
                    saveDictionary(fileToLoad, dictionary);
                    break;
                case "5":
                    isRunning = false;
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    break;
            }
        }
    }



    private static void dictionaryToLoad() {
        System.out.print("Dictionary to load: ");
        String fileName = scanner.nextLine();
        if (FileLoader.exists(fileName)) {
            DictionaryMap dictionary = DictionaryMap.builder(fileName);
            loadedDictionariesMenu(fileName, dictionary);
        } else {
            System.out.println(fileName.toUpperCase() + " dictionary not found");
        }
    }

    private static void saveDictionary(String fileName, DictionaryMap dictionary) {
        Map<String, String> toSave = dictionary.getEntries();
        File file = new File("DictionariesCollection\\" + fileName + ".txt");
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(file));
            for (Map.Entry<String, String> entry : toSave.entrySet()) {
                bf.write(entry.getKey() + " " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bf != null;
                bf.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void displayFilesInSystemFolder() {
        System.out.print("Existing dictionaries:\n");
        File file = new File("DictionariesCollection\\");
        String[] dictionaryNames = file.list((f1, name) -> name.endsWith(".txt"));
        if (dictionaryNames != null) {
            Arrays.stream(dictionaryNames).forEach(e -> System.out.println("-> " + e.toUpperCase().substring(0, e.length() - 4)));
        } else {
            System.out.println("No existing dictionaries");
        }
    }
}
