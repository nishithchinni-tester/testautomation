package org.quickbase.factory;

import org.openqa.selenium.WebDriver;

public class DriverFactory {

    public static WebDriver getDriver(String browser) {
        return switch (browser) {
            case "FIREFOX" -> SetUpDriver.getDriverForFireFox();
            case "EDGE" -> SetUpDriver.getDriverForEdge();
            default -> SetUpDriver.getDriverForChrome();
        };
    }
}
