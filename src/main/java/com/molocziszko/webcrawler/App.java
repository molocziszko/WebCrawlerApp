package com.molocziszko.webcrawler;

public class App {

    /**
     * This app find and count the number of matches of predefined terms
     * on the current crawled web page. These actions repeat until max pages
     * will be in the CSV file with statistics output.
     */
    public static void main(String[] args) {
        String terms = "Tesla, Musk, Gigafactory, Elon Mask";

        // Create an object that count the images.
        new WebCrawler(new CrawlURL("https://en.wikipedia.org/wiki/Elon_Musk", 0), terms).crawl();
    }
}
