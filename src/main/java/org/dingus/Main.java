package org.dingus;



import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws InvalidKeyException, FileNotFoundException {
        FileReading fileReader = new FileReading();
        SecretKeyValidation validation = new SecretKeyValidation();

        String fileContents = fileReader.readFile(fileReader.pickDirectory());
        //do key shit now pls ty


        fileContents = validation.validate(fileContents);

        TOTP totp = new TOTP();
        byte[] key = totp.parseSecret("dingus");
        totp.TOTPTime(key);



    }
}