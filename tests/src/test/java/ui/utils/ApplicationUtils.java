package ui.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.quickbase.customexceptions.EnvironmentNotFoundException;
import org.quickbase.utils.TestUtils;
import org.testng.Assert;
import ui.TestContext;
import ui.models.TestData;
import ui.pages.HomePage;
import ui.pages.SSOPage;
import ui.pages.TwoStepCodePage;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Random;

import static org.quickbase.utils.TestUtils.getAbsolutePathFromResources;

public class ApplicationUtils {

    final String testDataFileName = "testData.json";
    public WebDriver driver;
    TestUtils testUtils = new TestUtils();
    Logger log = LogManager.getLogger(ApplicationUtils.class);
    public ApplicationUtils(){}

    public ApplicationUtils(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage loginQB(String userName, String password){
        SSOPage ssoPage = new SSOPage(driver);
        HomePage homePage = null;
        if(ssoPage.isSSOTextHeaderVisible()) {
            homePage = ssoPage.clickOnNOButton()
                    .enterUserName(userName)
                    .enterPassword(password)
                    .clickOnSignInButton(TwoStepCodePage.class)
                    .enterRealmTwoStepCode()
                    .clickOnSignInButton(HomePage.class);
        }else{
            log.info("SSO Page is not displayed");
            Assert.fail("SSO page is not displayed");
        }
        return homePage;
    }

    public TestData loadStaticTestData() {
        ObjectMapper mapper = new ObjectMapper();
        TestData testData = null;
        try {
            List<TestData> allConfigs = mapper.readValue(
                    new File(getAbsolutePathFromResources(testDataFileName)), new TypeReference<>() {});

            testData = allConfigs.stream()
                    .filter(config -> config.getEnv().equalsIgnoreCase(testUtils.getEnv()))
                    .findFirst()
                    .orElseThrow(() -> new
                            EnvironmentNotFoundException("Environment not found: " + testUtils.getEnv()));
            log.info("Test Data is loaded");
        }catch (Exception e){
            log.info(e.getStackTrace());
        }
        return testData;
    }

    public String getQBUserRealmCode(){
        GmailReader gmailReader = new GmailReader(TestContext.getTestData());
        String otp="";
        for(int i=0; i<6; i++) {
            otp = gmailReader.getQuickbaseOTP();
            if(otp != null) break;
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return otp;
    }

    public String getRandomColor() {
        Random rand = new Random();
        int r = rand.nextInt(256);
        int g = rand.nextInt(256);
        int b = rand.nextInt(256);
        return String.format("#%02X%02X%02X", r, g, b);
    }

    public String getRandomString(String buildString){
        return buildString + RandomStringUtils.randomAlphanumeric(6);
    }

}
