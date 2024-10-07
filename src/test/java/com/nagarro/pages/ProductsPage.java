package com.nagarro.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.nagarro.utils.project.Util_Lib;
import com.nagarro.utils.reporting.AssertionLog;
import com.nagarro.utils.reporting.ExtentReportLogger;
import com.nagarro.utils.web.DriverFactory;
import com.nagarro.utils.web.Web_Lib;

public class ProductsPage {

    private final WebDriver          driver;
    private final Web_Lib            webLib;
    private final AssertionLog       assertionLog;
    private final ExtentReportLogger logger;

    // locators
    private final String[] loc_SelectDropDown = { "xpath", "//select[@data-test='product-sort-container']" };
    // private final String[] loc_Password = { "id", "password" };
    private final String[] loc_ProductsPrice = { "xpath", "//div[@data-test='inventory-item-price']" };

    private final String[] loc_AddtoCart = { "id", "add-to-cart-sauce-labs-onesie" };

    private final String[] loc_CartLink = { "xpath", "//a[@data-test='shopping-cart-link']" };

    private final String[] loc_Login      = { "id", "login-button" };
    private final String[] loc_ThreeStrip = { "id", "react-burger-menu-btn" };
    private final String[] loc_Logout     = { "id", "logout_sidebar_link" };

    public ProductsPage(final AssertionLog assertionLog, final ExtentReportLogger logger) {
        this.driver = DriverFactory.getDriver();
        webLib = new Web_Lib(this.driver, logger);
        this.assertionLog = assertionLog;
        this.logger = logger;
    }

    public boolean verifyUserOnProductsPage() {
        return webLib.checkElementPresent("xpath", "//span[text()='Products']");
    }

    public void setLowToHighFilter() {
        webLib.selectItemDropDown(loc_SelectDropDown, "Price (low to high)");

    }

    public boolean verifyProductsAreSorted() {
        final List<WebElement> listPrice = webLib.getElements(loc_ProductsPrice);

        final List<Float> listArra = new ArrayList();
        for (int i = 0; i < listPrice.size(); i++) {
            String strPrice = listPrice.get(i).getText();
            strPrice = Util_Lib.replace(strPrice, "$", "");
            final float floatPrice = Float.parseFloat(strPrice);
            listArra.add(floatPrice);
        }

        if (listArra.stream().sorted().toList().equals(listArra)) {
            logger.logInfo("The Filter prices are sorted correctly");
            return true;
        } else {
            logger.logWarning("The Filter prices are NOT sorted correctly");
            return false;
        }

    }

    public boolean verifyBrokenLinks() {
        final List<WebElement> listLinks = webLib.getElements("xpath", "//a");
        boolean brokenLink = false;
        final List<String> listArra = new ArrayList();
        for (int i = 0; i < listLinks.size(); i++) {
            final String strURL = listLinks.get(i).getText();
            if (strURL.isBlank() || strURL.isEmpty()) {
                listArra.add(strURL);
                brokenLink = true;
            }
        }
        if (brokenLink) {
            logger.logWarning("Broken links are :" + listArra.size());
        }
        return brokenLink;

    }

    public void addRandomItemToCart() {

        webLib.click(loc_AddtoCart);

    }

    public CartPage clickCartButton() {
        webLib.click(loc_CartLink);
        final CartPage cartPage = new CartPage(assertionLog, logger);
        return cartPage;

    }

    public boolean logout() {
        logger.logInfo("Logout check");
        webLib.click(loc_ThreeStrip);
        webLib.click(loc_Logout);
        if (webLib.checkElementPresent(loc_Login)) {
            return true;
        } else {
            return false;
        }

    }

}
