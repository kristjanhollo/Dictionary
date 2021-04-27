package UserInterface;

import java.io.*;

public class Userinterface {

    public static void main(String[] args) {


        try
                (BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error");
        }

        try
            (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("test.txt", true))) {
            bufferedWriter.newLine();
            bufferedWriter.append("\nder3425345f");
            bufferedWriter.newLine();
            bufferedWriter.append("hfftrh");
            bufferedWriter.newLine();
            bufferedWriter.append("qwsdfaf");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }


        System.out.println("=====================================");

        try
                (BufferedReader bufferedReader = new BufferedReader(new FileReader("test.txt"))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                System.out.println(line);
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
