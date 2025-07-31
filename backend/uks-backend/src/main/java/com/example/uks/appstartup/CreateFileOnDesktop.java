package com.example.uks.appstartup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateFileOnDesktop {

    protected static String getRadnomString(){

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()_+,.-";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();

        //Duzina generisane plain text sifre: 10 karaktera
        while(sb.length() < 11){
         int index = (int) (rnd.nextFloat() * characters.length());
         sb.append(characters.charAt(index));
        }
        String text = sb.toString();

        return text;
    }

    public static String createPassowordFile(){
        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + File.separator + "Desktop";
        File newFile = new File(desktopPath + File.separator + "uks-super-admin.txt");
        String firstPass = getRadnomString();

        try {
            if(newFile.createNewFile()) {
                System.out.println("File created successfully at: "+newFile.getAbsolutePath());
                if(newFile.length() == 0){
                    String filePath = newFile.getPath();

                    try(FileWriter fileWriter = new FileWriter(filePath)){
                        fileWriter.write(firstPass);
                        System.out.println("Plaint text pass written to: "+filePath);
                    } catch (IOException e) {
                        System.err.println("Error writing to file: "+ e.getMessage());
                    }
                } else {
                    System.out.println("File already exist at: "+newFile.getAbsolutePath());
                }
            }
        } catch (IOException e){
            System.err.println("An error occurred while assigning password from file.");
            e.printStackTrace();
        }

        return new BCryptPasswordEncoder().encode(firstPass);
    }


}
