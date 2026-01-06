package org.quickbase.testng;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.quickbase.Driver;
import org.quickbase.utils.WebDriverUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends Driver implements ITestListener {

    Logger log = LogManager.getLogger(TestListener.class);
    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getName();
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

        WebDriverUtils webDriverUtils = new WebDriverUtils();
        // Access the driver from BaseTest
        File srcFile = ((TakesScreenshot) webDriverUtils.getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";
        
        try {
            FileUtils.copyFile(srcFile, new File(path));
            log.info("Screenshot captured for failed test: {}", testName);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}