package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    private static String homeDir = System.getProperty("user.dir");

    public static WebDriver getDriver() {
        // Initialize WebDriver only if it's not already set
        if (webDriver.get() == null) {
            webDriver.set(createDriver());
        }
        return webDriver.get();
    }

    private static WebDriver createDriver() {
        WebDriver driver = null;
        String browserType = getBrowserType(); // Getting the browser type

        // Log the current directory for debugging purposes
        System.out.println("Home Directory: " + homeDir);

        switch (browserType) {
            case "chrome" -> {
                WebDriverManager.chromedriver().setup(); // Automatically set up ChromeDriver
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--headless"); // Run Chrome in headless mode
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup(); // Automatically set up FirefoxDriver
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--remote-allow-origins=*");
                firefoxOptions.addArguments("--headless"); // Run Firefox in headless mode
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-dev-shm-usage");
                firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            }
            default -> {
                throw new IllegalArgumentException("Browser type not supported: " + browserType);
            }
        }
        driver.manage().window().maximize(); // Maximize the window, may not affect headless
        return driver;
    }

    private static String getBrowserType() {
        String browserType = null;
        try (FileInputStream fileInputStream = new FileInputStream(homeDir + "/src/main/java/properties/config.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            browserType = properties.getProperty("browser", "chrome").toLowerCase(); // Default to chrome if not specified
        } catch (IOException ex) {
            System.err.println("Error reading properties file: " + ex.getMessage());
        }
        return browserType;
    }

    public static void cleanUpDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove();
        }
    }
}
