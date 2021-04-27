package UserInterface;


import java.util.Scanner;

public class ReadFile {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        boolean run = true;
        while(run) {


            menu();

            String menuOption = scanner.nextLine().toUpperCase();

            switch (menuOption) {
                case "1":
                    System.out.println("Option 1");
                    break;
                case "2":
                    LoadFile.menu();
                    break;

                case "Q":
                    run = false;
                    break;

                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    menu();
                    break;
            }
        }

    }

    static void menu() {
        System.out.println("Welcome to dictionary!");
        System.out.println("1 - make a new dictionary");
        System.out.println("2 - load an existing dictionary");
        System.out.println("Q - to quit program");
        System.out.println("___________________________");
        System.out.println("");
    }



}
