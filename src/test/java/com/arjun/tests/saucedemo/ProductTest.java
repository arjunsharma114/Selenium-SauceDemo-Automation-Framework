package com.arjun.tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.arjun.base.BaseTest;
import com.arjun.pages.saucedemo.LoginPage;
import com.arjun.pages.saucedemo.ProductPage;

public class ProductTest extends BaseTest {

    @BeforeMethod
    public void loginBeforeTest() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "secret_sauce"
        );
    }

    @Test
    public void verifyProductCount() {

        ProductPage productPage =
                new ProductPage(driver);

        int productCount =
                productPage.getProductCount();

        System.out.println(
                "Product Count: " + productCount
        );

        Assert.assertEquals(productCount, 6);
    }

    @Test
    public void verifyFirstProductName() {

        ProductPage productPage =
                new ProductPage(driver);

        String productName =
                productPage.getFirstProductName();

        System.out.println(
                "First Product: " + productName
        );

        Assert.assertEquals(
                productName,
                "Sauce Labs Backpack"
        );
    }

    @Test
    public void verifyFirstProductPrice() {

        ProductPage productPage =
                new ProductPage(driver);

        String price =
                productPage.getFirstProductPrice();

        System.out.println(
                "Product Price: " + price
        );

        Assert.assertEquals(price, "$29.99");
    }

    @Test
    public void verifyAddProductToCart() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.addBackpackToCart();

        String cartCount =
                productPage.getCartBadgeCount();

        Assert.assertEquals(cartCount, "1");
    }

    @Test
    public void verifySortLowToHigh() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.sortProductsLowToHigh();

        double firstPrice =
                productPage.getFirstProductPriceAsNumber();

        System.out.println(
                "Lowest Price: " + firstPrice
        );

        Assert.assertEquals(firstPrice, 7.99);
    }

    @Test
    public void verifySortHighToLow() {

        ProductPage productPage =
                new ProductPage(driver);

        productPage.sortProductsHighToLow();

        double firstPrice =
                productPage.getFirstProductPriceAsNumber();

        System.out.println(
                "Highest Price: " + firstPrice
        );

        Assert.assertEquals(firstPrice, 49.99);
    }
}