package com.arjun.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private WebDriver driver;

    // Locators
    private By productsTitle = By.className("title");
    private By menuButton = By.id("react-burger-menu-btn");
    private By cartIcon = By.className("shopping_cart_link");

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getProductsTitle() {
        return driver.findElement(productsTitle).getText();
    }

    public void clickMenu() {
        driver.findElement(menuButton).click();
    }

    public void clickCart() {
        driver.findElement(cartIcon).click();
    }

    // Page information
    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}