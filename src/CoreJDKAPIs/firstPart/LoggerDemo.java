package CoreJDKAPIs.firstPart;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LoggerDemo {
    private static final Logger LOGGER = Logger.getLogger(LoggerDemo.class.getName());
    public static void main(String[] args) throws IOException {
        FileHandler fh = new FileHandler("logs.txt");
        LOGGER.addHandler(fh);

        LOGGER.info("The program is working");

    }
}
