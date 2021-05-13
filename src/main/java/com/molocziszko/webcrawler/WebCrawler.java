package com.molocziszko.webcrawler;

import com.molocziszko.webcrawler.utils.CSVWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayDeque;
import java.util.Deque;

public class WebCrawler {
    private static final int MAX_DEPTH = 8;
    private static final int MAX_VISITED_PAGES = 10_000;

    private final Deque<CrawlURL> listOfPagesToCrawl;
    private final Deque<String> listOfVisitedPages;
    private final String keywordList;

    public WebCrawler(CrawlURL seedURL, String termsToSearch) {
        this.listOfPagesToCrawl = new ArrayDeque<>(10_000);
        this.listOfVisitedPages = new ArrayDeque<>(10_000);
        listOfPagesToCrawl.add(seedURL);
        keywordList = termsToSearch;
    }

    public static void main(String[] args) {
        String terms = "Tesla, Musk, Gigafactory, Elon Mask";
        new WebCrawler(new CrawlURL("https://en.wikipedia.org/wiki/Elon_Musk", 0), terms).crawl();
    }

    public void crawl() {
        int crawledPages = 0;
        while (!listOfPagesToCrawl.isEmpty() && listOfVisitedPages.size() <= MAX_VISITED_PAGES) {
            CrawlURL currentLink = listOfPagesToCrawl.remove();
            var url = currentLink.getUrl();
            var depth = currentLink.getDepth();
            HitsHunter hitsHunter = new HitsHunter(currentLink.getUrl(), keywordList);

            var doc = getPage(depth, url, listOfVisitedPages);
            var shots = hitsHunter.search(doc);
            var nextDepth = depth + 1;

            printer.printTotalResult(hitsHunter);
            CSVWriter.serialize(shots);

            if (doc != null && depth <= MAX_DEPTH) {
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if (!listOfVisitedPages.contains(next_link)) {
                        listOfPagesToCrawl.add(new CrawlURL(next_link, nextDepth));
                        crawledPages++;
                    }
                }
            }
        }
        System.out.println("Finished!");
    }

    /**
     * @return A Document containing the html page required to parse later.
     */
    private Document getPage(int depth, String url, Deque<String> listOfPages) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var statusCode = response.statusCode();
            var textToParse = response.body();

            Document doc = Jsoup.parse(textToParse);

            if (statusCode == 200) {
                if (!listOfPages.contains(url)) {
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
