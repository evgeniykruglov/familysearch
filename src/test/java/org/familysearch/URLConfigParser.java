package org.familysearch;

import java.io.*;

public class URLConfigParser {
    private String[] output;
    private File file;

    public String[] listOfBooks(String path) {
        file = new File(path);

        try {
            FileReader filereader = new FileReader(path);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String line = bufferedreader.readLine();
            //output = new String[line];
            while (line != null) {
                //Try to parse integer from the String line
                try {
                    System.out.println(line);
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                    System.exit(1);
                }
                line = bufferedreader.readLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ioexception)
        {
            System.out.println("File input error occured!");
            ioexception.printStackTrace();
        }
        return output;
    }

}
