package org.quickbase.extent;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;

public class ReportLogger {
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTestThreadLocal.set(test);
    }

    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    public static void log(String message) {
        getTest().log(Status.INFO, message);
    }

    public static void assertAndLog(Object actual, Object expected, String stepName) {
        try {
            Assert.assertEquals(actual, expected, stepName);
            getTest().log(Status.PASS, stepName + " - Actual: [" + actual + "] matches Expected: [" + expected + "]");
        } catch (AssertionError e) {
            getTest().log(Status.FAIL, stepName + " - FAILED. Actual: [" + actual + "] but Expected: [" + expected + "]");
            throw e;
        }
    }
}