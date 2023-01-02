import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;


public class FileLogger {

    public FileLogger(FileLoggerConfiguration configuration) {
        this.configuration = configuration;
    }

    private FileLoggerConfiguration configuration;

    long currentSize;

    private boolean configLog() throws IOException {
        Path path = Paths.get(configuration.getDestinationFile() + "." + configuration.getFileFormat());

        try {
            currentSize = Files.size(path);

        } catch (NoSuchFileException e) {
            File file = new File(
                    configuration.getDestinationFile() + "." + configuration.getFileFormat());
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        switch (configuration.getCurrentLoggingLevel()) {
            case DEBUG:
                return true;
            default:
                return false;
        }
    }
    public static void log(String text){

    }





    public void debug(String message) throws FileMaxSizeReachedException, IOException {
        if (configLog()) {
            File file = new File(
                    configuration.getDestinationFile() + "." + configuration.getFileFormat());
            try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
                String text =
                        "[" + LocalTime.now().toString() + "][DEBUG] Повідомлення: [" + message + "]\n";
                byte[] textSize = text.getBytes("UTF-8");
                checkFileSize(currentSize, textSize.length);
                bufferWriter.write(text);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void info(String message) throws FileMaxSizeReachedException {
        File file = new File(configuration.getDestinationFile() + "." + configuration.getFileFormat());
        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
            String text = "[" + LocalTime.now().toString() + "][INFO] Повідомлення: [" + message + "]\n";
            byte[] textSize = text.getBytes("UTF-8");
            checkFileSize(currentSize, textSize.length);
            bufferWriter.write(text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkFileSize(long currentSize, long textSize) throws FileMaxSizeReachedException {
        if (currentSize + textSize > configuration.getMaxSizeFile()) {
            throw new FileMaxSizeReachedException(
                    "Over max size " + configuration.getMaxSizeFile() + " Size of file will be " + (
                            currentSize + textSize));
        }
    }


}