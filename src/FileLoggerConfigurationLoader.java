import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FileLoggerConfigurationLoader {

    public FileLoggerConfiguration load(String path) {
        FileLoggerConfiguration flc = null;
        try (InputStream input = new FileInputStream(path)) {

            Properties prop = new Properties();

            prop.load(input);

            flc = new FileLoggerConfiguration(prop.getProperty("FILE"),
                    LoggingLevel.valueOf(prop.getProperty("LEVEL")),
                    Integer.parseInt(prop.getProperty("MAX-SIZE")),
                    prop.getProperty("FORMAT"));
        } catch (IOException io) {
            io.printStackTrace();
        }

        return flc;
    }
}