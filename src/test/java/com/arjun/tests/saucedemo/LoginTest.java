package com.arjun.tests.saucedemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arjun.base.BaseTest;
import com.arjun.pages.saucedemo.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "Valid login failed"
        );
    }

    @Test
    public void verifyInvalidUsername() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("invalid_user", "secret_sauce");

        String error = loginPage.getErrorMessage();

        System.out.println("Error: " + error);

        Assert.assertTrue(
                error.contains("Username and password do not match"),
                "Expected error message not displayed"
        );
    }

    @Test
    public void verifyInvalidPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "wrong_password");

        String error = loginPage.getErrorMessage();

        System.out.println("Error: " + error);

        Assert.assertTrue(
                error.contains("Username and password do not match"),
                "Expected error message not displayed"
        );
    }

    @Test
    public void verifyBlankUsername() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "secret_sauce");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
                error.contains("Username is required"),
                "Username validation message not displayed"
        );
    }

    @Test
    public void verifyBlankPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
                error.contains("Password is required"),
                "Password validation message not displayed"
        );
    }

    @Test
    public void verifyBothFieldsBlank() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("", "");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
                error.contains("Username is required"),
                "Username validation message not displayed"
        );
    }

    @Test
    public void verifyLockedOutUser() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorMessage();

        Assert.assertTrue(
                error.contains("locked out"),
                "Locked user error message not displayed"
        );
    }

    @Test
    public void verifyUsernameField() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterUsername("standard_user");

        Assert.assertEquals(
                loginPage.getUsernameFieldValue(),
                "standard_user"
        );
    }

    @Test
    public void verifyPasswordFieldIsMasked() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.enterPassword("secret_sauce");

        Assert.assertEquals(
                loginPage.getPasswordFieldType(),
                "password"
        );
    }
}