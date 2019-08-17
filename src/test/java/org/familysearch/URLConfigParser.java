package org.familysearch;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class URLConfigParser {
    private String[] output;
    private File file;
    private String[] titleAndLink;
    private Map<String, String> titlesAndLinks = new HashMap<String, String>();

    public Map<String, String> getListOfBooks(String path) {
        file = new File(path);

        try {
            FileReader filereader = new FileReader(path);
            BufferedReader bufferedreader = new BufferedReader(filereader);
            String line = bufferedreader.readLine();
            while (line != null) {
                //Try to parse integer from the String line
                try {
                    titleAndLink = line.split(";");
                    titlesAndLinks.put(titleAndLink[0], titleAndLink[1]);
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
        return titlesAndLinks;
    }
}
