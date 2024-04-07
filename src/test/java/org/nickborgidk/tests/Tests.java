package org.nickborgidk.tests;

import org.junit.jupiter.api.BeforeEach;
import org.nickborgidk.TOTP;
import org.nickborgidk.SecretKeyValidation;
import org.nickborgidk.FileReading;
import org.junit.jupiter.api.Test;

import java.security.InvalidKeyException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class Tests {
    TOTP totp = new TOTP();
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


    //issa add some more tests regarding the regex and file reading stuff
}
