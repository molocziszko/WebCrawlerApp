package com.molocziszko.webcrawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerTest {
    final int MAX_DEPTH = 8;
    final int MAX_VISITED_PAGES = 5;

    final String url = "https://www.nationalgeographic.com/impact";
    final String terms = "human, explorers, educators, elon";
    WebCrawler webCrawler = new WebCrawler(new CrawlURL(url, 0), terms);

    @Test
    void crawl() {


       /* final Deque<CrawlURL> listOfPagesToCrawl = new ArrayDeque<>();
        final Deque<String> listOfVisitedPages = new ArrayDeque<>();
        final Deque<HitsHunter> topTenPages = new ArrayDeque<>();
        WebCrawler webCrawler = null;
        new WebCrawler(new CrawlURL(url, 0), terms).crawl();*/


        Assertions.assertEquals(5, webCrawler.getListOfVisitedPages().size());
    }
}
