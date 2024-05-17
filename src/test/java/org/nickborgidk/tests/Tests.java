package org.nickborgidk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.nickborgidk.main.EmptyFileException;
import org.nickborgidk.main.TOTP;
import org.nickborgidk.main.SecretKeyValidation;
import org.nickborgidk.main.FileReading;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.time.LocalDateTime;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    TOTP totp = new TOTP();
    SecretKeyValidation validation = new SecretKeyValidation();
    FileReading fileReader = new FileReading();
    byte[] key;
    @BeforeEach
    void testKey(){
        String strKey = "MCASTSTAMCASTSTA";
        key = strKey.getBytes();
    }
    @Test
    void DateTest1() throws InvalidKeyException {
        assertEquals("400136", totp.CodeAtDate(key, LocalDateTime.parse("2023-12-25 07:00:00", totp.formatter)), "standard date test");
    }

    @Test
    void DateTest2() throws InvalidKeyException {
        assertEquals("390900", totp.CodeAtDate(key, LocalDateTime.parse("2024-01-25 10:00:00", totp.formatter)), "standard date test");
    }

    @Test
    void DateTest3() throws InvalidKeyException {
        assertEquals("764104", totp.CodeAtDate(key, LocalDateTime.parse("2024-02-25 13:00:00", totp.formatter)), "standard date test");
    }

    @Test
    void validKey(){
        assertEquals("helloTHERE123456", validation.validate("helloTHERE123456"), "standard key string");
    }

    @Test
    void emptyKey(){
        assertEquals("MCASTSTAMCASTSTA", validation.validate(""), "empty key string");
    }

    @Test
    void invalidKeyLength(){
        assertEquals("MCASTSTAMCASTSTA", validation.validate("helloTHEREhowareyoudoingtodaydave"), "long key string");
    }

    @Test
    void invalidKeyCharacters(){
        assertEquals("MCASTSTAMCASTSTA", validation.validate(":D"), "long key string");
    }

    @Test
    void standardFileValidation(){
        assertTrue(fileReader.validateFile("key.txt"), "standard text file example");
    }

    @Test
    void invalidFileValidation(){
        assertFalse(fileReader.validateFile("key.csv"), "different file extension");
    }

    @Test
    void midExtensionValidation(){
        assertFalse(fileReader.validateFile("key.txt.csv"));
    }

    @Test
    void standardFileReading() throws EmptyFileException {
        Scanner sc = new Scanner("yaycodetime");
        assertEquals("yaycodetime", fileReader.readFile(sc), "testing reading of a standard string (file stub)");
    }
    @Test
    void noFileReading(){
        Scanner sc = new Scanner("");
        assertThrows(EmptyFileException.class, () -> {
            fileReader.readFile(sc);
        });
    }

    @Test
    void multiLineFileReading() throws EmptyFileException {
        Scanner sc = new Scanner("    yaycodetime\n dingus 2");
        assertEquals("yaycodetimedingus2", fileReader.readFile(sc), "Reading file data with multiple lines and combining them");
    }



    //issa add some more tests regarding the regex and file reading stuff
}
