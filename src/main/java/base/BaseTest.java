package base;

import Enums.TestProperties;
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
        driver = new ApplicationManager(properties).getDriver();
    }

    @AfterTest
    public static void end(){
        if(properties.getProperty(TestProperties.CI_MODE.get()).equals(true)){
            driver.close();
        }
    }

    public void validateHeader(){

    }

}
