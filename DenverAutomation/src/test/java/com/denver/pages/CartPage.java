package com.denver.pages;

import com.denver.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
	
	//8.CartPage.java — cart verification.

    private final WebDriver driver;
    private final WaitUtil wait;

    private final By cartLink = By.cssSelector(
        "a[href='/cart'], a[href*='/cart']");

    private final By cartItems = By.cssSelector(
        "tr.cart-item, .cart-item, [data-cart-item]");

    private final By emptyCartMessage = By.xpath(
        "//*[contains(translate(normalize-space(.), " +
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
        "'your cart is empty') or " +
        "contains(translate(normalize-space(.), " +
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
        "'cart is empty')]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public void openCart() {
        wait.clickable(cartLink).click();
    }

    public boolean hasItems() {
        return !driver.findElements(cartItems).isEmpty();
    }

    public boolean isEmptyMessageDisplayed() {
        return driver.findElements(emptyCartMessage).stream()
            .anyMatch(e -> e.isDisplayed());
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
