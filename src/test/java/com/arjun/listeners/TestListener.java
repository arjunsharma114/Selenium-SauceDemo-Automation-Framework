package com.arjun.listeners;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import com.arjun.base.BaseTest;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    // Create Extent Report
    private static ExtentReports getExtentReports() {

        if (extent == null) {

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("target/ExtentReport.html");

            extent = new ExtentReports();

            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "Selenium Maven Project");
            extent.setSystemInfo("Tester", "Arjun");
            extent.setSystemInfo("Framework", "Selenium + TestNG");
            extent.setSystemInfo("Browser", "Chrome");
        }

        return extent;
    }

    @Override
    public void onStart(ITestContext context) {

        System.out.println("===== TEST SUITE STARTED =====");

        getExtentReports();
    }

    @Override
    public void onTestStart(ITestResult result) {

        System.out.println("TEST STARTED: " + result.getName());

        ExtentTest extentTest =
                extent.createTest(result.getName());

        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        System.out.println("TEST PASSED: " + result.getName());

        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        System.out.println("TEST FAILED: " + result.getName());

        Object testInstance = result.getInstance();

        if (testInstance instanceof BaseTest) {

            WebDriver driver =
                    ((BaseTest) testInstance).getDriver();

            if (driver != null) {

                String screenshotPath =
                        takeScreenshot(driver, result.getName());

                if (screenshotPath != null) {

                    test.get().fail(
                            result.getThrowable(),
                            MediaEntityBuilder
                                    .createScreenCaptureFromPath(
                                            screenshotPath)
                                    .build()
                    );

                } else {

                    test.get().fail(result.getThrowable());
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        System.out.println("TEST SKIPPED: " + result.getName());

        test.get().skip("Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {

        System.out.println("===== TEST SUITE FINISHED =====");

        if (extent != null) {

            extent.flush();
        }
    }

    private String takeScreenshot(
            WebDriver driver,
            String testName) {

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        File destination =
                new File(
                        "target/screenshots/"
                                + testName
                                + ".png"
                );

        destination.getParentFile().mkdirs();

        try {

            Files.copy(
                    source.toPath(),
                    destination.toPath(),
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Screenshot saved: "
                            + destination.getAbsolutePath()
            );

            return destination.getAbsolutePath();

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}