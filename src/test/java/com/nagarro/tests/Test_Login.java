package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;
import com.nagarro.pages.ProductsPage;

public class Test_Login extends BaseClass {

    public ProductsPage proPage;

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = "Regression")
    public void verifyLoginWithStdUser() {
        test = startTest("Login Test", "Checking user can login with standard user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        if (proPage.verifyUserOnProductsPage()) {
            logger.logPass("Login of standard user successfully verified");
        } else {
            logger.logFail("Login of standard user is NOT successfully verified");
        }

    }

    @Test(groups = "Regression")
    public void verifyLoginWithOtherUser() {
        test = startTest("Login Test", "Checking user can login with admin user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        if (proPage.verifyUserOnProductsPage()) {
            logger.logPass("Login of standard user successfully verified");
        } else {
            logger.logFail("Login of standard user is NOT successfully verified");
        }

    }

    @Test(groups = "Regression")
    public void verifyLoginWithInvalidUser() {
        test = startTest("Login Test", "Checking user can login with Light user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        if (proPage.verifyUserOnProductsPage()) {
            logger.logPass("Login of standard user successfully verified");
        } else {
            logger.logFail("Login of standard user is NOT successfully verified");
        }

    }

    @AfterClass
    public void afterClass() {

    }

}
