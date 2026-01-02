package ui;

import org.quickbase.Driver;
import org.quickbase.enums.Suite;
import org.quickbase.utils.TestUtils;
import org.quickbase.utils.WebDriverUtils;
import org.testng.annotations.*;
import ui.models.TestData;
import ui.utils.ApplicationUtils;

public class BaseTest extends Driver {

    TestUtils testUtils = new TestUtils();
    public WebDriverUtils driverUtils = null;

    /**
     * Loads Config Data & Static Test-Data.
     */
    @BeforeSuite(alwaysRun = true)
    public void loadData(){
        ApplicationUtils applicationUtils = new ApplicationUtils();
        testUtils.loadConfigData();
        TestData testData = applicationUtils.loadStaticTestData();
        TestContext.init(testData, testUtils);
    }

    /**
     * Initialises Web-Driver according to Browser argument.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        if(testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())){
            initializeDriver();
            TestContext.initDriver(driver);
            driverUtils = new WebDriverUtils(driver);
            driverUtils.openURL(testUtils.environmentConfig.getURL());
        }
    }

    /**
     * TearDowns the Driver Object.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if(driver!=null) {
            driver.quit();
        }
    }
}
