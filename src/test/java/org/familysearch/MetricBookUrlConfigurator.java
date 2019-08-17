package org.familysearch;

import java.util.HashMap;

public class MetricBookUrlConfigurator {
    private static String titleOfBook;

    public static String metricBookUrlSetter(HashMap hashMapOfTitlesAndLinks) {
        String url = (String)hashMapOfTitlesAndLinks.get(titleOfBook);
        return url;
    }

    public static void metricBookTitleGetter(String selectedTitleOfBook){
        titleOfBook = selectedTitleOfBook;
    }
}
