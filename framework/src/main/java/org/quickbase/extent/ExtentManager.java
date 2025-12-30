package org.quickbase.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.quickbase.utils.TestUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports createInstance() {
        if (extent == null) {
            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String folderName = "TestReports";
            String reportName = "TestReport_" + timeStamp + ".html";
            String path = System.getProperty("user.dir") + File.separator + folderName;

            File directory = new File(path);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(path + File.separator + reportName);
            sparkReporter.config().setReportName("QB-TestAutomation Results");
            sparkReporter.config().setDocumentTitle("Automation Test Execution Report");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Browser",new TestUtils().getBrowser());
            extent.setSystemInfo("Environment",new TestUtils().getEnv());
            extent.setSystemInfo("ExecutionGroups",new TestUtils().getTestGroups());
        }
        return extent;
    }
}