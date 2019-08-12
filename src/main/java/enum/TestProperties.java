package Enums;

public enum TestProperties {
    GECKO_DRIVER("webdriver.gecko.driver"),
    GECKO_DRIVER_PATH("webdriver.gecko.driver.path"),
    CHROME_DRIVER("webdriver.chrome.driver"),
    CHROME_DRIVER_PATH("webdriver.chrome.driver.path"),
    BASE_URL("base.url"),
    CI_MODE("ci.mode");

    private final String value;

    TestProperties(String v) {
        this.value = v;
    }

    public String get() {
        return value;
    }

}
