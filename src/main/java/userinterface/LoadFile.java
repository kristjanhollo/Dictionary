package userinterface;

import dictiontary.Dictionary;


import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class LoadFile {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        Dictionary dictionary = new Dictionary();
        menu(dictionary);
    }


    static void menu(Dictionary dictionary) {
        boolean run = true;
        while (run) {

            System.out.println("-----------------------");
            System.out.println("1 - to show existing dictionaries");
            System.out.println("2 - to load dictionary");
            System.out.println("3 - to go back to main menu");
            System.out.println("Q - to quit program");


            switch (scanner.nextLine().toUpperCase()) {
                case "1" -> filesInFolder();
                case "2" -> fileToRead(dictionary);
                case "3" -> run = false;
                case "Q" -> System.exit(0);
                default -> System.out.println("Safe case");
            }
        }
    }




    private static void loadedDictionariesMenu(String fileToLoad, Dictionary dictionary) {
        boolean run = true;
        while (run) {
            System.out.println("1 - to list all translations");
            System.out.println("2 - to search for a translation");
            System.out.println("3 - to replace a word");
            System.out.println("4 - to add a new word");
            System.out.println("5 - to go back");
            System.out.println("Q - to quit");


            switch (scanner.nextLine().toUpperCase()) {
                case "1" -> dictionary.listAllEntries();
                case "2" -> wordToTranslate(dictionary);
//      To-do          case "3" -> {
//                    System.out.println("Word to replace: ");
//                    String wordToReplace = scanner.nextLine();
//                    System.out.println("New word: ");
//                    String newWord = scanner.nextLine();
//                    System.out.println("New translation");
//                    String newTranslation = scanner.nextLine();
//                }
                case "4" -> addNewWordToDictionary(fileToLoad, dictionary);
                case "5" -> run = false;
                case "Q" -> System.exit(0);
                default -> System.out.println("Safe case");
            }
        }
    }

    private static void addNewWordToDictionary(String fileToLoad, Dictionary dictionary) {
        System.out.println("English word to add: ");
        String newWordAdd = scanner.nextLine();
        if (dictionary.wordExist(newWordAdd)) {
            System.out.println("Word exists already");
            System.out.println("Returning to menu");
        } else {
            System.out.println("translation: ");
            String newEstonian = scanner.nextLine();
            dictionary.addWord(newWordAdd, newEstonian);
            saveFile(newWordAdd, newEstonian, fileToLoad);
        }
    }

    private static void wordToTranslate(Dictionary dictionary) {
        System.out.println("Word to translate: ");
        String wordToTranslate = scanner.nextLine().toLowerCase();
        dictionary.searchWord(wordToTranslate);
    }

    private static void fileToRead(Dictionary dictionary) {
        String fileToLoad;
        System.out.print("File to read: ");
        fileToLoad = scanner.nextLine();
        if (searchWord(fileToLoad + ".txt", dictionary)) {
            loadedDictionariesMenu(fileToLoad, dictionary);
        }
    }

    private static void saveFile(String englishWord, String estonianWord, String fileName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("DictionariesCollection\\" + fileName + ".txt", true))) {
            bufferedWriter.newLine();
            bufferedWriter.append(englishWord).append(" ").append(estonianWord);
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    private static boolean searchWord(String fileToLoad, Dictionary dictionary) {
        if(loadFile(fileToLoad, dictionary)) {
            System.out.println("Dictionary " + fileToLoad.toUpperCase().substring(0 ,fileToLoad.length() - 4) + " has been successfully loaded");
            return true;
        }
        return false;
    }


    private static void filesInFolder() {
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
