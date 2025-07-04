package brp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtils {
    private static final Logger Logger = LogManager.getLogger(LogUtils.class);

    public static void info(String message) {
        Logger.info(message);
    }

    public static void info(Object object) {
        Logger.info(object);
    }

    public static void warn(String message) {
        Logger.warn(message);
    }

    public static void warn(Object object) {
        Logger.warn(object);
    }

    public static void error(String message) {
        Logger.error(message);
    }

    public static void error(Object object) {
        Logger.error(object);
    }

    public static void debug(String message) {
        Logger.debug(message);
    }

    public static void debug(Object object) {
        Logger.debug(object);
    }

    public static void fatal(String message) {
        Logger.fatal(message);
    }

    public static void fatal(Object object) {
        Logger.fatal(object);
    }
}
