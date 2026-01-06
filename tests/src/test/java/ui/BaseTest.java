package ui;

import org.openqa.selenium.WebDriver;
import org.quickbase.Driver;
import org.quickbase.enums.Suite;
import org.quickbase.utils.TestUtils;
import org.quickbase.utils.WebDriverUtils;
import org.testng.annotations.*;
import ui.models.TestData;
import ui.utils.ApplicationUtils;

public class BaseTest extends Driver {

    private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    private final TestUtils testUtils = new TestUtils();
    public ThreadLocal<WebDriverUtils> threadLocalDriverUtils = new ThreadLocal<>();
    private static TestData suiteData;
    private static TestUtils suiteUtils;

    /**
     * Loads Config Data & Static Test-Data.
     */
    @BeforeSuite(alwaysRun = true)
    public void loadData() {
        ApplicationUtils applicationUtils = new ApplicationUtils();
        testUtils.loadConfigData();
        suiteData = applicationUtils.loadStaticTestData();
        suiteUtils = testUtils;
    }

    /**
     * Initialises Web-Driver according to Browser argument.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        testUtils.loadConfigData();
        TestContext.init(suiteData, suiteUtils);
        if (testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())) {
            WebDriver driver = initializeDriver();
            threadLocalDriver.set(driver);
            TestContext.initDriver(driver);
            threadLocalDriverUtils.set(new WebDriverUtils(getDriver()));
            getDriverUtils().openURL(testUtils.environmentConfig.getURL());
        }
    }

    /**
     * Helper method to get the driver for the current thread.
     */
    public WebDriver getDriver() {
        return threadLocalDriver.get();
    }

    /**
     * Helper method to get the utils for the current thread.
     */
    public WebDriverUtils getDriverUtils() {
        return threadLocalDriverUtils.get();
    }

    /**
     * TearDowns the Driver Object.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
        }
        threadLocalDriver.remove();
        threadLocalDriverUtils.remove();
    }
}
