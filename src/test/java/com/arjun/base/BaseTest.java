package com.arjun.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.arjun.utils.ConfigReader;
import com.arjun.factory.DriverFactory;
import com.arjun.utils.WaitUtils;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected WaitUtils wait;
    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {

        driver = DriverFactory.createDriver(browser);

        wait = new WaitUtils(driver);

        int implicitWait = Integer.parseInt(
                ConfigReader.getProperty("implicitWait")
        );

        driver.manage().timeouts().implicitlyWait(
                java.time.Duration.ofSeconds(implicitWait)
        );

        driver.get(
                ConfigReader.getProperty("url")
        );
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}