package com.molocziszko.webcrawler.utils;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Extractor {

    public static List<String> extract(Document doc) {
        String htmlText = "";
        if (doc != null) {
            htmlText = doc.body().text();
        }
        String[] array = htmlText.toLowerCase().split(" ");
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public static String[] toArr(String text) {
        return text.toLowerCase().split(" ");
    }

}
