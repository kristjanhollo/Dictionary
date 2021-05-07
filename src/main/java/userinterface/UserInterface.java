package userinterface;


import java.util.Scanner;

public class UserInterface implements GUI {
    private static final Scanner scanner = new Scanner(System.in);

    public void startProgram() {
        drawBook();
        boolean isRunning = true;
        while (isRunning) {
            MainMenu.show();
            switch (scanner.nextLine().toUpperCase()) {
                case "1":
                    loadDictionary();
                    break;
                case "2":
                    DictionaryOperator.menu();
                    break;
                case "Q":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Wrong input, please choose from menu: ");
                    break;
            }
        }

    }

    public void drawBook() {
        System.out.println(
                "         ______ ______\n" +
                "       _/      Y      \\_\n" +
                "      // ~~ ~~ | ~~ ~  \\\\\n" +
                "     // ~ ~ ~~ | ~~~ ~~ \\\\\n" +
                "    //________.|.________\\\\\n" +
                "   `----------`-'----------'");
    }

    private void loadDictionary() {
        System.out.print("Name of new dictionary: ");
        String fileName = scanner.nextLine();
        FileLoader.loadFile(fileName);
    }
}

