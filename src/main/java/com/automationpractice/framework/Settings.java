package com.automationpractice.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.*;
import java.util.Properties;

public class Settings {

    private static final String SELENIUM_BASEURL = "selenium.baseUrl";
    private static final String SELENIUM_BROWSER = "selenium.browser";
    private static final String SELENIUM_PROPERTIES = "selenium.properties";
    private static final String OUTPUT_DIR = "outputDir";

    private String baseUrl;
    private BrowserType browser;
    private Properties properties = new Properties();
    private String outputDir;

    public Settings() {
        loadSettings();
    }

    private void loadSettings() {
        properties = loadPropertiesFile();
        baseUrl = properties.getProperty(SELENIUM_BASEURL);
        browser = BrowserType.Browser(properties.getProperty(SELENIUM_BROWSER));
        outputDir = properties.getProperty(OUTPUT_DIR);
    }

    private Properties loadPropertiesFile() {
        try {
            // get specified property file
            String filename = getPropertyOrNull(SELENIUM_PROPERTIES);
            // it is not defined, use default
            if (filename == null) {
                filename = SELENIUM_PROPERTIES;
            }

            // try to load from classpath
            InputStream stream = getClass().getClassLoader().getResourceAsStream(filename);
            // no file in classpath, look on disk
            if (stream == null) {
                stream = new FileInputStream(new File(filename));
            }
            Properties result = new Properties();
            result.load(stream);
            return result;
        } catch (IOException e) {
            throw new UnknownPropertyException("Property file is not found");
        }
    }

    public String getPropertyOrNull(String name) {
        return getProperty(name, false);
    }

    private String getProperty(String name, boolean forceExceptionIfNotDefined) {
        String result;
        if ((result = System.getProperty(name, null)) != null) {
            return result;
        } else if ((result = getPropertyFromPropertiesFile(name)) != null) {
            return result;
        } else if (forceExceptionIfNotDefined) {
            throw new UnknownPropertyException("Unknown property: [" + name + "]");
        }
        return result;
    }

    private String getPropertyFromPropertiesFile(String name) {
        Object result = properties.get(name);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }

    public WebDriver getDriver() {
        return getDriver(browser);
    }

    private WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("marionette", true);;
                return new FirefoxDriver(options);

            case IE:
                System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
                return new InternetExplorerDriver();
            case GC:
                System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
                return new ChromeDriver();
            default:
                throw new UnknownBrowserException("Cannot create driver for unknown browser type");
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public BrowserType getBrowser() {
        return browser;
    }
    public String getOutputDir() {
        return outputDir;
    }

    public class UnknownBrowserException extends RuntimeException {
        public UnknownBrowserException(String message) {
            super(message);
        }
    }
    public class UnknownPropertyException extends RuntimeException {
        public UnknownPropertyException(String message) {
            super(message);
        }
    }

}
