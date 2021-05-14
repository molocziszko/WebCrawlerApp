package com.molocziszko.webcrawler.utils;

import com.molocziszko.webcrawler.service.HitsHunter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class TopTenReporter {

    public static void writeReport(Collection<HitsHunter> sorted) {
        List<String> resArr = new ArrayList<>(10);
        for (HitsHunter hunter : sorted) {
            var res = Printer.printTotalResult(hunter);
            resArr.add(res);
        }
        CSVWriter.writeInTopTen(resArr);
    }

    public static List<HitsHunter> getSortedTopTen(Collection<HitsHunter> list) {
        var sorted = list
                .stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
        return sorted;
    }
}

