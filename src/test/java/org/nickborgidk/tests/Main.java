package org.nickborgidk.tests;

import org.nickborgidk.main.EmptyFileException;
import org.nickborgidk.main.FileReading;
import org.nickborgidk.main.SecretKeyValidation;
import org.nickborgidk.main.TOTP;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, FileNotFoundException, EmptyFileException, UnsupportedEncodingException {
        FileReading fileReader = new FileReading();
        SecretKeyValidation validation = new SecretKeyValidation();
        TOTP totp = new TOTP();

        String fileContents = fileReader.readFile(pickDirectory());

        fileContents = validation.validate(fileContents);

        byte[] key = fileContents.getBytes(StandardCharsets.UTF_8);
        totp.ThreeCodes(key, 30);
        LocalDateTime date = LocalDateTime.parse("2023-12-25 07:00:00", totp.formatter);
        System.out.println("The TOTP Code for Date " + date.format(totp.formatter) + " is: " + totp.CodeAtDate(key, date));
    }

    public static Scanner pickDirectory() throws FileNotFoundException {
        /*Creates a new file picker frame and only accepts a .txt, it then returns a Scanner object with the directory of the file */
        String selectedFilePath = "";
        FileDialog filePicker = new FileDialog((Frame) null);
        filePicker.setTitle("Please select a key file with the .txt extension");

        while (true){ //repeats until .txt extension is found in the end of selectedFilePath
            filePicker.setVisible(true);
            if (filePicker.getFile() != null) {
                selectedFilePath = filePicker.getDirectory() + filePicker.getFile();
            }

            if (FileReading.validateFile(selectedFilePath)){
                return new Scanner(new File(selectedFilePath));
            }
        }
    }
}