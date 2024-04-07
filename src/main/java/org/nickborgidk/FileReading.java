package org.nickborgidk;

import java.awt.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReading {

    public Scanner pickDirectory() throws FileNotFoundException {
        /*Creates a new file picker frame and only accepts a .txt, it then returns a Scanner object with the directory of the file */
        String selectedFilePath = "";
        FileDialog filePicker = new FileDialog((Frame) null);

        while (!selectedFilePath.contains("txt")){ //repeats until .txt extension is found
            filePicker.setVisible(true);
            if (filePicker.getFile() != null) {
                selectedFilePath = filePicker.getDirectory() + filePicker.getFile();
            }
        }
        return new Scanner(new File(selectedFilePath));
    }

    public String readFile(Scanner sc) throws FileNotFoundException {
        /*Scans each line and appends it to a string, it is then returned*/
        String fileData = "";
        while(sc.hasNextLine()){
            fileData += sc.nextLine();
        }
        return fileData;
    }
}
