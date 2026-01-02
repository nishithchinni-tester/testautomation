package org.quickbase.factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    /**
     * Accepts browser parameter and calls SetUpDriver for respective browser.
     *
     * @param browser
     * @return WebDriver object.
     */
    public static WebDriver getDriver(String browser) {
        return switch (browser) {
            case "FIREFOX" -> SetUpDriver.getDriverForFireFox();
            case "EDGE" -> SetUpDriver.getDriverForEdge();
            default -> SetUpDriver.getDriverForChrome();
        };
    }
}
