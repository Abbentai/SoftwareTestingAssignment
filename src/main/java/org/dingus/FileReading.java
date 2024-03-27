package org.dingus;

import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {

    public String pickDirectory(){
        String selectedFilePath = "";
        FileDialog filePicker = new FileDialog((Frame) null);

        while (selectedFilePath.isEmpty() || !selectedFilePath.contains("txt")){
            filePicker.setVisible(true);
            if (filePicker.getFile() != null) {
                selectedFilePath = filePicker.getDirectory() + filePicker.getFile();
            }
        }
        return selectedFilePath;
    }

    public String readFile(String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }

        return "a";

    }


}
