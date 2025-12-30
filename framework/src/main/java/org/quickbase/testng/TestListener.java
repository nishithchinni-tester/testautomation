package org.quickbase.testng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.quickbase.extent.ExtentManager;
import org.quickbase.extent.ReportLogger;
import org.quickbase.utils.WebDriverUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
        ReportLogger.setTest(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().log(Status.FAIL, "Test Failed: " + result.getThrowable());

        // Get driver from the test class
        Object testClass = result.getInstance();
        WebDriver driver = new WebDriverUtils().getDriver();

        // Capture Screenshot as Base64 (simplest for embedding in HTML)
        String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        test.get().addScreenCaptureFromBase64String(screenshot, "Failure Screenshot");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush(); // Generates the report
    }
}