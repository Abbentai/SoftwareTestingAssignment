package org.nickborgidk.main;

import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class FileReading {
    static Pattern fileRegex = Pattern.compile(".txt$", Pattern.CASE_INSENSITIVE);

    public static boolean validateFile(String filePath){
        if (fileRegex.matcher(filePath).find()){
            return true;
        }
        return false;
    }

    public String readFile(Scanner sc) throws EmptyFileException {
        /*Scans each line and appends it to a string, it is then returned*/
        String fileData = "";
        while(sc.hasNextLine()){
            fileData += sc.nextLine();
        }
        if (fileData.isEmpty())
            throw new EmptyFileException();
        return fileData.replace(" ", "");
    }
}
