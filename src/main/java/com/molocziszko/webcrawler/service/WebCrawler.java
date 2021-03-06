package com.molocziszko.webcrawler.service;

import com.molocziszko.webcrawler.model.CrawlURL;
import com.molocziszko.webcrawler.utils.CSVWriter;
import com.molocziszko.webcrawler.utils.Printer;
import com.molocziszko.webcrawler.utils.TopTenReporter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;

/**
 * @author molocziszko
 * @version 1.0
 * <p>
 * This class find and count the number of matches of predefined terms.
 * It aims to crawl, fetch HTML representation and pass produced data further to
 */
public class WebCrawler {
    private static final int MAX_DEPTH = 8;
    private static final int MAX_VISITED_PAGES = 10_000;

    private final Deque<CrawlURL> listOfPagesToCrawl;
    private final Deque<String> listOfVisitedPages;
    private final String keywordList;
    private final Deque<HitsHunter> topTenPages;

    public WebCrawler(CrawlURL seedURL, String termsToSearch) {
        listOfPagesToCrawl = new ArrayDeque<>(10_000);
        listOfVisitedPages = new ArrayDeque<>(10_000);
        topTenPages = new ArrayDeque<>(10_000);
        listOfPagesToCrawl.add(seedURL);
        keywordList = termsToSearch;
    }

    /**
     * Main entry point into the logic for crawling pages.
     * It crawls the links, collect data in buffer, which then passed
     * to write statistic in a file.
     */
    public void crawl() {
        int crawledPages = 0;
        List<String> buffer = new ArrayList<>(100);
        while (!listOfPagesToCrawl.isEmpty() && listOfVisitedPages.size() < MAX_VISITED_PAGES) {
            CrawlURL currentLink = listOfPagesToCrawl.remove();
            var url = currentLink.url();
            var depth = currentLink.depth();
            HitsHunter hitsHunter = new HitsHunter(url, keywordList);

            var doc = getPage(depth, url, listOfVisitedPages);
            hitsHunter.search(doc);
            topTenPages.add(hitsHunter);

            Printer.printProcess(url, depth, listOfVisitedPages);
            var result = Printer.printTotalResult(hitsHunter);
            buffer.add(result);
            if (buffer.size() == 100) {
                CSVWriter.writeInFile(buffer, false);
                buffer = new ArrayList<>(100);
            }

            if (doc != null && depth <= MAX_DEPTH) {
                var nextDepth = depth + 1;
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if (!listOfVisitedPages.contains(next_link)) {
                        listOfPagesToCrawl.add(new CrawlURL(next_link, nextDepth));
                        crawledPages++;
                    }
                }
            }
        }
        List<HitsHunter> sorted = TopTenReporter.getSortedTopTen(topTenPages);
        TopTenReporter.writeReport(sorted);

        System.out.println("Finished!");
    }

    /**
     * Helper method that performs getting a page over HTTP using JSOUP library.
     *
     * @param depth The current depth of the crawl processing
     * @param url   The URL that we're crawling at this point
     * @return A {@code Document} that we pass to search method
     * @see HitsHunter#search(Document doc)
     */
    private Document getPage(int depth, String url, Deque<String> listOfPages) {

        try {
            Connection connection = Jsoup.connect(url);
            Document doc = connection.get();

            if (connection.response().statusCode() == 200) {
                if (Collections.frequency(listOfPages, url) == 0) {
                    listOfPages.add(url);
                }
                return doc;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public Deque<CrawlURL> getListOfPagesToCrawl() {
        return listOfPagesToCrawl;
    }

    public Deque<String> getListOfVisitedPages() {
        return listOfVisitedPages;
    }

    public Deque<HitsHunter> getTopTenPages() {
        return topTenPages;
    }
}
