package org.task.lseg.impl;

import java.time.Duration;
import java.time.LocalTime;

public class Process {
    private long PID;
    // maybe the two description are different
    private String descriptionStart;
    private String descriptionEnd;
    private LocalTime timestampStart;
    private LocalTime timestampEnd;
    private long duration;

    public Process(long PID) {
        // throw an exception if PID is negative
        if (PID < 0) {
            throw new LogsMonitorException("PID must be a positive number.");
        }
        this.PID = PID;
    }

    public void start(String descriptionStart, String timestampStart) {
        this.descriptionStart = descriptionStart;
        this.timestampStart = LocalTime.parse(timestampStart);
        duration = 0;
    }

    public void end(String descriptionEnd, String timestampEnd) {
        if (timestampStart == null) {
            throw new LogsMonitorException("The process was never started!");
        }

        this.descriptionEnd = descriptionEnd;
        this.timestampEnd = LocalTime.parse(timestampEnd);

        if (timestampStart.isBefore(this.timestampEnd)) {
            duration = Duration.between(timestampStart, this.timestampEnd).getSeconds();
        } else {
            throw new LogsMonitorException("The end timestamp must be after the start timestamp for PID: " + PID);
        }
    }

    public long getPID() {
        return PID;
    }

    public String getDescriptionStart() {
        return descriptionStart;
    }

    public String getDescriptionEnd() {
        return descriptionEnd;
    }

    public LocalTime getTimestampStart() {
        return timestampStart;
    }

    public LocalTime getTimestampEnd() {
        return timestampEnd;
    }

    public long getDuration() {
        return duration;
    }

    public long getDurationUntil(LocalTime lastTimestamp) {
        return Duration.between(timestampStart, lastTimestamp).getSeconds();
    }

    public boolean isEnded() {
        return timestampEnd != null;
    }
}
