package com.arjun.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.arjun.base.BaseTest;
import com.arjun.pages.GooglePage;
import com.arjun.data.TestDataProvider;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {

        GooglePage googlePage = new GooglePage(driver);

        googlePage.openGoogle();

        String title = googlePage.getTitle();

        System.out.println("Google Title: " + title);

        Assert.assertEquals(title, "Google");
    }

    @Test
    public void verifySearchBox() {

        GooglePage googlePage = new GooglePage(driver);

        googlePage.openGoogle();

        Assert.assertTrue(
            googlePage.isSearchBoxDisplayed(),
            "Google search box is not displayed" );
    }

    @Test(dataProvider = "searchData", dataProviderClass = TestDataProvider.class)
    public void searchSelenium(String searchText) {

        GooglePage googlePage = new GooglePage(driver);

        googlePage.openGoogle();

        googlePage.search(searchText);
	
	wait.waitForTitleContains(searchText);

        String title = googlePage.getTitle();

        System.out.println("Search Page Title: " + title);

        Assert.assertTrue(
        title.contains(searchText),
        "Search page title does not contain " + searchText);
    }
}