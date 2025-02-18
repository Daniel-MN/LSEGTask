package org.task.lseg.impl;

public class Properties {
    private static final String LOG_FILE_PATH = ".\\src\\main\\resources\\logs.log";
    private static String logFilePath;
    private static final String OUTPUT_FILE_PATH = ".\\src\\main\\resources\\output.txt";
    private static String outputFilePath;
    private static final int ALERT_WARNING_THRESHOLD = 300;
    private static int alertWarningThreshold;
    private static final int ALERT_ERROR_THRESHOLD = 600;
    private static int alertErrorThreshold;

    public static String getLogFilePath() {
        if (logFilePath == null) {
            logFilePath = System.getProperty("logFilePath");
            if (logFilePath == null || logFilePath.isEmpty()) {
                logFilePath = LOG_FILE_PATH;
            }
        }

        return logFilePath;
    }

    public static String getOutputFilePath() {
        if (outputFilePath == null) {
            outputFilePath = System.getProperty("outputFilePath");
            if (outputFilePath == null || outputFilePath.isEmpty()) {
                outputFilePath = OUTPUT_FILE_PATH;
            }
        }
        return outputFilePath;
    }

    public static int getAlertWarningThreshold() {
        if (alertWarningThreshold == 0) {
            try {
                alertWarningThreshold = Integer.parseInt(System.getProperty("alertWarningThreshold"));
            } catch (NumberFormatException e) {
                alertWarningThreshold = ALERT_WARNING_THRESHOLD;
            }
        }
        return alertWarningThreshold;
    }

    public static int getAlertErrorThreshold() {
        if (alertErrorThreshold == 0) {
            try {
                alertErrorThreshold = Integer.parseInt(System.getProperty("alertErrorThreshold"));
            } catch (NumberFormatException e) {
                alertErrorThreshold = ALERT_ERROR_THRESHOLD;
            }
        }
        return alertErrorThreshold;
    }
}
