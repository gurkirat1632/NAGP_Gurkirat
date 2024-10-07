package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;
import com.nagarro.pages.CartPage;
import com.nagarro.pages.ProductsPage;

public class Test03AddToCartAndPurchase extends BaseClass {

    public ProductsPage proPage;
    public CartPage     cartPage;

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = "Regression")
    public void verifyAddToCartAndPurchase() {
        test = startTest("Login Test", "Checking user can login with standard user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        proPage.addRandomItemToCart();
        cartPage = proPage.clickCartButton();

        if (!cartPage.checkProductAdded()) {
            logger.logFail("Products are NOT added in cart correctly");
        }

        if (cartPage.clickCheckoutAndFillInfoANdConfirmOrder(Configs.getPropertyInputData("first"), Configs.getPropertyInputData("last"),
                Configs.getPropertyInputData("zip"))) {
            logger.logPass("Order Confirmed");
        } else {
            logger.logFail("Order NOT Confirmed");
        }

    }

    @Test(groups = "Regression")
    public void verifyRemoveFromCart() {
        test = startTest("Login Test", "Checking user can login with standard user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        proPage.addRandomItemToCart();
        cartPage = proPage.clickCartButton();

        if (!cartPage.checkProductAdded()) {
            logger.logFail("Products are NOT added in cart correctly");
        }

        cartPage.removeProductAdded();

        if (cartPage.checkProductAdded()) {
            logger.logFail("Products are NOT removed in cart correctly");
        } else {
            logger.logPass("Products are  removed in cart correctly");
        }

    }

    @AfterClass
    public void afterClass() {

    }

}
