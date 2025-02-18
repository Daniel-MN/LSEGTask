package org.task.lseg.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileWriter {
    private static OutputFileWriter instance;
    private final String outputFilePath;
    private BufferedWriter writer;

    private OutputFileWriter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        try {
            this.writer = new BufferedWriter(new FileWriter(outputFilePath, false));
        } catch (IOException e) {
            throw new LogsMonitorException("Error creating the output file: " + outputFilePath, e);
        }
    }

    public static OutputFileWriter getInstance() {
        if (instance == null) {
            instance = new OutputFileWriter(Properties.getOutputFilePath());
        }

        return instance;
    }

    public void logWarning(Process process) {
        try {
            writer.write("WARNING: Process " + process.getPID() + " exceeded warning threshold\n");
            writer.flush();
        } catch (IOException e) {
            throw new LogsMonitorException("Error writing to the output file: " + outputFilePath, e);
        }
    }

    public void logError(Process process) {
        try {
            writer.write("ERROR: Process " + process.getPID() + " exceeded error threshold\n");
            writer.flush();
        } catch (IOException e) {
            throw new LogsMonitorException("Error writing to the output file: " + outputFilePath, e);
        }
    }

    public void logProcessEnd(Process process) {
        try {
            writer.write("Process " + process.getPID() + " ended with duration " + process.getDuration() + " seconds\n");
            writer.flush();
        } catch (IOException e) {
            throw new LogsMonitorException("Error writing to the output file: " + outputFilePath, e);
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            throw new LogsMonitorException("Error writing to the output file: " + outputFilePath, e);
        }
    }
}
