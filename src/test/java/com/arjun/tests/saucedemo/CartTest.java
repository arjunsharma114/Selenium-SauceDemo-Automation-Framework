package com.arjun.tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.arjun.base.BaseTest;
import com.arjun.pages.saucedemo.CartPage;
import com.arjun.pages.saucedemo.LoginPage;
import com.arjun.pages.saucedemo.ProductPage;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );
    }

    @Test
    public void verifyCartItemCount() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1
        );
    }

    @Test
    public void verifyProductNameInCart() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertEquals(
                cartPage.getFirstProductName(),
                "Sauce Labs Backpack"
        );
    }

    @Test
    public void verifyProductPriceInCart() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        Assert.assertEquals(
                cartPage.getFirstProductPrice(),
                "$29.99"
        );
    }

    @Test
    public void verifyRemoveProductFromCart() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        cartPage.removeBackpack();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0
        );
    }

    @Test
    public void verifyContinueShopping() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        cartPage.clickContinueShopping();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
        );
    }

    @Test
    public void verifyCheckoutButton() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        driver.findElement(
                org.openqa.selenium.By.className("shopping_cart_link")
        ).click();

        CartPage cartPage =
                new CartPage(driver);

        cartPage.clickCheckout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("checkout-step-one.html")
        );
    }
}