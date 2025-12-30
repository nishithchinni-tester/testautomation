package org.quickbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.quickbase.customexceptions.DriverNotInvokedException;
import org.quickbase.factory.DriverFactory;
import org.quickbase.utils.TestUtils;

import java.time.Duration;

public class Driver {

    public WebDriver driver;
    public Logger log = null;
    TestUtils testUtils = new TestUtils();

    public void initializeDriver() {
        try {
            log = LogManager.getLogger(Driver.class);
            driver = DriverFactory.getDriver(testUtils.getBrowser());
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(1));
            log.info("{} Driver initialized !!", testUtils.getBrowser().toUpperCase());
        } catch (Exception e) {
            throw new DriverNotInvokedException(e.getMessage());
        }
    }
}
