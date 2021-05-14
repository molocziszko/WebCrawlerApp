package com.molocziszko.webcrawler;

import com.molocziszko.webcrawler.utils.CSVWriter;
import com.molocziszko.webcrawler.utils.Printer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.*;
import java.util.stream.Collectors;

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

    public static void main(String[] args) {
        String terms = "Tesla, Musk, Gigafactory, Elon Mask";
        new WebCrawler(new CrawlURL("https://en.wikipedia.org/wiki/Elon_Musk", 0), terms).crawl();
    }

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
                CSVWriter.writeInAllStat(buffer);
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

        var sorted = topTenPages
                .stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());

        List<String> resArr = new ArrayList<>(10);
        for (HitsHunter hunter : sorted) {
            var res = Printer.printTotalResult(hunter);
            resArr.add(res);
        }
        CSVWriter.writeInTopTen(resArr);

        System.out.println("Finished!");
    }

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
}
