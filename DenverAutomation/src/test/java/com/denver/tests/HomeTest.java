package com.denver.tests;

import com.denver.base.BaseClass;
import com.denver.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseClass {

    @Test
    public void TC01_verifyHomePageTitle() {
        HomePage home = new HomePage(driver);
        Assert.assertFalse(home.getTitle().isBlank(),
            "Page title should not be empty");
    }

    @Test
    public void TC02_verifyHomePageUrl() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.getCurrentUrl().startsWith(
            "https://denverformen.com/"));
    }

    @Test
    public void TC03_verifyHomePageLoaded() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.isHomePageLoaded());
    }

    @Test
    public void TC04_verifyProductLinksExist() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.hasProductLinks(),
            "Expected product links on homepage");
    }

    @Test
    public void TC05_verifyProductCardsExist() {
        HomePage home = new HomePage(driver);
        Assert.assertTrue(home.getProductCardCount() > 0,
            "Expected at least one product card");
    }
}
