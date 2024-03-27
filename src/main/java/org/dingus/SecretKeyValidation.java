package org.dingus;

import javax.crypto.SecretKey;
import java.util.regex.Pattern;

public class SecretKeyValidation {

    public String validate(String iKey){
        Pattern keyRegex = Pattern.compile("^[a-zA-Z0-9]{1,16}$", Pattern.CASE_INSENSITIVE);
        if (!keyRegex.matcher(iKey).find()){
            return "MCASTSTAMCASTSTA";
        }
        return iKey;
    }

}
