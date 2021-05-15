package com.molocziszko.webcrawler.utils;

import org.jsoup.nodes.Document;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author molocziszko
 * @version 1.0
 *
 * This util class does secondary preparing operations with text data.
 */
public class Extractor {

    /**
     * Static method that extract {@code Document} and transform it to {@code List}
     *
     * @param doc raw HTML page.
     * @return A {@code List<String>} of strings retrieved from HTML.
     */
    public static List<String> extract(Document doc) {
        String htmlText = "";
        if (doc != null) {
            htmlText = doc.body().text();
        }
        String[] array = htmlText.toLowerCase().split(" ");
        return Arrays.stream(array).collect(Collectors.toList());
    }

    /**
     * Static method that transform {@code String} into {@code String[]}
     *
     * @param text raw HTML page.
     * @return A {@code List<String>} of strings retrieved from HTML.
     */
    public static String[] toArr(String text) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            var current = text.charAt(i);
            if (current == ',') {
                continue;
            }
            current = text.charAt(i);
            sb.append(current);
        }
        var str = sb.toString();

        return str.toLowerCase().split(" ");
    }
}
