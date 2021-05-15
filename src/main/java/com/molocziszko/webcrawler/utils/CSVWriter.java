package com.molocziszko.webcrawler.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author molocziszko
 * @version 1.0
 * <p>
 * Class for writing data into a file.
 */
public class CSVWriter {

    /**
     * This method serve for writing resulting data into a file.
     */
    public static void writeInFile(List<String> result, boolean isTopTen) {
        String fileForAllStat = "src/main/resources/all_stat_report.csv";
        String fileForTopTenStat = "src/main/resources/top10_stat_report.csv";

        String file = isTopTen ? fileForTopTenStat : fileForAllStat;

        try {
            for (String data : result) {
                Files.writeString(
                        Paths.get(file),
                        data + System.lineSeparator(),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
