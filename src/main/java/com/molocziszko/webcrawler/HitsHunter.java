package com.molocziszko.webcrawler;

import com.molocziszko.webcrawler.utils.Extractor;
import org.jsoup.nodes.Document;

import java.util.Map;

public class HitsHunter {
    private int totalHitsOnLink;
    private Map<String, Integer> hitsList;
    private String seedUrl;
    private static String keywordList;

    public HitsHunter(String url, String keywordList) {

    }

    protected void initHitsPairs(String keys) {
        var keywordsList = Extractor.toArr(keys);
        for (String key : keywordsList) {
            hitsList.put(key, 0);
        }
    }

    /*public String search(Document doc) {
        String[] matcherTextList = Extractor.extract(doc).toLowerCase().split(" ");

        initHitsPairs(keywordList);

        for (String word : matcherTextList) {
            if (getHitsList().containsKey(word)) {
                collectAllHits(word);
            }
        }

        // return progressViewer.printTotalResult(this);
    }*/

    public void collectAllHits(String word) {
        getHitsList().put(word, getHitsList().get(word) + 1);
        totalHitsOnLink += 1;
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

    public int getTotalHitsOnLink() {
        return totalHitsOnLink;
    }

    public String getCollection() {
        var collection = getHitsList();
        collection.put("Total", getTotalHitsOnLink());
        return Extractor.toStr(collection);
    }
}
