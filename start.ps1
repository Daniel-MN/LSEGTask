param(
    [string]$logFilePath = ".\src\main\resources\logs.log",
    [string]$outputFilePath = ".\src\main\resources\output.txt",
    [int]$alertWarningThreshold = 300,
    [int]$alertErrorThreshold = 600
)

$mvnCommand = "mvn clean install"
Write-Host "Executing: $mvnCommand"
Invoke-Expression $mvnCommand

$jarPath = ".\target\LSEGTask-1.0-SNAPSHOT.jar"

# Construct the Java command
$javaCommand = "java -DlogFilePath=`"$logFilePath`" -DoutputFilePath=`"$outputFilePath`" -DalertWarningThreshold=$alertWarningThreshold -DalertErrorThreshold=$alertErrorThreshold -jar $jarPath"

# Execute the command
Write-Host "Executing: $javaCommand"
Invoke-Expression $javaCommand