package com.arjun.pages.saucedemo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartItems = By.className("cart_item");
    private By productNames =
            By.cssSelector(".cart_item .inventory_item_name");
    private By productPrices =
            By.className("inventory_item_price");

    private By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    private By continueShoppingButton =
            By.id("continue-shopping");

    private By checkoutButton =
            By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public String getFirstProductName() {
        return driver.findElements(productNames).get(0).getText();
    }

    public String getFirstProductPrice() {
        WebElement price = wait.until(
                ExpectedConditions.visibilityOfElementLocated(productPrices)
        );

        return price.getText();
    }

    public void removeBackpack() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeBackpackButton
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        cartItems, 0
                )
        );
    }

    public void clickContinueShopping() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        continueShoppingButton
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        wait.until(
                ExpectedConditions.urlContains(
                        "inventory.html"
                )
        );
    }

    public void clickCheckout() {

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(
                        checkoutButton
                )
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-one.html"
                )
        );
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