import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        FileLoggerConfigurationLoader fileLoggerConfigurationLoader = new FileLoggerConfigurationLoader();
        FileLogger fl = new FileLogger(
                fileLoggerConfigurationLoader.load("src\\configuration.properties"));

        try {
            fl.debug(")))");
            fl.info("))");

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FileMaxSizeReachedException e) {
            throw new RuntimeException(e);
        }

    }

}