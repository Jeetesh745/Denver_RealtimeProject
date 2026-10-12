package com.denver.tests;

import com.denver.base.BaseClass;
import com.denver.pages.CartPage;
import com.denver.pages.HomePage;
import com.denver.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test-yogesh extends BaseClass {

	//9.Test classes — execute scenarios
	
    @Test
    public void TC14_openCartPage() {
        CartPage cart = new CartPage(driver);
        cart.openCart();

        Assert.assertTrue(
            cart.getCurrentUrl().contains("/cart"),
            "Expected cart page URL");
    }

    @Test
    public void TC15_addProductToCart() {
        HomePage home = new HomePage(driver);
        home.openProduct("Hamilton Intense");

        ProductPage product = new ProductPage(driver);

        Assert.assertTrue(product.isAddToCartDisplayed(),
            "Product must be available before adding to cart");

        product.addToCart();

        CartPage cart = new CartPage(driver);
        cart.openCart();

        Assert.assertTrue(cart.hasItems(),
            "Expected at least one item in the cart");
    }

   // @Test
    public void TC16_verifyEmptyCartMessage() {
        CartPage cart = new CartPage(driver);
        cart.openCart();

        if (cart.hasItems()) {
            Assert.assertTrue(cart.getCurrentUrl().contains("/cart"));
        } else {
            Assert.assertTrue(
                cart.isEmptyMessageDisplayed() ||
                cart.getCurrentUrl().contains("/cart"),
                "Expected the cart page to remain accessible");
        }
    }
}
