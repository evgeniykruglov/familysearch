package org.familysearch;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FileParser {
    //String path;
    public String[] getLogPass (String path) {
        File file = new File(path);
        String[] dataFromFile = new String[2];
        try {
            FileReader fr = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fr);
            try {
                String line;
                int countOfLines = 0;
                do {
                    line = bufferReader.readLine();
                    if (countOfLines <= 1) {
                        dataFromFile[countOfLines] = line;
                    }
                    countOfLines++;
                } while (line != null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not fount " + ex);
            ex.printStackTrace();
        }
//        if (type == "login") {
//            String outputValue = dataFromFile[0];
//            return outputValue;
//        } else if (type == "password") {
//            String outputValue = dataFromFile[1];
//            return outputValue;
//        }
        return dataFromFile;
    }
}
