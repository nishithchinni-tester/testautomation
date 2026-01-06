package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ui.TestContext;

import java.lang.reflect.InvocationTargetException;

public class BasePage {

    private final static Logger log = LogManager.getLogger(BasePage.class);

    /**
     * Updated to accept the driver explicitly.
     */
    protected <T> T createInstance(Class<T> clazz, WebDriver driver) {
        try {
            // We pass the 'driver' argument directly into the constructor
            return clazz.getDeclaredConstructor(WebDriver.class)
                    .newInstance(driver);
        } catch (NoSuchMethodException e) {
            log.error("No WebDriver constructor found for: {}", clazz.getName());
        } catch (Exception e) {
            log.error("Reflection error: {}", e.getMessage());
        }
        return null;
    }
}
