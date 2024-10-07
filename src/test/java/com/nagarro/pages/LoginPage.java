package com.nagarro.pages;

import org.openqa.selenium.WebDriver;

import com.nagarro.config.Configs;
import com.nagarro.utils.reporting.AssertionLog;
import com.nagarro.utils.reporting.ExtentReportLogger;
import com.nagarro.utils.web.DriverFactory;
import com.nagarro.utils.web.Web_Lib;

public class LoginPage {

    private final WebDriver          driver;
    private final Web_Lib            webLib;
    private final AssertionLog       assertionLog;
    private final ExtentReportLogger logger;

    // locators
    private final String[] loc_UserName   = { "id", "user-name" };
    private final String[] loc_Password   = { "id", "password" };
    private final String[] loc_Login      = { "id", "login-button" };
    private final String[] loc_ThreeStrip = { "id", "react-burger-menu-btn" };
    private final String[] loc_Logout     = { "id", "logout_sidebar_link" };

    public LoginPage(final AssertionLog assertionLog, final ExtentReportLogger logger) {
        this.driver = DriverFactory.getDriver();
        webLib = new Web_Lib(this.driver, logger);
        this.assertionLog = assertionLog;
        this.logger = logger;
    }

    public void setUserName(final String strUserName) {
        logger.logInfo("Entering username :" + strUserName);
        webLib.setText(loc_UserName, strUserName);
    }

    public void setPassword(final String strPassword) {
        logger.logInfo("Entering password :" + strPassword);
        webLib.setText(loc_Password, strPassword);
    }

    public ProductsPage clickLogin() {
        logger.logInfo("Click Login button on Login page");
        webLib.click(loc_Login);
        webLib.waitForURLContains(Configs.getPropertyInputData("resultURLKeyword"), 10);
        final ProductsPage proPage = new ProductsPage(assertionLog, logger);
        return proPage;
    }

}
