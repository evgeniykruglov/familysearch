package org.familysearch;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class FileParser {
    //String path;
    public String login;
    public String password;

    public void readLogPass(String path) {
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
        this.login = dataFromFile [0];
        this.password = dataFromFile [1];
        //return dataFromFile;
    }
    public String getLogin() {
        return this.login;
    }
    public String getPassword() {
        return this.password;
    }
}
