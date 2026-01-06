package ui.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import ui.TestContext;

import java.lang.reflect.InvocationTargetException;

public class BasePage {

    private final static Logger log = LogManager.getLogger(BasePage.class);

    /**
     * Creates an instance of a class given its Class object.
     *
     * @param clazz The .class variable (e.g., User.class)
     * @param <T>   The type of the class
     * @return An instance of the class, or null if instantiation fails
     */
    protected static <T> T createInstance(Class<T> clazz) {
        try {
            // Look for a constructor that takes WebDriver as an argument
            return clazz.getDeclaredConstructor(WebDriver.class).
                    newInstance(TestContext.getDriver());
        } catch (NoSuchMethodException e) {
            log.error("No WebDriver constructor found for: {}", clazz.getName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Reflection error: {}", e.getMessage());
        }
        return null;
    }
}
