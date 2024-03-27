package org.dingus;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.TOTPGenerator;
import com.eatthepath.otp.TimeBasedOneTimePasswordGenerator;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;


public class TOTP {

    /*public SecretKey convertStringToSecretKeyto(String encodedKey) {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return originalKey;
    }*/

    public byte[] parseSecret(String keyInput){
        return keyInput.getBytes();
    }

    public void TOTPTime(byte[] key) throws InvalidKeyException {
        TOTPGenerator totp = new TOTPGenerator.Builder(key)
                .withHOTPGenerator(builder -> {
                    builder.withPasswordLength(6);
                    builder.withAlgorithm(HMACAlgorithm.SHA1); // SHA256 and SHA512 are also supported
                })
                .withPeriod(Duration.ofSeconds(30))
                .build();

        try {
            String code = totp.now();
            System.out.println(code);
            //boolean isValid = totp.verify(code);
        } catch (IllegalStateException e) {
            // Handle error
        }
    }
    /*public void TOTPTime(SecretKey key) throws InvalidKeyException {
        final TimeBasedOneTimePasswordGenerator totp = new TimeBasedOneTimePasswordGenerator();
        //totp.time

        final Instant now = Instant.now();
        final Instant later = now.plus(totp.getTimeStep()); //load your own time step

        System.out.println("Current password: " + totp.generateOneTimePasswordString(key, now));
        System.out.println("Future password:  " + totp.generateOneTimePasswordString(key, later));
    }*/
}


