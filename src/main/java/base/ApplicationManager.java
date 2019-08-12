package base;

import enums.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public String baseUrl;
    private Properties properties;
    private WebDriver driver;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
    }

    public WebDriver getDriver() {
        String browser = properties.getProperty("browser");
        if (driver == null) {
            switch (browser) {
                case "ff":
                case "firefox":
                    System.setProperty(TestProperties.GECKO_DRIVER.get(), properties.getProperty(TestProperties.GECKO_DRIVER_PATH.get()));
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    //System.setProperty("webdriver.firefox.marionette", "");
                    break;
                case "chrome":
                case "googlechrome":
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");

                    File chromeDriver = new File(properties.getProperty(TestProperties.CHROME_DRIVER_PATH.get()));
                    System.setProperty(TestProperties.CHROME_DRIVER.get(), chromeDriver.getAbsolutePath());
                    driver = new ChromeDriver(options);
                    break;
                //todo implement
                case "ie":
                case "explorer":
                    break;
                //todo implement
                case "internetexplorer":
                    break;
                default:
                    throw new Error("Unsupported Browser");
            }

            baseUrl = properties.getProperty(TestProperties.BASE_URL.get());
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(baseUrl);
            System.out.println(driver.getTitle());

        }
        return driver;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
