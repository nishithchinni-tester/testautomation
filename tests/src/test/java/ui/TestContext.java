package ui;

import org.openqa.selenium.WebDriver;
import org.quickbase.utils.TestUtils;
import ui.models.TestData;

public class TestContext {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<TestData> testData = new ThreadLocal<>();
    private static ThreadLocal<TestUtils> testUtils = new ThreadLocal<>();

    public static void init(TestData data, TestUtils tesUtils) {
        testData.set(data);
        testUtils.set(tesUtils);
    }
    public static void initDriver(WebDriver drv){
        driver.set(drv);
    }

    public static WebDriver getDriver() { return driver.get(); }
    public static TestData getTestData() { return testData.get(); }
    public static TestUtils getTestutils() { return testUtils.get(); }

    public static void remove() {
        driver.remove();
        testData.remove();
    }
}