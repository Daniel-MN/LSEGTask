package org.task.lseg.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private static LogAnalyzer instance;
    // keep the processes in their start order, so that the thresholds can be checked in order
    // and the logs can be written in the correct order
    // keep also the ended processes to not remove them from list because it takes O(n) time
    private List<Process> processes;
    // map the PID to the index in the processes list
    // if the process is ended, it is removed from the map to allow using the same PID again
    private Map<Integer, Integer> processMap;
    private LocalTime lastTimestamp;
    private final int alertWarningThreshold;
    private final int alertErrorThreshold;
    // as the process are kept in the start order, the last process index for which a warning was logged is kept
    private int lastWarningLogged;
    // as the process are kept in the start order, the last process index for which an error was logged is kept
    private int lastErrorLogged;

    private LogAnalyzer() {
        this.processes = new ArrayList<>();
        this.processMap = new HashMap<>();
        this.alertWarningThreshold = Properties.getAlertWarningThreshold();
        this.alertErrorThreshold = Properties.getAlertErrorThreshold();
        this.lastWarningLogged = -1;
        this.lastErrorLogged = -1;
    }

    public static LogAnalyzer getInstance() {
        if (instance == null) {
            instance = new LogAnalyzer();
        }
        return instance;
    }


    public void analyzeLog(String timestamp, String description, EStateProcess stateProcess, int pid) {
        // process the log
        processMap.computeIfAbsent(pid, k -> {
            // if it's not in the map, it's a new process (the pid can be reused))
            Process process = new Process(pid);
            if (stateProcess == EStateProcess.START) {
                process.start(description, timestamp);
            } else {
                throw new RuntimeException("Invalid log line. Process with PID " + pid + " should start before ending.");
            }

            if (lastTimestamp != null && process.getTimestampStart().isBefore(lastTimestamp)) {
                throw new RuntimeException("The logging file has an invalid order of timestamps. " +
                        "Process with PID " + pid + " should start after the last process.");
            }
            processes.add(process);
            return processes.size() - 1;
        });

        lastTimestamp = LocalTime.parse(timestamp);

        // The thresholds are checked after each log line is processed.
        // The warnings and errors are logged when the thresholds are exceeded.
        checkThresholds();

        if (stateProcess == EStateProcess.END) {
            processes.get(processMap.get(pid)).end(description, timestamp);
            OutputFileWriter.getInstance().logProcessEnd(processes.get(processMap.get(pid)));
            // Do not need to keep track of the process anymore. It is ended.
            processMap.remove(pid);
        }
    }

    private void checkThresholds() {
        while (lastWarningLogged < processes.size()) {
            if (processes.get(lastWarningLogged + 1).getDurationUntil(lastTimestamp) > alertWarningThreshold) {
                if (!processes.get(lastWarningLogged + 1).isEnded()) {
                    OutputFileWriter.getInstance().logWarning(processes.get(lastWarningLogged + 1));
                }
                lastWarningLogged++;
            } else {
                break;
            }
        }

        while (lastErrorLogged < processes.size()) {
            if (processes.get(lastErrorLogged + 1).getDurationUntil(lastTimestamp) > alertErrorThreshold) {
                if (!processes.get(lastErrorLogged + 1).isEnded()) {
                    OutputFileWriter.getInstance().logError(processes.get(lastErrorLogged + 1));
                }
                lastErrorLogged++;
            } else {
                break;
            }
        }
    }
}
