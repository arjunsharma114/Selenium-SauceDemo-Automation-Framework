package com.arjun.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver(String browser) {

        WebDriver driver;

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();

            String headless = System.getProperty("headless", "false");

            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }

            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {

            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else {

            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        driver.manage().window().maximize();

        return driver;
    }
}