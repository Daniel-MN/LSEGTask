package org.task.lseg.impl;

public class LogsMonitorException extends RuntimeException {
    public LogsMonitorException(String message) {
        super(message);
    }

    public LogsMonitorException(String message, Throwable cause) {
        super(message, cause);
    }
}
