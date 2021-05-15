package com.molocziszko.webcrawler.model;

/**
 * @author molocziszko
 * @version 1.0
 *
 * POJO class in the form of record for keeping current
 * crawling page and depth of traversing.
 */
public record CrawlURL(String url, int depth) {
}
