package tests;

import base.LocalDesiredCapabilities;
import conf.TestProperties;
import base.BaseTest;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.codehaus.plexus.logging.LoggerManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import util.ScreenShotManager;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

public class ActionsExercises extends BaseTest {
    private static final Logger logger = LogManager.getLogger(ActionsExercises.class);

    @Test
    public void logging() {
        logger.info("logging info test");
        logger.warn("logging warn test");
        logger.debug("logging debug test");
        logger.error("logging error test", new NullPointerException("NullError"));

    }

    @Test
    public void mouseOver() {
        Actions actions = new Actions(driver);
        By accountList = By.id("nav-link-accountList");
        By searchField = By.id("twotabsearchtextbox");
        WebElement element = driver.findElement(accountList);
        actions.moveToElement(element).perform();
        actions.moveToElement(driver.findElement(searchField)).doubleClick().keyDown(Keys.SHIFT).sendKeys(Keys.SHIFT, "test").perform();
        actions.moveToElement(element).perform();
    }

    @Test
    public void dragAndDrop() throws MalformedURLException {
        URL url = new URL("http://jqueryui.com/droppable/");
        Actions actions = new Actions(driver);

        open(url.toString());

        driver.switchTo().frame(driver.findElement(By.className("demo-frame")));
        WebElement droppable = driver.findElement(By.id("droppable"));
        WebElement draggable = driver.findElement(By.id("draggable"));

        actions.dragAndDrop(draggable, droppable).perform();

        driver.switchTo().defaultContent();
    }

    @Test
    public void tabs() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", TestProperties.CHROME_DRIVER);

        open("http://qaclickacademy.com/practice.php");
        System.out.println(driver.findElements(By.tagName("a")).size());

        WebElement footer = driver.findElement(By.id("gf-BIG"));// Limiting webdriver scope

        System.out.println(footer.findElements(By.tagName("a")).size());

        //3-
        WebElement column = footer.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        System.out.println(column.findElements(By.tagName("a")).size());

        //4- click on each link in the column and check if the pages are opening-
        for (int i = 1; i < column.findElements(By.tagName("a")).size(); i++) {
            String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);

            column.findElements(By.tagName("a")).get(i).sendKeys(clickOnLinkTab);
            Thread.sleep(5000L);

        }// opens all the tabs
        Set<String> abc = driver.getWindowHandles();//4
        Iterator<String> it = abc.iterator();

        while (it.hasNext()) {
            driver.switchTo().window(it.next());
            System.out.println(driver.getTitle());
        }
    }

    By inputLeavingFrom = By.xpath("//input[@id='fromPlaceName']");

    @Test
    public void dropdown() throws InterruptedException {
        String bengaluru_internation_aiport = "BENGALURU INTERNATION AIPORT";

        open("https://www.ksrtc.in");
        driver.findElement(inputLeavingFrom).sendKeys(bengaluru_internation_aiport.substring(0, 4));
        Thread.sleep(2000);

        driver.findElement(inputLeavingFrom).sendKeys(Keys.DOWN);

        System.out.println(driver.findElement(inputLeavingFrom).getText());

        autoSuggestiveField(bengaluru_internation_aiport);
    }

    @Test
    public void takeScreenShot() throws InterruptedException {
        open("https://www.ksrtc.in");
        ScreenShotManager.saveScreenShot();
    }

    private void autoSuggestiveField(String value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = "return document.getElementById(\"fromPlaceName\").value;";

        long millis = Long.parseLong("5000") + System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();

        while (!js.executeScript(script).toString().equalsIgnoreCase(value)
                && currentTime < millis) {

            driver.findElement(inputLeavingFrom).sendKeys(Keys.DOWN);
            currentTime = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis());
        }
    }

}
