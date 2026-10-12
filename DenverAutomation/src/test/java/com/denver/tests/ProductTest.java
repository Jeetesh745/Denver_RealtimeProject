
package com.denver.tests;

import com.denver.base.BaseClass;
import com.denver.pages.HomePage;
import com.denver.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseClass {

    private void openKnownProduct() {
        HomePage home = new HomePage(driver);
        home.openProduct("Hamilton Intense");
    }

    @Test
    public void TC08_openProductDetails() {
        openKnownProduct();

        ProductPage product = new ProductPage(driver);

        Assert.assertTrue(product.isProductPage(),
            "Expected product details URL");
        Assert.assertFalse(product.getProductName().isBlank());
    }

    @Test
    public void TC09_verifyProductPrice() {
        openKnownProduct();

        ProductPage product = new ProductPage(driver);

        Assert.assertFalse(product.getPriceText().isBlank(),
            "Product price should be displayed");
    }

    @Test
    public void TC10_verifyAddToCartAvailability() {
        openKnownProduct();

        ProductPage product = new ProductPage(driver);

        Assert.assertTrue(
            product.isAddToCartDisplayed() || product.isSoldOut(),
            "Expected Add to Cart or Sold Out status");
    }

    @Test
    public void TC13_verifyInvalidProductUrl() {
        driver.get(
            "https://denverformen.com/products/" +
            "this-product-does-not-exist-987654");

        String body = driver.findElement(
            org.openqa.selenium.By.tagName("body"))
            .getText().toLowerCase();

        boolean notFound =
            body.contains("page not found") ||
            body.contains("404") ||
            body.contains("not found");

        Assert.assertTrue(notFound,
            "Expected the invalid product URL to show a not-found state");
    }
}
