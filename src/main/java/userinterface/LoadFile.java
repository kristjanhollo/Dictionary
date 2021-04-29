package userinterface;

import dictiontary.Dictionary;


import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class LoadFile {
    private static final Scanner scanner = new Scanner(System.in);
    static void menu(Dictionary dictionary) {
        boolean run = true;
        while (run) {

            printMenu();

            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    displayFilesInSystemFolder();
                    break;
                case "2":
                    dictionaryToLoad(dictionary);
                    break;
                case "3":
                    run = false;
                    break;
                case "Q":
                    System.exit(0);
                default:
                    System.out.println("Safe case");
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
                    addNewWordToDictionary(fileToLoad, dictionary);
                break;
                case "4":
                    removeEntryFromDictionary(dictionary);
                    break;
                case "5":
                    run = false;
                    break;
                case "Q":
                    System.exit(0);
                default :
                    System.out.println("Wrong input, please choose from menu: ");
            }
        }
    }

    private static void printMenu() {
        System.out.println("-----------------------");
        System.out.println("1 - to show existing dictionaries");
        System.out.println("2 - to load dictionary");
        System.out.println("3 - to go back to main menu");
        System.out.println("Q - to quit program");
    }

    private static void displayDictionariesMenu() {
        System.out.println("1 - to list all translations");
        System.out.println("2 - to search for a translation");
        System.out.println("3 - to add a new word");
        System.out.println("4 - to remove a word from dictionary");
        System.out.println("5 - to go back");
        System.out.println("Q - to quit");
    }

    private static void addNewWordToDictionary(String fileToLoad, Dictionary dictionary) {
        System.out.println("English word to add: ");
        String englishWordToAdd = scanner.nextLine();
        if (dictionary.checkForWord(englishWordToAdd)) {
            System.out.println("Word exists already");
            System.out.println("Returning to menu");
        } else {
            System.out.println("Translation: ");
            String estonianTranslation = scanner.nextLine();
            dictionary.addWord(englishWordToAdd, estonianTranslation);
            addToDictionary(englishWordToAdd, estonianTranslation, fileToLoad);
        }
    }

    private static void translateWord(Dictionary dictionary) {
        System.out.println("Word to translate: ");
        String wordToTranslate = scanner.nextLine().toLowerCase();
        dictionary.searchWord(wordToTranslate);
    }

    private static boolean searchWord(String fileToLoad, Dictionary dictionary) {
        if(loadFile(fileToLoad, dictionary)) {
            System.out.println("Dictionary " + fileToLoad.toUpperCase().substring(0 ,fileToLoad.length() - 4) + " has been successfully loaded");
            return true;
        }
        return false;
    }

    private static void removeEntryFromDictionary(Dictionary dictionary) {
        System.out.println("Word to remove from dictionary: ");
        String wordToRemove = scanner.nextLine();
        if(dictionary.checkForWord(wordToRemove)) {
           dictionary.removeWord(wordToRemove);
        }
    }

    private static void dictionaryToLoad(Dictionary dictionary) {
        System.out.print("Dictionary to load: ");
        String fileToLoad = scanner.nextLine();
        if (searchWord(fileToLoad + ".txt", dictionary)) {
            loadedDictionariesMenu(fileToLoad, dictionary);
        }
    }

    private static void addToDictionary(String englishWord, String estonianWord, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("DictionariesCollection\\" + fileName + ".txt", true))) {
            bufferedWriter.newLine();
            bufferedWriter.append(englishWord).append(" ").append(estonianWord);
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private static void displayFilesInSystemFolder() {
        System.out.println("Existing dictionaries:\n");
        File f = new File("DictionariesCollection\\");
        String[] dictionaryNames = f.list((f1, name) -> name.endsWith(".txt"));
        if (dictionaryNames != null) {
            Arrays.stream(dictionaryNames).forEach(e -> System.out.println(e.toUpperCase().substring(0, e.length() - 4)));
        } else {
            System.out.println("No existing dictionaries");
        }
    }

    private static boolean loadFile(String fileName, Dictionary dictionary) {
        try(Scanner readLines = new Scanner(Path.of("DictionariesCollection\\" + fileName))) {
            while(readLines.hasNext()) {
                String[] lines = readLines.nextLine().toLowerCase().split(" ");
                dictionary.addWord(lines[0],lines[1]);
            }

        } catch (IOException e) {
            System.out.println(fileName.substring(0,fileName.length()-4).toUpperCase() + " dictionary not found");
            return false;
        }
        return true;
    }
}
