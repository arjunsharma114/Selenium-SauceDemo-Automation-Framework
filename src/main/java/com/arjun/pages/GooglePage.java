package com.arjun.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.arjun.utils.ConfigReader;
import com.arjun.utils.WaitUtils;

public class GooglePage {

    private WebDriver driver;
    private WaitUtils wait;

    // Locator
    private By searchBox = By.name("q");

    // Constructor
    public GooglePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitUtils(driver);
    }

    // Open Google
    public void openGoogle() {

        driver.get(ConfigReader.getProperty("url"));
    }

    // Search
    public void search(String text) {

        WebElement searchElement =
                wait.waitForElementVisible(searchBox);

        searchElement.sendKeys(text);
        searchElement.submit();
    }

    // Check search box is displayed
    public boolean isSearchBoxDisplayed() {

        return wait
                .waitForElementVisible(searchBox)
                .isDisplayed();
    }

    // Get page title
    public String getTitle() {

        return driver.getTitle();
    }
}