package com.molocziszko.webcrawler;

import java.util.Objects;

public class CrawlURL {
    private String url;
    private int depth;

    public CrawlURL(String url, int depth) {
        this.url = url;
        this.depth = depth;
    }

    public String getUrl() {
        return url;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrawlURL crawlURL = (CrawlURL) o;
        return depth == crawlURL.depth && Objects.equals(url, crawlURL.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, depth);
    }
}
