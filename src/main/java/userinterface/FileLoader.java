package userinterface;

import java.io.File;
import java.io.IOException;

public class FileLoader {
    public static void loadFile(String path) {
        File fileToLoad = new File("DictionariesCollection\\" + path + ".txt");
        if (fileToLoad.exists()) {
            System.out.println("Sorry, " + path.toUpperCase() + " already exists.");
        } else {
            try {
                fileToLoad = new File("DictionariesCollection\\" + path + ".txt");
                if (fileToLoad.createNewFile()) {
                    System.out.println(path.toUpperCase() + " has been created!");
                    System.out.println();
                }
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public static boolean exists(String fileName) {
        File fileToLoad =  new File("DictionariesCollection\\" + fileName + ".txt");
        return fileToLoad.exists();
    }
}
