package com.molocziszko.webcrawler.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class CSVWriter {

    /**
     * This method serve for write resulting data in file.
     */
    public static void writeInAllStat(List<String> result) {
        String file = "src/main/resources/all_stat_report.csv";
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

    public static void writeInTopTen(List<String> result) {
        String file = "src/main/resources/top10_stat_report.csv";
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
