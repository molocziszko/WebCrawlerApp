package com.molocziszko.webcrawler;

import com.molocziszko.webcrawler.utils.Extractor;
import org.jsoup.nodes.Document;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class HitsHunter {
    private static String keywordList;
    private final Map<String, Integer> hitsList;
    private String seedUrl;

    public HitsHunter(String url, String keywordList) {
        hitsList = new LinkedHashMap<>();
        HitsHunter.keywordList = keywordList;
        initHitsPairs(url);
    }

    private void initHitsPairs(String url) {
        this.seedUrl = url;
        var keywordsList = Extractor.toArr(keywordList);
        for (String key : keywordsList) {
            hitsList.put(key, 0);
        }
    }

    public void search(Document doc) {
        var matcherTextList = Extractor.extract(doc);
        var keys = Extractor.toArr(getKeywordList());
        initHitsPairs(seedUrl);

        for (String key : keys) {
            int count = Collections.frequency(matcherTextList, key);
            hitsList.put(key, count);
        }
    }

    public Map<String, Integer> getHitsList() {
        return hitsList;
    }

    public String getKeywordList() {
        return keywordList;
    }

    public String getSeedUrl() {
        return seedUrl;
    }

    public String getCollection() {
        var collection = getHitsList();
        StringBuilder sb = new StringBuilder();
        for (int val : collection.values()) {
            sb.append(val);
            sb.append("; ");
        }
        return sb.toString();
    }
}
