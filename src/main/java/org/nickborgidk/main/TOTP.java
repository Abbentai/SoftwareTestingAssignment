package org.nickborgidk.main;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;

import java.security.InvalidKeyException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class TOTP {
    Clock clock = Clock.systemDefaultZone();
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //maybe add seconds here
    public void ThreeCodes(byte[] key, int offset) throws InvalidKeyException {
        /*Calls to generate 3 codes, the current code of the method being called, along with 2 codes for +-offset*/
        System.out.println("Past Code is: " + TotpBuilder(key, -offset, 0));
        System.out.println("Current Code is: " + TotpBuilder(key, 0, 0));
        System.out.println("Future Code is: " + TotpBuilder(key, offset, 0));
    }

    public String CodeAtDate(byte[] key, LocalDateTime dateTime) throws InvalidKeyException {
        /*This calculates the difference in time from a specific date given and the current system time, the calculation and result is in unix time*/
        ZoneId zoneId = ZoneId.of("Europe/Malta"); //specifying timezone and format

        long current = LocalDateTime.now().atZone(zoneId).toInstant().toEpochMilli(); //This converts local time to unix time for easier calculation
        long dateGiven = dateTime.atZone(zoneId).toInstant().toEpochMilli();
        long difference = (current - dateGiven);

        return (TotpBuilder(key, 0, difference));
    }

    public String TotpBuilder(byte[] key, int offset, long differenceGiven) throws InvalidKeyException {
        /*This is specifying the properties of the TOTPGenerator from this library: https://github.com/BastiaanJansen/otp-java*/
        TOTPGenerator totp = new TOTPGenerator.Builder(key)
                .withHOTPGenerator(builder -> {
                    builder.withPasswordLength(6);
                    builder.withAlgorithm(HMACAlgorithm.SHA1); // SHA256 and SHA512 are also supported
                })
                .withPeriod(Duration.ofSeconds(30))
                .withClock(clock)
                .build();
        return GenerateCode(totp, offset, differenceGiven);
    }

    public String GenerateCode(TOTPGenerator totp, int offset, long timeDifference){
        /*Generates the code, removes any difference in time and converts it to seconds*/
        return totp.at((System.currentTimeMillis() - timeDifference) / 1000 + offset);
    }
}


