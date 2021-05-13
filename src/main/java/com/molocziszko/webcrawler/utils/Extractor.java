package com.molocziszko.webcrawler.utils;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.Map;

public class Extractor {

    public static String extract(Document doc) {
        String htmlText = "";
        if (doc != null) {
            htmlText = doc.body().text();
        }
        return htmlText;
    }

    public static String[] toArr(String text) {
        return text.toLowerCase().split(" ");
    }

    public static String toStr(Map<String, Integer> list) {

        return list.toString();
    }
}
