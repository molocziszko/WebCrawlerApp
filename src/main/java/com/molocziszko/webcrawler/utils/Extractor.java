package com.molocziszko.webcrawler.utils;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Extractor {

    public static List<String> extract(Document doc) {
        String htmlText = "";
        if (doc != null) {
            htmlText = doc.body().text();
        }
        String[] array = htmlText.toLowerCase().split(" ");
        List<String> result = Arrays.stream(array).collect(Collectors.toList());
        return result;
    }

    /*public static List<String> extract(Document doc) {
        String htmlText = "";
        if (doc != null) {
            htmlText = doc.body().text();
        }
        return Collections.singletonList(htmlText.toLowerCase());
    }*/

    public static String[] toArr(String text) {
        return text.toLowerCase().split(" ");
    }

}
