import java.io.Serializable;

public class FileLoggerConfiguration implements Serializable {

    private String destinationFile;
    private LoggingLevel currentLoggingLevel;
    private int maxSizeFile;
    private String fileFormat;

    public FileLoggerConfiguration(String destinationFile, LoggingLevel currentLoggingLevel,
                                   int maxSizeFile, String fileFormat) {

        this.destinationFile = destinationFile;
        this.currentLoggingLevel = currentLoggingLevel;
        this.maxSizeFile = maxSizeFile;
        this.fileFormat = fileFormat;
    }

    public String getDestinationFile() {
        return destinationFile;
    }

    public LoggingLevel getCurrentLoggingLevel() {
        return currentLoggingLevel;
    }

    public int getMaxSizeFile() {
        return maxSizeFile;
    }

    public String getFileFormat() {
        return fileFormat;
    }
}