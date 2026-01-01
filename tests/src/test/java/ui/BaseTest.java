package ui;

import org.quickbase.Driver;
import org.quickbase.enums.Suite;
import org.quickbase.utils.TestUtils;
import org.quickbase.utils.WebDriverUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import ui.models.TestData;
import ui.utils.ApplicationUtils;

public class BaseTest extends Driver {

    TestUtils testUtils = new TestUtils();
    public WebDriverUtils driverUtils = null;

    @BeforeSuite(alwaysRun = true)
    public void loadData(){
        ApplicationUtils applicationUtils = new ApplicationUtils();
        testUtils.loadConfigData();
        TestData testData = applicationUtils.loadStaticTestData();
        TestContext.init(testData, testUtils);
    }

    @BeforeTest(alwaysRun = true)
    public void setUp(){
        if(testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())){
            initializeDriver();
            TestContext.initDriver(driver);
            driverUtils = new WebDriverUtils(driver);
            driverUtils.openURL(testUtils.environmentConfig.getURL());
        }
    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        if(driver!=null) {
            driver.quit();
        }
    }
}
