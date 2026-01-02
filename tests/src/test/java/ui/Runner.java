package ui;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.quickbase.enums.Suite;
import org.quickbase.reportportal.Secrets;
import org.quickbase.testng.UISuite;
import org.quickbase.utils.TestUtils;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.List;

public class Runner {

    public static void main(String[] args){

        TestUtils testUtils = new TestUtils();

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
        tng.addListener(new ReportPortalTestNGListener());
        if(suite!=null) {
            tng.setXmlSuites(List.of(suite));
        }
        tng.run();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
