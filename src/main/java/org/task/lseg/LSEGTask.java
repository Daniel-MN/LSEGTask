package org.task.lseg;

public class LSEGTask {
    public static void main(String[] args) {
        String logFilePath = System.getProperty("logFilePath");
        String outputFilePath = System.getProperty("outputFilePath");
        String alertWarningThreshold = System.getProperty("alertWarningThreshold");
        String alertErrorThreshold = System.getProperty("alertErrorThreshold");

        if (logFilePath == null || outputFilePath == null || alertWarningThreshold == null || alertErrorThreshold == null) {
             throw new RuntimeException("Please provide the log file path, output file path, alert warning threshold and alert error threshold");
        }

        LogsMonitorApp logsMonitorApp = new LogsMonitorApp();
        logsMonitorApp.start();
    }
}