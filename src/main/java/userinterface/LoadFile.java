package userinterface;

import dictiontary.Dictionary;


import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class LoadFile {
    private static final Scanner scanner = new Scanner(System.in);

    static void menu() {
        boolean run = true;
        while (run) {

            printMenu();

            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    displayFilesInSystemFolder();
                    break;
                case "2":
                    dictionaryToLoad();
                    break;
                case "3":
                    run = false;
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    break;
            }
        }
    }


    private static void loadedDictionariesMenu(String fileToLoad, Dictionary dictionary) {
        boolean run = true;
        while (run) {
            displayDictionariesMenu();

            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    dictionary.listFullDictionary();
                    break;
                case "2":
                    translateWord(dictionary);
                    break;
                case "3":
                    addNewWordToDictionary(dictionary);
                    saveDictionary(fileToLoad, dictionary);
                    break;
                case "4":
                    removeEntryFromDictionary(dictionary);
                    saveDictionary(fileToLoad,dictionary);
                    break;
                case "5":
                    run = false;
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Wrong input, please choose from menu: ");
            }
        }
    }

    private static void printMenu() {
        System.out.println("――――――――――――――――――――――");
        System.out.println("1 - to show existing dictionaries");
        System.out.println("2 - to load dictionary");
        System.out.println("3 - to go back to main menu");
        System.out.println("Q - to quit program");
        System.out.println("――――――――――――――――――――――");
    }

    private static void displayDictionariesMenu() {
        System.out.println("――――――――――――――――――――――");
        System.out.println("1 - to list all translations");
        System.out.println("2 - to search for a translation");
        System.out.println("3 - to add a new word");
        System.out.println("4 - to remove a word from dictionary");
        System.out.println("5 - to go back");
        System.out.println("Q - to quit");
        System.out.println("――――――――――――――――――――――");
    }

    private static void addNewWordToDictionary(Dictionary dictionary) {
        System.out.println("English word to add: ");
        String englishWordToAdd = scanner.nextLine();
        if (dictionary.checkForWord(englishWordToAdd)) {
            System.out.println(englishWordToAdd + " already exists in dictionary");
            System.out.println("Returning to menu");
        } else {
            System.out.println("Translation: ");
            String estonianTranslation = scanner.nextLine();
            dictionary.addWord(englishWordToAdd, estonianTranslation);
        }
    }

    private static void translateWord(Dictionary dictionary) {
        System.out.println("Word to translate: ");
        String wordToTranslate = scanner.nextLine().toLowerCase();
        if(dictionary.checkForWord(wordToTranslate)) {
            System.out.println(wordToTranslate + " -> " + dictionary.searchWord(wordToTranslate));
        } else {
            System.out.println(wordToTranslate + " not in dictionary");
        }
        boolean run = true;
        while(run) {
            System.out.print("Would you like to translate another word? (y/n): ");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "y", "yes", "ye":
                    translateWord(dictionary);
                    break;
                case "n", "no":
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input, please choose between y / n");
                    break;
            }
        }
    }


    private static void removeEntryFromDictionary(Dictionary dictionary) {
        System.out.println("Word to remove from dictionary: ");
        String wordToRemove = scanner.nextLine().toLowerCase();
        if (dictionary.checkForWord(wordToRemove)) {
            dictionary.removeWord(wordToRemove);
        } else {
            System.out.println(wordToRemove + " not in dictionary");
        }
    }

    private static void dictionaryToLoad() {
        System.out.print("Dictionary to load: ");
        String fileName = scanner.nextLine();
        if (checkFileExist(fileName)) {
            Dictionary dictionary = loadFile(fileName);
            loadedDictionariesMenu(fileName, dictionary);
        } else {
            System.out.println(fileName.toUpperCase() + " dictionary not found");
        }

    }


    private static boolean checkFileExist(String fileName) {
        File tempFile = new File("DictionariesCollection\\" + fileName + ".txt");
        return tempFile.exists();
    }

    private static Dictionary loadFile(String fileName) {
        Dictionary dictionary = new Dictionary();
        try (Scanner readLines = new Scanner(Path.of("DictionariesCollection\\" + fileName + ".txt"))) {
            while (readLines.hasNext()) {
                String[] lines = readLines.nextLine().toLowerCase().split(" ");
                dictionary.addWord(lines[0], lines[1]);
            }
            System.out.println("Dictionary " + fileName + " has been successfully loaded");
            return dictionary;
        } catch (IOException e) {
            return null;
        }

    }

    private static void saveDictionary(String fileName, Dictionary dictionary) {
        Map<String, String> toSave = dictionary.getDictionaryWords();
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
        File f = new File("DictionariesCollection\\");
        String[] dictionaryNames = f.list((f1, name) -> name.endsWith(".txt"));
        if (dictionaryNames != null) {
            Arrays.stream(dictionaryNames).forEach(e -> System.out.println("-> " + e.toUpperCase().substring(0, e.length() - 4)));
        } else {
            System.out.println("No existing dictionaries");
        }
    }
}
