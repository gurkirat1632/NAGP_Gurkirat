package com.nagarro.pages;

import org.openqa.selenium.WebDriver;

import com.nagarro.utils.reporting.AssertionLog;
import com.nagarro.utils.reporting.ExtentReportLogger;
import com.nagarro.utils.web.DriverFactory;
import com.nagarro.utils.web.Web_Lib;

public class CartPage {

    private final WebDriver          driver;
    private final Web_Lib            webLib;
    private final AssertionLog       assertionLog;
    private final ExtentReportLogger logger;

    private final String[] loc_RemoveButton = { "id", "remove-sauce-labs-onesie" };
    private final String[] loc_Checkout     = { "id", "checkout" };
    private final String[] loc_FirstName    = { "id", "first-name" };
    private final String[] loc_LastName     = { "id", "last-name" };
    private final String[] loc_Zip          = { "id", "postal-code" };
    private final String[] loc_Continue     = { "id", "continue" };
    private final String[] loc_Finish       = { "id", "finish" };
    private final String[] loc_OrderConfirm = { "id", "back-to-products" };

    public CartPage(final AssertionLog assertionLog, final ExtentReportLogger logger) {
        this.driver = DriverFactory.getDriver();
        webLib = new Web_Lib(this.driver, logger);
        this.assertionLog = assertionLog;
        this.logger = logger;
    }

    public boolean checkUserOnCartPage() {
        return webLib.checkElementPresent(loc_RemoveButton);
    }

    public boolean checkProductAdded() {
        return webLib.checkElementPresent(loc_RemoveButton);
    }

    public void removeProductAdded() {
        webLib.click(loc_RemoveButton);
    }

    public boolean clickCheckoutAndFillInfoANdConfirmOrder(final String strFirstName, final String strLastName, final String zipCode) {
        webLib.click(loc_Checkout);
        webLib.setText(loc_FirstName, strFirstName);
        webLib.setText(loc_LastName, strLastName);
        webLib.setText(loc_Zip, zipCode);
        webLib.click(loc_Continue);
        webLib.click(loc_Finish);
        if (webLib.checkElementPresent(loc_OrderConfirm)) {
            return true;
        } else {
            return false;
        }
    }
}
