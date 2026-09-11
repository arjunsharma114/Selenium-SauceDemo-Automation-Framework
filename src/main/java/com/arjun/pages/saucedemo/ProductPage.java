package com.arjun.pages.saucedemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    private WebDriver driver;

    // Locators
    private By productItems = By.className("inventory_item");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");

    private By backpackAddButton =
            By.id("add-to-cart-sauce-labs-backpack");

    private By cartBadge =
            By.className("shopping_cart_badge");

    private By sortDropdown =
            By.className("product_sort_container");

    // Constructor
    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    // Product methods

    public int getProductCount() {
        List<WebElement> products =
                driver.findElements(productItems);

        return products.size();
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

    public void addBackpackToCart() {
        driver.findElement(backpackAddButton).click();
    }

    public String getCartBadgeCount() {
        return driver.findElement(cartBadge).getText();
    }

    public void sortProductsLowToHigh() {

        Select select = new Select(
                driver.findElement(sortDropdown)
        );

        select.selectByValue("lohi");
    }

    public void sortProductsHighToLow() {

        Select select = new Select(
                driver.findElement(sortDropdown)
        );

        select.selectByValue("hilo");
    }

    public double getFirstProductPriceAsNumber() {

        String price = getFirstProductPrice();

        return Double.parseDouble(
                price.replace("$", "")
        );
    }
}