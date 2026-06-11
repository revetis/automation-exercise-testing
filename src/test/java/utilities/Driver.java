package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    public static WebDriver driver;

    /**
     * Private constructor to enforce the Singleton pattern and prevent
     * direct instantiation of this class.
     * * <p>All methods in this class should be accessed statically.</p>
     *
     * @author Revetis
     */
    private Driver() {

    }


    /**
     * Returns the existing WebDriver if it has already been initialized.
     * Otherwise, initializes a new WebDriver instance and returns it.
     *
     * @return the active WebDriver instance
     * @author Revetis
     */
    public static WebDriver getDriver() {
        try {
            if (driver == null) {
                switch (ConfigReader.getProperty("browser")) {
                    case "safari":
                        driver = new SafariDriver();
                        break;
                    case "edge":
                        driver = new EdgeDriver();
                        break;
                    case "firefox":
                        driver = new FirefoxDriver();
                        break;
                    default:
                        driver = new ChromeDriver();
                        break;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("An a error occured while setup WebDriver. ERROR: " + e.getMessage());
        }

        return driver;
    }

    /**
     * Quits the active WebDriver session, closing all associated windows and tabs.
     * <p>
     * This method completely terminates the browser process and releases system resources.
     * </p>
     *
     * @author Revetis
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }


    /**
     * Closes the current browser window or tab that the WebDriver is currently focusing on.
     * <p>
     * If it is the last window open, the browser may close, but the driver process
     * might remain active in the background.
     * </p>
     *
     * @author Revetis
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.close();
        }
    }


}
