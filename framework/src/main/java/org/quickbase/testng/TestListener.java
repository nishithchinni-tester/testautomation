package org.quickbase.testng;

import com.epam.reportportal.message.ReportPortalMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.quickbase.Driver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener extends Driver implements ITestListener {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    /**
     * On Test Failure - Attaches the screenshot in ReportPortal.
     *
     * @param result
     */
    @Override
    public void onTestFailure(ITestResult result) {

        if (driver != null) {
            try {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                ReportPortalMessage rpMessage = new ReportPortalMessage(screenshotFile, "Failure Screenshot: " + result.getName());
                log.info(rpMessage);
            } catch (Exception e) {
                log.error("ReportPortal screenshot failed: {}", e.getMessage());
            }
        }
    }


}