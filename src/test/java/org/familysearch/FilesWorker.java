package org.familysearch;

import java.io.File;
import java.io.FileFilter;

public class FilesWorker {
    static File directory = new File(System.getenv("USERPROFILE") + "\\Downloads");
    static File[] myFiles = directory.listFiles();
    static File searchFile;

    private static File fileFinder() throws NullPointerException {
        searchFile = null;
        try {
            searchFile = myFiles[0];
            for (int i = 1; i != myFiles.length; i++) {
                if (searchFile.lastModified() < myFiles[i].lastModified()) {
                    searchFile = myFiles[i];
                }
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.printStackTrace();
        }
        return searchFile;
    }

    private static void fileRenamer(File oldFile, String newFileName) {
        File newFile = new File(System.getenv("USERPROFILE") + "\\Downloads\\" + newFileName + ".jpg");
        oldFile.renameTo(newFile);

    }

    public static void fileFinderAndRenamer(String fileName) {
        fileRenamer(fileFinder(), fileName);
    }
}
