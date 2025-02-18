package org.task.lseg.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

public class CSVFileReader {
    public static void readCSV(String filePath, Consumer<String[]> lineProcessor) {
        // read the file and process each line
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                lineProcessor.accept(fields);
            }
        } catch (IOException e) {
           throw new LogsMonitorException("Error reading the file: " + filePath, e);
        }
    }
}
