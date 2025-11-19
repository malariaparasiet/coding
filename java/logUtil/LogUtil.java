package logUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {
    private FileWriter logFileWriter;

    public enum LogTypes {SUCCESS, ERROR, INFO};

    public LogUtil(String fileName) {
        try {
            logFileWriter = new FileWriter(fileName);
            System.out.println("Logfile created with name: " + fileName);
        } catch (IOException e) {
            System.out.println("Something did go wrong when creating logfile, error: " + e);
        }
    }

    public void log(LogTypes logType, String logMessage) {

        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm:ss");

        String formattedDateTime = now.format(formatter);

        switch (logType) {
            case SUCCESS:
                try {
                    logFileWriter.write(formattedDateTime + ": " + "✅: " + logMessage + "\n");
                } catch (IOException e) {
                    System.out.println("Error with logFile" + e);
                }
                break;
            case ERROR:
                try {
                    logFileWriter.write(formattedDateTime + ": " + "❌: " + logMessage + "\n");
                } catch (IOException e) {
                    System.out.println("Error with logFile" + e);
                }
                break;
            case INFO:
                try {
                    logFileWriter.write(formattedDateTime + ": " + "ℹ️: " + logMessage + "\n");
                } catch (IOException e) {
                    System.out.println("Error with logFile" + e);
                }
                break;
            default:
                break;
        }
        try {
            logFileWriter.flush();
        } catch (IOException e) {
            System.out.println("Could not close logFile: " + e);
        }
    }
}