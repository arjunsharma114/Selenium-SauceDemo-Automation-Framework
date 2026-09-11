package com.arjun.tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arjun.base.BaseTest;
import com.arjun.pages.saucedemo.HomePage;
import com.arjun.pages.saucedemo.LoginPage;

public class HomePageTest extends BaseTest {

    @Test
    public void verifyHomePageTitle() {

        HomePage homePage = new HomePage(driver);

        String title = homePage.getPageTitle();

        System.out.println("Page Title: " + title);

        Assert.assertEquals(title, "Swag Labs");
    }

    @Test
    public void verifyProductsTitle() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);

        String productsTitle = homePage.getProductsTitle();

        System.out.println("Products Title: " + productsTitle);

        Assert.assertEquals(productsTitle, "Products");
    }

    @Test
    public void verifyHomePageUrl() {

        HomePage homePage = new HomePage(driver);

        String url = homePage.getCurrentUrl();

        System.out.println("Current URL: " + url);

        Assert.assertTrue(
                url.contains("saucedemo.com"),
                "URL is incorrect"
        );
    }
}