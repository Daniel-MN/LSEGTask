package org.task.lseg.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LogReaderTest {

    @Test
    void readLog() {
        LogReader logReader = new LogReader();
        logReader.readLog(new String[]{"11:35:23", "scheduled task 032", "START", "37980"});
        LogAnalyzer logAnalyzer = mock(LogAnalyzer.class);
        doAnswer(invocation -> {
            assertEquals("11:35:23", invocation.getArgument(0));
            assertEquals("scheduled task 032", invocation.getArgument(1));
            assertEquals(EStateProcess.START, invocation.getArgument(2));
            assertEquals(37980, (Integer) invocation.getArgument(3));
            return null;
        }).when(logAnalyzer).analyzeLog(anyString(), anyString(), any(EStateProcess.class), anyInt());
    }
}