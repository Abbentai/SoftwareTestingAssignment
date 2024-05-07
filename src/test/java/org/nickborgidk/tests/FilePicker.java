package org.nickborgidk.tests;

import org.nickborgidk.main.FileReading;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FilePicker {
    public Scanner pickDirectory() throws FileNotFoundException {
        /*Creates a new file picker frame and only accepts a .txt, it then returns a Scanner object with the directory of the file */
        String selectedFilePath = "";
        FileDialog filePicker = new FileDialog((Frame) null);
        filePicker.setTitle("Please select a key file with the .txt extension");

        while (true){ //repeats until .txt extension is found in the end of selectedFilePath
            filePicker.setVisible(true);
            if (filePicker.getFile() != null) {
                selectedFilePath = filePicker.getDirectory() + filePicker.getFile();
            }

            if (FileReading.validateFile(selectedFilePath)){
                return new Scanner(new File(selectedFilePath));
            }
        }
    }
}
