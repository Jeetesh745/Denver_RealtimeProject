package com.denver.pages;

import com.denver.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
	
	//ProductPage.java — product details and add-to-cart actions.

    private final WebDriver driver;
    private final WaitUtil wait;

    private final By productTitle = By.cssSelector(
        "h1.product__title, h1");

    private final By price = By.cssSelector(
        ".price .price-item--regular, .price-item--sale, .price");

    private final By addToCart = By.xpath(
        "//button[contains(translate(normalize-space(.), " +
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
        "'add to cart') or " +
        "contains(translate(normalize-space(.), " +
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
        "'add to bag')]");

    private final By soldOut = By.xpath(
        "//*[contains(translate(normalize-space(.), " +
        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')," +
        "'sold out')]");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public String getProductName() {
        return wait.visible(productTitle).getText().trim();
    }

    public String getPriceText() {
        return wait.visible(price).getText().trim();
    }

    public boolean isProductPage() {
        return driver.getCurrentUrl().contains("/products/");
    }

    public boolean isSoldOut() {
        return !driver.findElements(soldOut).isEmpty();
    }

    public boolean isAddToCartDisplayed() {
        return driver.findElements(addToCart).stream()
            .anyMatch(e -> e.isDisplayed() && e.isEnabled());
    }

    public void addToCart() {
        wait.clickable(addToCart).click();
    }
}
