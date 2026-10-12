
package com.denver.pages;

import com.denver.utilities.WaitUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

    private final WebDriver driver;
    private final WaitUtil wait;

    private final By searchInput = By.cssSelector(
        "input[type='search'], input[name='q']");

    private final By searchButton = By.cssSelector(
        "button[type='submit'], input[type='submit']");

    private final By productLinks = By.cssSelector(
        "a[href*='/products/']");

    private final By productCards = By.cssSelector(
        ".card-wrapper, .product-card, li.grid__item");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtil(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isHomePageLoaded() {
        return wait.visible(By.tagName("body")).isDisplayed();
    }

    public boolean hasProductLinks() {
        return !driver.findElements(productLinks).isEmpty();
    }

    public void search(String productName) {
        WebElement search = wait.visible(searchInput);
        search.clear();
        search.sendKeys(productName);

        var buttons = driver.findElements(searchButton);

        if (!buttons.isEmpty() && buttons.get(0).isDisplayed()) {
            buttons.get(0).click();
        } else {
            search.submit();
        }
    }

    public int getProductCardCount() {
        return driver.findElements(productCards).size();
    }

    public boolean hasProductNamed(String name) {
        return driver.findElements(productLinks).stream()
            .anyMatch(e -> e.getText().toLowerCase()
                .contains(name.toLowerCase()));
    }

    public void openProduct(String productName) {
        By product = By.xpath(
            "//a[contains(@href,'/products/') and " +
            "contains(translate(normalize-space(.), " +
            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), " +
            "'" + productName.toLowerCase() + "')]");

        wait.clickable(product).click();
    }
}
