package org.task.lseg.impl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTest {

    @Test
    void readCSV() {
        CSVFileReader.readCSV(".\\src\\main\\resources\\test.csv", a -> {
            assertEquals("11:35:23", a[0].trim());
            assertEquals("scheduled task 032", a[1].trim());
            assertEquals("START", a[2].trim());
            assertEquals("37980", a[3].trim());
        });
    }
}