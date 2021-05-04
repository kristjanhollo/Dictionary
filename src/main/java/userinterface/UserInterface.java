package userinterface;


import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void startProgram() {
        drawBook();
        boolean run = true;
        while(run) {

            menu();

            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    makeDictionary();
                    break;// to - do
                case "2":
                    LoadFile.menu();
                    break;
                case "Q":
                    run = false;
                    break;
                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    break;
            }
        }

    }

    static void menu() {
        System.out.println("1 - make a new dictionary");
        System.out.println("2 - load an existing dictionary");
        System.out.println("Q - to quit program");
        System.out.println("――――――――――――――――――――――");
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

        System.out.println("Welcome to dictionary!\n");
    }

    private static void makeDictionary() {

        System.out.print("Name of new dictionary: ");
        String fileName = scanner.nextLine();
        File tempFile = new File("DictionariesCollection\\" + fileName + ".txt");
        if(tempFile.exists()) {
            System.out.println("Sorry, " + fileName.toUpperCase() + " already exists.");
        } else {
            try {
                File myObj = new File("DictionariesCollection\\" + fileName + ".txt");
                if (myObj.createNewFile()) {
                    System.out.println(fileName.toUpperCase() + " has been created!");
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
        }
    }
    }
}

