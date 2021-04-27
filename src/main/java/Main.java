import Dictionary.Dictionary;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         Dictionary dictionary = new Dictionary();
         Scanner scanner = new Scanner(System.in);
        System.out.print("Add word: ");
        String wordOne = scanner.nextLine();
        System.out.print("Add translation: ");
        String wordTwo = scanner.nextLine();
        dictionary.addWord(wordOne, wordTwo);

        System.out.print("Add word: ");
        String wordOne1 = scanner.nextLine();
        System.out.print("Add translation: ");
        String wordTwo1 = scanner.nextLine();

        dictionary.addWord(wordOne1, wordTwo1);
        System.out.print("Add word: ");
        String wordOne2 = scanner.nextLine();
        System.out.print("Add translation: ");
        String wordTwo2 = scanner.nextLine();
        dictionary.addWord(wordOne2, wordTwo2);

        dictionary.listAllEntrys();



        System.out.print("Word to translate: ");
        String searchWord = scanner.nextLine();
         dictionary.searchWord(searchWord);
         dictionary.test(searchWord);

    }
}
