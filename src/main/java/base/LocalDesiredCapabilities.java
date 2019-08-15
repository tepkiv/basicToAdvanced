package base;

import conf.TestProperties;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class LocalDesiredCapabilities {
    public ChromeOptions chrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars", "--no-sandbox");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("chrome.switches", Collections.singletonList("--ignore-certificate-errors"));
        return options;
    }

    public FirefoxOptions firefox(FirefoxOptions options) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/ms-excel");
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }

    public InternetExplorerOptions iexplore(InternetExplorerOptions options) {
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        options.usePerProcessProxy();
        options.setCapability("requireWindowFocus", false);
        options.setCapability("ie.ensureCleanSession", true);
        options.setCapability("ie.browserCommandLineSwitches", Arrays.asList("-private"));
        options.takeFullPageScreenshot();
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        return options;
    }
}
