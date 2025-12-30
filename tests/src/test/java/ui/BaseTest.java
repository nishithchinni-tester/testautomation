package ui;

import org.quickbase.Driver;
import org.quickbase.enums.Suite;
import org.quickbase.utils.TestUtils;
import org.quickbase.utils.WebDriverUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest extends Driver {

    TestUtils testUtils = new TestUtils();
    WebDriverUtils driverUtils =null;

    @BeforeTest(alwaysRun = true)
    public void setUp(){
        testUtils.loadConfigData();
        if(testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())){
            initializeDriver();
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
