package com.molocziszko.webcrawler;

import com.molocziszko.webcrawler.model.CrawlURL;
import com.molocziszko.webcrawler.service.WebCrawler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WebCrawlerTest {
    final int MAX_DEPTH = 8;
    final int MAX_VISITED_PAGES = 5;

    final String url = "https://www.nationalgeographic.com/impact";
    final String terms = "human, explorers, educators, elon";
    WebCrawler webCrawler = new WebCrawler(new CrawlURL(url, 0), terms);

    @Test
    void crawl() {

        Assertions.assertEquals(5, webCrawler.getListOfVisitedPages().size());
        Assertions.assertEquals(5, );
    }
}
