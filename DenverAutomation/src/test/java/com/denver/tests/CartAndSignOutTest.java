package com.denver.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CartAndSignOutTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
    }

    // Reusable login method
    public void login() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("user-name")))
                .sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.className("inventory_list")));
    }

    // TC_001: Add product to cart
    @Test
    public void verifyAddToCart() {

        // Step 1: Login
        login();

        // Step 2: Add product to cart
        driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack"))
                .click();

        // Step 3: Verify cart badge
        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")));

        Assert.assertEquals(cartBadge.getText(), "1",
                "Cart count should be 1");

        // Step 4: Open cart
        driver.findElement(By.className("shopping_cart_link"))
                .click();

        // Step 5: Verify product is displayed
        WebElement product = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_item_name")));

        Assert.assertEquals(product.getText(),
                "Sauce Labs Backpack",
                "Product should appear in the cart");

        System.out.println("Add to Cart test passed");
    }

    // TC_002: Sign out
    @Test
    public void verifySignOut() {

        // Step 1: Login
        login();

        // Step 2: Open the menu
        driver.findElement(By.id("react-burger-menu-btn")).click();

        // Step 3: Click Logout
        WebElement logoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("logout_sidebar_link")));

        logoutButton.click();

        // Step 4: Verify login page is displayed
        WebElement loginButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("login-button")));

        Assert.assertTrue(loginButton.isDisplayed(),
                "User should be redirected to the login page");

        System.out.println("Sign Out test passed");
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
