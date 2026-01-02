package org.quickbase.testng;

import org.quickbase.enums.ExecutionModes;
import org.quickbase.utils.TestUtils;
import org.testng.xml.XmlPackage;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.ArrayList;
import java.util.List;

public class UISuite {

    public String testPackage = "ui.tests.*";

    public XmlSuite createUISuite(TestUtils testUtils) {
        XmlSuite suite = new XmlSuite();
        suite.setName("QB-UI-TestSuite");

        if (testUtils.getExecMode().equals(ExecutionModes.PARALLEL.toString())) {
            suite.setParallel(XmlSuite.ParallelMode.METHODS);
            suite.setThreadCount(testUtils.getParallelCount());
        }
        XmlTest test = new XmlTest(suite);
        test.setName("UI Execution");
        List<XmlPackage> packages = new ArrayList<>();
        packages.add(new XmlPackage(testPackage));
        test.setPackages(packages);

        String[] testGroups = testUtils.getTestGroups().split(",");
        for (String testGroup : testGroups) {
            test.addIncludedGroup(testGroup);
        }
        return suite;
    }
}
