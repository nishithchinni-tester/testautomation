package ui.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.InvocationTargetException;

public class BasePage {

    static Logger log = LogManager.getLogger(BasePage.class);
    /**
     * Creates an instance of a class given its Class object.
     * @param clazz The .class variable (e.g., User.class)
     * @param <T> The type of the class
     * @return An instance of the class, or null if instantiation fails
     */
    public static <T> T createInstance(Class<T> clazz, WebDriver driver) {
        try {
            // Look for a constructor that takes WebDriver as an argument
            return clazz.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (NoSuchMethodException e) {
            log.error("No WebDriver constructor found for: {}", clazz.getName());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Reflection error: {}", e.getMessage());
        }
        return null;
    }
}
