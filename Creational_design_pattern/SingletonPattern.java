// Singleton Logger class
class Logger {
    private static Logger instance;

    private Logger() {}

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Main class to demonstrate the Singleton Pattern
public class SingletonPattern {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("This is a log message.");

        Logger anotherLogger = Logger.getInstance();
        anotherLogger.log("This is another log message.");

        System.out.println("Are both loggers the same instance? " + (logger == anotherLogger));
    }
}
