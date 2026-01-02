package org.quickbase.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quickbase.customexceptions.EnvironmentNotFoundException;
import org.quickbase.enums.PropertyKeys;
import org.quickbase.models.EnvironmentConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;

public class TestUtils {

    public static Properties prop = new Properties();
    public static HashMap<String, String> propMap = new HashMap<String, String>();
    private final Logger log = LogManager.getLogger(TestUtils.class);
    public EnvironmentConfig environmentConfig = null;

    /**
     * Accepts Properties Filename and loads the properties based on filename.
     * @param propertiesFileName
     * @return HashMap<String,String> with properties keys and values.
     */
    private HashMap<String, String> getPropertiesHashMap(String propertiesFileName) {
        try {
            FileInputStream ip = new FileInputStream(getAbsolutePathFromResources(propertiesFileName));
            prop.load(ip);
            propMap.putAll((Map) prop);
            return propMap;
        } catch (IOException e) {
            log.info(e.getStackTrace());
        }
        return propMap;
    }

    /**
     * Loads environments configuration by reading env.json file under resources.
     */
    public void loadConfigData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String environmentFileName = "env.json";
            List<EnvironmentConfig> allConfigs = mapper.readValue(
                    new File(getAbsolutePathFromResources(environmentFileName)), new TypeReference<>() {
                    });

            environmentConfig = allConfigs.stream()
                    .filter(config -> config.getEnv().equalsIgnoreCase(getEnv()))
                    .findFirst()
                    .orElseThrow(() -> new
                            EnvironmentNotFoundException("Environment not found: " + getEnv()));
            log.info("Environment Data is loaded");
        } catch (Exception e) {
            log.info(e.getStackTrace());
        }
    }

    public HashMap<String, String> getPropFileValues() {
        final String propertiesFileName = "Runner.properties";
        return getPropertiesHashMap(propertiesFileName);
    }

    public HashMap<String, String> getReportPortalProperties() {
        final String reportPortalPropertiesFileName = "reportportal.properties";
        return getPropertiesHashMap(reportPortalPropertiesFileName);
    }

    public String getBrowser() {
        return getPropFileValues().get(PropertyKeys.BROWSER.getValue());
    }

    public String getEnv() {
        return getPropFileValues().get(PropertyKeys.ENVIRONMENT.getValue());
    }

    public String getExecMode() {
        return getPropFileValues().get(PropertyKeys.EXECMODE.getValue());
    }

    public int getParallelCount() {
        return Integer.parseInt(getPropFileValues().get(PropertyKeys.PARALLELCOUNT.getValue()));
    }

    public String getSuite() {
        return getPropFileValues().get(PropertyKeys.SUITE.getValue());
    }

    public String getTestGroups() {
        return getPropFileValues().get(PropertyKeys.EXECUTIONGROUPS.getValue());
    }

    public String getReportPortalUrl() {
        return getReportPortalProperties().get(PropertyKeys.RPURL.getValue());
    }

    public String getReportPortalProject() {
        return getReportPortalProperties().get(PropertyKeys.RPPROJECT.getValue());
    }

    public String getReportPortalLaunch() {
        String epochString = String.valueOf(Instant.now().toEpochMilli());
        String rpLaunch = getReportPortalProperties().get(PropertyKeys.RPLAUNCH.getValue());
        return rpLaunch + "_" + epochString;
    }

    public String getReportPortalEnable() {
        return getReportPortalProperties().get(PropertyKeys.RPENABLE.getValue());
    }

    public static String getAbsolutePathFromResources(String fileName) {
        ClassLoader classLoader = TestUtils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found in resources: " + fileName);
        }
        try {
            return Paths.get(resource.toURI()).toAbsolutePath().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
