package ui;

import org.quickbase.enums.Suite;
import org.quickbase.testng.TestListener;
import org.quickbase.testng.UISuite;
import org.quickbase.utils.TestUtils;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.util.List;

public class Runner {

    public static void main(String[] args){
        TestUtils testUtils = new TestUtils();
        XmlSuite suite = null;
        if(testUtils.getSuite().equalsIgnoreCase(Suite.UI.name())){
            suite = new UISuite().createUISuite(testUtils);
        }
        TestNG tng = new TestNG();
        TestListener testListener = new TestListener();
        tng.addListener(testListener);
        if(suite!=null) {
            tng.setXmlSuites(List.of(suite));
        }
        tng.run();
    }
}
