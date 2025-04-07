package com.example.uks.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.*;
import java.util.Random;

public class CreateFileOnDesktop {

    protected static String getRandomString(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+,.-";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
//      Duzina generisane plain text sifre: 10 karaktera
        while (sb.length() < 11) {
            int index = (int) (rnd.nextFloat() * characters.length());
            sb.append(characters.charAt(index));
        }
        String text = sb.toString();

        return text;
    }

    public static String createPasswordFile() {
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";
        File newFile = new File(desktopPath + File.separator + "uks-super-admin.txt");
        String firstPass = getRandomString();
        try {
            if (newFile.createNewFile()) {
                System.out.println("File created successfully at: " + newFile.getAbsolutePath());
                if(newFile.length() == 0){
                    String filePath = newFile.getPath();

                    try(FileWriter writer = new FileWriter(filePath)){

                        writer.write(firstPass);
                        System.out.println("Plain text written to: " + filePath);

                    } catch (IOException e) {
                        System.err.println("Error writing to file: " + e.getMessage());
                    }
                }
            } else {
                System.out.println("File already exists at: " + newFile.getAbsolutePath());
                try(BufferedReader br = new BufferedReader(new FileReader(newFile.getAbsolutePath()))){
                    String line;

                    while((line = br.readLine()) != null){
                        firstPass = line;
                    }
                } catch (IOException e) {
                    System.err.println("An error occurred while assigning password from file.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred while creating the file.");
            e.printStackTrace();
        }
        return new BCryptPasswordEncoder().encode(firstPass);
    }
}