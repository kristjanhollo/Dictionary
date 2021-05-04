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
                    break;// to - do
                case "2":
                    // TODO: change this
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
//        System.out.println("
//                       __...--~~~~~-._   _.-~~~~~--...__
//                    //               `V'               \\\\\s
//                   //                 |                 \\\\\s
//                  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\\s
//                 //__.....----~~~~._\\ | /_.~~~~----.....__\\\\
//                ====================\\\\|//====================
//                               \s");
//
//        System.out.println("Welcome to dictionary!\n");
    }

    private void loadDictionary() {
        //TODO: change this
        System.out.print("Name of new dictionary: ");
        String fileName = scanner.nextLine();
        FileLoader.loadFile(fileName);
    }
}

