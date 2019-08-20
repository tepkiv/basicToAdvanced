package base;

import conf.TestProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
    public static WebDriver driver;
    public static Properties properties = new Properties();
    protected static ApplicationManager app;

    @BeforeTest
    public void setUp() throws IOException {
        String configFile = System.getProperty("config.file","src/test/resources/config/application.properties");
        properties.load(new FileReader(new File(configFile)));
        app = new ApplicationManager(properties);
        driver =  ApplicationManager.getDriver();
    }

    @AfterTest
    public void tearDown(){
        if(properties.getProperty(TestProperties.CI_MODE).equals("true")){
            driver.close();
        }
    }

    public void open(String s) {
        driver.get(s);
    }

    private void open() {
        driver.get("google.com");
    }

}
