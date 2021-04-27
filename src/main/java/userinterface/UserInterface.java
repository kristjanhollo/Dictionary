package userinterface;


import dictiontary.Dictionary;

import java.util.Scanner;

public class UserInterface {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary dictionary = new Dictionary();
        drawBook();
        boolean run = true;
        while(run) {

            menu();

            String menuOption = scanner.nextLine().toUpperCase();

            switch (menuOption) {
                case "1" -> System.out.println(dictionary); // to - do
                case "2" -> LoadFile.menu(dictionary);
                case "Q" -> run = false;
                default -> System.out.println("Wrong input, please choose from menu: ");
            }
        }

    }

    static void menu() {
        System.out.println("1 - make a new dictionary");
        System.out.println("2 - load an existing dictionary");
        System.out.println("Q - to quit program");
        System.out.println("___________________________\n");
    }

    static void drawBook() {
        System.out.println("""
                       __...--~~~~~-._   _.-~~~~~--...__
                    //               `V'               \\\\\s
                   //                 |                 \\\\\s
                  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\\s
                 //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
                ====================\\\\|//====================
                               \s""");

        System.out.println("Welcome to dictionary!");
    }
}

