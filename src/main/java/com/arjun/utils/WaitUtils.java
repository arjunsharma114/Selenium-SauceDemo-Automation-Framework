package com.arjun.utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.arjun.utils.ConfigReader;

public class WaitUtils {

    private final WebDriver driver;
    private final WebDriverWait wait;

   public WaitUtils(WebDriver driver) {
    this.driver = driver;

    int explicitWait = Integer.parseInt(
        ConfigReader.getProperty("explicitWait")
    );

    this.wait = new WebDriverWait(
        driver,
        Duration.ofSeconds(explicitWait)
    );
}

    public WebElement waitForElementVisible(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public WebElement waitForElementClickable(By locator) {
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public void waitForElementPresent(By locator) {
        wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public boolean waitForTitleContains(String title) {
        return wait.until(
                ExpectedConditions.titleContains(title)
        );
    }

    public void waitForUrlContains(String url) {
        wait.until(
                ExpectedConditions.urlContains(url)
        );
    }
}