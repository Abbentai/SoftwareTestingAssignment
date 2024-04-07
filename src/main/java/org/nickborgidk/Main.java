package org.nickborgidk;

import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, FileNotFoundException {
        FileReading fileReader = new FileReading();
        SecretKeyValidation validation = new SecretKeyValidation();
        TOTP totp = new TOTP();

        String fileContents = fileReader.readFile(fileReader.pickDirectory());

        fileContents = validation.validate(fileContents);

        byte[] key = fileContents.getBytes();
        totp.ThreeCodes(key, 30);
        LocalDateTime date = LocalDateTime.parse("2023-12-25 07:00", totp.formatter);
        System.out.println("The TOTP Code for Date " + date.format(totp.formatter) + " is: " + totp.CodeAtDate(key, LocalDateTime.parse("2023-12-25 07:00", totp.formatter)));


    }
}