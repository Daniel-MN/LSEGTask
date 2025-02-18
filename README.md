# LSEGTask
# LogMonitor
# Musuroi Daniel-Nicusor
# 2025

 This is a Logs Monitor App, which reads a CSV file and as it reads each line, it processes the logs, adding
 information/reports to the output file.

 The app reads the logs from a file line by line, and for each line, it processes the log. If the log is related to a 
starting process, then add the process to the list of processes. If the log is related to an ending process, then check 
if the process is in the list of processes. If it is, then calculate the duration of the process and log it the output.
For each log line, the app checks if the duration of the processes is greater that the thresholds. Having the processes 
in the order of their starting point, the app will log an alert as soon as the threshold is reached.
 
***Pre-requisites:***
- Java 17
- Maven

***How to run the app:***
1. Clone the repository
2. Open a terminal and navigate to the root of the project
3. Run the following command: `mvn clean install`
4. Run the following command: `java -DlogFilePath=<path-to-log-file> -DoutputFilePath=<path-to-output-file> 
                                     -DalertWarningThreshold=<alert-warning-threshold in seconds>
                                     -DalertErrorThreshold=<alert-error-threshold in seconds>
                                     -jar target/LogMonitor-1.0-SNAPSHOT.jar`
    All the parameters are optional, if not provided, the app will use the default values:
    - logFilePath: `.\src\\main\\resources\\logs.log`
    - outputFilePath: `.\src\\main\\resources\\output.log`
    - alertWarningThreshold: 300 seconds = 5 minutes
    - alertErrorThreshold: 600 seconds = 10 minutes

***Alternative way to run the app -- in powershell -- using powerhshell script:***
1. Clone the repository
2. Open a terminal and navigate to the root of the project
3. Run the following command: `.\run.ps1 -logFilePath <path-to-log-file> -outputFilePath <path-to-output-file> 
                                     -alertWarningThreshold <alert-warning-threshold in seconds>
                                     -alertErrorThreshold <alert-error-threshold in seconds>`
    All the parameters are optional, if not provided, the app will use the default values:
    - logFilePath: `.\src\\main\\resources\\logs.log`
    - outputFilePath: `.\src\\main\\resources\\output.log`
    - alertWarningThreshold: 300 seconds = 5 minutes
    - alertErrorThreshold: 600 seconds = 10 minutes

***Unit tests***:
 There are only two unit tests there, probably not enough. If I had more time, I would have added more tests.

***Future Improvements***:
- Add more unit tests
- Add logs (debug logs in the code for example)
- Add a bash script to start the app
- Adapt the scripts to be able to install java and maven if needed
- Adapt the scripts to follow the output file as it is written
