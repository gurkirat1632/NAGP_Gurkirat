package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;
import com.nagarro.pages.CartPage;
import com.nagarro.pages.ProductsPage;

public class Test05VerifyLogout extends BaseClass {

    public ProductsPage proPage;
    public CartPage     cartPage;

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = "Regression")
    public void verifyLogout() {
        test = startTest("Login Test", "Checking user can login with standard user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();

        if (proPage.logout()) {
            logger.logPass("Logout successfull");
        }

    }

    @AfterClass
    public void afterClass() {

    }

}
