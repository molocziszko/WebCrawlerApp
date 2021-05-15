package com.molocziszko.webcrawler.utils;

import com.molocziszko.webcrawler.service.HitsHunter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author molocziszko
 * @version 1.0
 * <p>
 * Class for getting top 10 pages sorted by total hits.
 */
public class TopTenReporter {

    /**
     * Static method that performs wrapping statistic data into CSV-readable form.
     *
     * @param sorted The list of statistic data objects sorted by total hits.
     */
    public static void writeReport(Collection<HitsHunter> sorted) {
        List<String> resArr = new ArrayList<>(10);
        for (HitsHunter hunter : sorted) {
            var res = Printer.printTotalResult(hunter);
            resArr.add(res);
        }
        CSVWriter.writeInFile(resArr, true);
    }

    /**
     * Static method that sort {@code Collection} of statistic data objects
     * by total hits.
     *
     * @param list unsorted {@code Collection} of statistic data objects.
     * @return A {@code List<HitsHunter>} that already sorted
     */
    public static List<HitsHunter> getSortedTopTen(Collection<HitsHunter> list) {
        var sorted = list
                .stream()
                .sorted()
                .limit(10)
                .collect(Collectors.toList());
        return sorted;
    }
}

