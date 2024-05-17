package org.nickborgidk.main;

import java.util.regex.Pattern;
public class SecretKeyValidation {

    public String validate(String iKey){
        /*Validates the potential key and whether its acceptable, in this case 1-16 chars in length and only allowing alphabetic and numerical characters
        if invalid it returns a default key*/
        Pattern keyRegex = Pattern.compile("^[a-zA-Z0-9]{16}$", Pattern.CASE_INSENSITIVE);
        if (iKey.isEmpty() || !keyRegex.matcher(iKey).find()){
            return "MCASTSTAMCASTSTA";
        }
        return iKey;
    }
}
