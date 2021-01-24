package com.company;

import java.io.*;
import java.util.Scanner;

public class FileHandler {




    public static String readFile(String path) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }
        assert sc != null;
        String text = sc.useDelimiter(",").next();
        sc.close();
        return text;
    }

    public static void writeToFile(String fileName, String encryptedMessage) {
        try {
            FileWriter myWriter = new FileWriter(fileName + ".txt");
            myWriter.write(encryptedMessage);
            myWriter.close();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }
}


