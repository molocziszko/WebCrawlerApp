package com.molocziszko.webcrawler.utils;

import com.molocziszko.webcrawler.service.HitsHunter;

import java.util.Collection;

/**
 * @author molocziszko
 * @version 1.0
 *
 * Util class for printing output both in console and file.
 */
public class Printer {

    /**
     * Static method that print out parameters in console
     *
     * @param url the URL that we're crawling at this point.
     * @param depth the current depth of the crawl processing.
     * @param listOfPages pages that already crawled and processed.
     */
    public static void printProcess(String url, int depth, Collection<?> listOfPages) {
        System.out.println(">> Connected to: "
                + "[" + url + "]" + System.lineSeparator()
                + ">> Depth: " + depth
                + "; Links processed: " + listOfPages.size() + System.lineSeparator()
        );
    }

    /**
     * Static method that transform {@code HitsHunter} into {@code String} in a CSV-like format.
     *
     * @param map statistic object which will be writing into the file.
     * @return A {@code String} in a CSV-like format.
     */
    public static String printTotalResult(HitsHunter map) {

        return map.getSeedUrl()
                + "; " + map.getCollection();
    }
}
