package com.automationpractice.framework;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


@Listeners({BaseTest.ScreenshotListener.class})
public class BaseTest {
    private static Settings settings = new Settings();

    public static String time = new SimpleDateFormat("yyyy.MM.dd 'at' HHmm ss z").format(Calendar.getInstance().getTime());
    public static String filepath = settings.getOutputDir() + time + "/";
    public static Logger LOG;

    @BeforeMethod(alwaysRun = true)
    public static void beforeSuite() {
        BasePage.driver = settings.getDriver();
        BasePage.settings = settings;
        BasePage.driver.get(settings.getBaseUrl());
        BasePage.driver.manage().window().maximize();
        BasePage.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.setProperty("filepath", filepath);
        LOG = Logger.getLogger(BaseTest.class);
    }

    @AfterMethod(alwaysRun = true)
    public static void afterClass() {
        BasePage.driver.close();
    }


    public static class ScreenshotListener extends TestListenerAdapter {

        @Override
        public void onTestFailure(ITestResult result) {
            File screenshot1 = new File(filepath + result.getMethod().getMethodName() + ".png");
            screenshot1.delete();
            File screenshotTempFile = ((TakesScreenshot) BasePage.driver)
                    .getScreenshotAs(OutputType.FILE);

            try {
                FileUtils.copyFile(screenshotTempFile, screenshot1);
            } catch (IOException e) {

                LOG.info(e.getMessage());
            }
            LOG.info(screenshot1.getAbsolutePath());

        }


    }


}
