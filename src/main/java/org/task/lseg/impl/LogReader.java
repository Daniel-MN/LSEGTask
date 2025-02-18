package org.task.lseg.impl;

public class LogReader {
    private static final int TIMESTAMP_INDEX = 0;
    private static final int DESCRIPTION_INDEX = 1;
    private static final int STATE_PROCESS_INDEX = 2;
    private static final int PID_INDEX = 3;
    private static final int NR_OF_PARTS = 4;

    public void readLog(String[] logParts) {
        // process the log
        if (logParts.length != NR_OF_PARTS) {
            throw new RuntimeException("Invalid log line: " + String.join(",", logParts));
        }

        String timestamp = logParts[TIMESTAMP_INDEX].trim();
        String description = logParts[DESCRIPTION_INDEX].trim();
        EStateProcess stateProcess = EStateProcess.valueOf(logParts[STATE_PROCESS_INDEX].trim().toUpperCase());
        int PID = Integer.parseInt(logParts[PID_INDEX].trim());
        System.out.println("Timestamp: " + timestamp + ", Description: " + description + ", State: " + stateProcess + ", PID: " + PID);
    }
}
