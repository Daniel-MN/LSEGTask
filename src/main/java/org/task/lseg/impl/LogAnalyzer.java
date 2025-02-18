package org.task.lseg.impl;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzer {
    private static LogAnalyzer instance;
    private List<Process> processes;
    private Map<Integer, Integer> processMap;
    private LocalTime lastTimestamp;
    private final int alertWarningThreshold;
    private final int alertErrorThreshold;
    private int lastWarningLogged;
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

    }
}
