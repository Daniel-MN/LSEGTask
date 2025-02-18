package org.task.lseg;

import org.task.lseg.impl.CSVFileReader;
import org.task.lseg.impl.LogReader;
import org.task.lseg.impl.OutputFileWriter;
import org.task.lseg.impl.Properties;

public class LogsMonitorApp {
    public void start() {
        // read the csv and process the logs
        CSVFileReader.readCSV(Properties.getLogFilePath(), a -> new LogReader().readLog(a));
        // close the output file
        OutputFileWriter.getInstance().close();
    }
}
