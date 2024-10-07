package com.nagarro.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nagarro.base.BaseClass;
import com.nagarro.config.Configs;
import com.nagarro.pages.ProductsPage;

public class Test_ProductSearchFilter extends BaseClass {

    public ProductsPage proPage;

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @Test(groups = "Regression")
    public void verifyProductSearchAndFilter() {
        test = startTest("Login Test", "Checking user can login with standard user");
        test.assignCategory("Bus Booking");
        loginPage.setUserName(Configs.getPropertyInputData("username"));
        loginPage.setPassword(Configs.getPropertyInputData("password"));
        proPage = loginPage.clickLogin();
        proPage.setLowToHighFilter();

        if (proPage.verifyProductsAreSorted()) {
            logger.logPass("Products are sorted correctly");
        } else {
            logger.logFail("Products are NOT sorted correctly");
        }

        if (!proPage.verifyBrokenLinks()) {
            logger.logFail("There are broken links in the application");
        }

    }

    @AfterClass
    public void afterClass() {

    }

}
