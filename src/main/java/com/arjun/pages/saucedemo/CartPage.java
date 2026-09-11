package com.arjun.pages.saucedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {

    private WebDriver driver;

    // Locators
    private By cartItems = By.className("cart_item");
    private By productNames = By.cssSelector(".cart_item .inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    private By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    private By continueShoppingButton =
            By.id("continue-shopping");

    private By checkoutButton =
            By.id("checkout");

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Cart methods

    public int getCartItemCount() {
        List<WebElement> items =
                driver.findElements(cartItems);

        return items.size();
    }

    public String getFirstProductName() {
        return driver.findElements(productNames)
                .get(0)
                .getText();
    }

    public String getFirstProductPrice() {
        return driver.findElements(productPrices)
                .get(0)
                .getText();
    }

    public void removeBackpack() {
        driver.findElement(removeBackpackButton).click();
    }

    public void clickContinueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public boolean isProductPresent(String productName) {

        List<WebElement> products =
                driver.findElements(productNames);

        for (WebElement product : products) {

            if (product.getText().equals(productName)) {
                return true;
            }
        }

        return false;
    }
}