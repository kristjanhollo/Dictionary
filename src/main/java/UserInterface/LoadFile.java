package UserInterface;

import Dictionary.Dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Scanner;

public class LoadFile {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean run = true;

    public static void main(String[] args) {
        menu();
    }


    static void menu() {



        while (run) {

            System.out.println("-----------------------");
            System.out.println("1 - to show existing dictionaries");
            System.out.println("2 - to load dictionary");
            System.out.println("3 - to go back to main menu");
            System.out.println("Q - to quit program");


            String input = scanner.nextLine().toUpperCase();
            switch (input) {
                case "1":
                    filesInFolder();
                    break;
                case "2":
                    System.out.print("File to read: ");
                    String fileToLoad = scanner.nextLine();
                    loadFile(fileToLoad + ".txt");
                    break;
                case "3":
                    run = false;
                    break;
                case "Q":
                    System.exit(0);
            }
        }
    }

    static void filesInFolder() {
        File f = new File("DictionariesCollection\\");
        String[] pathnames = f.list((f1, name) -> name.endsWith(".txt"));
        if (pathnames != null) {
            Arrays.stream(pathnames).forEach(e -> System.out.println(e.toUpperCase().substring(0, e.length() - 4)));
        } else {
            System.out.println("No existing dictionaries");
        }
    }


    static void loadFile(String fileToLoad) {
        Dictionary dictionary = new Dictionary();
        try(Scanner readLines = new Scanner(Path.of("DictionariesCollection\\" + fileToLoad))) {
            while(readLines.hasNext()) {
                String[] lines = readLines.nextLine().toLowerCase().split(" ");
                dictionary.addWord(lines[0],lines[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error");
        }
        System.out.println("Dictionary "+ fileToLoad.toUpperCase().substring(0 ,fileToLoad.length() - 4) + " has been successfully loaded");
        dictionary.listAllEntrys();
    }
}
