package com.molocziszko.webcrawler;

import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class WebCrawlerTest {

    @Test
    void crawl() {
        final int MAX_DEPTH = 8;
        final int MAX_VISITED_PAGES = 10_000;

        final Deque<CrawlURL> listOfPagesToCrawl;
        final Deque<String> listOfVisitedPages;
        final String keywordList;
        final Deque<HitsHunter> topTenPages;
    }
}