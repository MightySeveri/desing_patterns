import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {
    private static Logger instance;
    private PrintWriter writer;

    private Logger() {
        openFile("log.txt");
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void write(String message) {
        if (writer != null) {
            writer.println(message);
            writer.flush();
        }
    }

    public void setFileName(String fileName) {
        close();
        openFile(fileName);
    }

    private void openFile(String fileName) {
        try {
            writer = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Could not open log file: " + e.getMessage());
        }
    }

    public void close() {
        if (writer != null) {
            writer.close();
            writer = null;
        }
    }
}
