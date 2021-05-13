package com.molocziszko.webcrawler.utils;

import com.molocziszko.webcrawler.HitsHunter;

import java.util.Collection;

public class Printer {

    public static void printProcess(String url, int depth, Collection<?> listOfPages) {
        System.out.println(">> Connected to: "
                + "[" + url + "]" + System.lineSeparator()
                + ">> Depth: " + depth
                + "; Links processed: " + listOfPages.size() + System.lineSeparator()
        );
    }

    public static String printTotalResult(HitsHunter map) {

        return map.getSeedUrl()
                + "; " + map.getCollection();
    }
}
