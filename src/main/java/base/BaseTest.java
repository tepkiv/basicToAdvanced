package base;

import enums.TestProperties;
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

    @BeforeTest
    public static void setUp() throws IOException {
        String configFile = System.getProperty("configFile","application.properties");
        properties.load(new FileReader(new File(configFile)));
        new ApplicationManager(properties);
        driver = ApplicationManager.getDriver();
    }

    @AfterTest
    public static void end(){
        if(properties.getProperty(TestProperties.CI_MODE.get()).equals(true)){
            driver.close();
        }
    }

    public void open(String s) {
        driver.get(s);
    }

    private void open() {
        driver.get("google.com");
    }

    public void validateHeader(){

    }

}
