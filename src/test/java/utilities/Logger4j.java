package utilities;

import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;

public class Logger4j {

    public static void info(String message) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        LogManager.getLogger(className).info(message);
        saveLogToAllure(message);
    }

    public static void warn(String message) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        LogManager.getLogger(className).warn(message);
        saveLogToAllure("WARN: " + message);
    }

    public static void error(String message) {
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        LogManager.getLogger(className).error(message);
        saveLogToAllure("ERROR: " + message);
    }

    @Attachment(value = "Log Message", type = "text/plain")
    public static String saveLogToAllure(String message) {
        return message;
    }


}
