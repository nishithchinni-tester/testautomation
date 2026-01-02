package ui;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.quickbase.enums.Suite;
import org.quickbase.reportportal.Secrets;
import org.quickbase.testng.RetryListener;
import org.quickbase.testng.UISuite;
import org.quickbase.utils.TestUtils;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.List;

public class Runner {

    /**
     * This is a Main Class to execute Test Suite.
     * @param args
     */
    public static void main(String[] args){

        TestUtils testUtils = new TestUtils();
        /**
         * Sets the System Properties with ReportPortal Configuration.
         */
        System.setProperty("rp.endpoint", testUtils.getReportPortalUrl());
        System.setProperty("rp.api.key", Secrets.getRPApiKey());
        System.setProperty("rp.project", testUtils.getReportPortalProject());
        System.setProperty("rp.launch", testUtils.getReportPortalLaunch());
        System.setProperty("rp.enable", testUtils.getReportPortalEnable());

        XmlSuite suite = null;
        if(testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())){
            suite = new UISuite().createUISuite(testUtils);
        }
        TestNG tng = new TestNG();
        /**
         * Adds the Listeners in TestNG XML.
         * Adds RetryListener, ReportPortalTestNgListener.
         */
        RetryListener retryListener = new RetryListener();
        ReportPortalTestNGListener reportPortalTestNGListener = new ReportPortalTestNGListener();
        tng.addListener(retryListener);
        tng.addListener(reportPortalTestNGListener);
        if(suite!=null) {
            tng.setXmlSuites(List.of(suite));
        }
        /**
         * Runs the test suite.
         */
        tng.run();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
