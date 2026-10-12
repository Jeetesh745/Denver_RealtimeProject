package com.denver.tests;

import com.denver.base.BaseClass;
import com.denver.pages.HomePage;
import com.denver.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseClass {

    @Test
    public void TC06_searchExistingProduct() {
        HomePage home = new HomePage(driver);
        home.search("Hamilton Intense");

        new WaitUtil(driver).urlContains("search");

        Assert.assertTrue(driver.getCurrentUrl().contains("search"),
            "Search results page should open");
        Assert.assertTrue(
            driver.findElements(By.cssSelector("a[href*='/products/']"))
                .size() > 0,
            "Expected product links in search results");
    }

    @Test
    public void TC07_searchSecondProduct() {
        HomePage home = new HomePage(driver);
        home.search("Acne Clear Face Wash");

        new WaitUtil(driver).urlContains("search");

        Assert.assertTrue(driver.getCurrentUrl().contains("search"));
        Assert.assertTrue(driver.findElements(
            By.cssSelector("a[href*='/products/']")).size() > 0,
            "Expected search results");
    }

    @Test
    public void TC11_searchNonexistentProduct() {
        HomePage home = new HomePage(driver);
        home.search("XYZNonexistentProduct987654");

        new WaitUtil(driver).urlContains("search");

        String body = driver.findElement(By.tagName("body"))
            .getText().toLowerCase();

        Assert.assertTrue(
            body.contains("no results") ||
            body.contains("no products") ||
            home.getProductCardCount() == 0,
            "Expected no-results behavior for unknown product");
    }

    @Test
    public void TC12_searchWithBlankInput() {
        HomePage home = new HomePage(driver);
        home.search("");

        // Blank search may be ignored or may open an empty search page.
        Assert.assertTrue(home.isHomePageLoaded(),
            "The page should remain usable after a blank search");
    }
}
