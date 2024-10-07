package com.nagarro.utils.reporting;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.nagarro.config.Configs;
import com.nagarro.utils.web.DriverFactory;

public class AssertionLog {

    WebDriver          driver;
    ExtentReportLogger logger;
    String             methodName;
    static boolean     stopTestonFail = Configs.stopTestONFail;

    public AssertionLog(final String methodName) {
        this.driver = DriverFactory.getDriver();
        this.methodName = methodName;
        logger = new ExtentReportLogger(driver, methodName);
    }

    /****
     * Description : this function will log the assertion output to extent file and do assertions Usage : parameter : expected value, actual value, description
     * to print in report, and if image to be taken
     */
    public void assertEquals(final Object actual, final Object expected, final String desc, final boolean imageCapture) {
        final String strM = "Expected :" + expected.toString() + "|Actual :" + actual.toString();
        try {
            Assert.assertEquals(actual.toString(), expected.toString(), desc + "|Details:" + strM);
            Assert.assertTrue(true);
            logger.logPass(desc, imageCapture);
        } catch (final AssertionError e) {
            logger.logFail(desc + "|Details:" + strM, imageCapture);
            System.out.println("*********ASSERTION FAILED : EXPECTED " + expected.toString() + " Actual :" + actual.toString());
            if (stopTestonFail) // if not continue with test
                Assert.assertEquals(actual, expected, desc);
        }

    }

    /****
     * Description : this function will log the assertion output to extent file and do assertions Usage : parameter : expected value, actual value, description
     * to print in report
     */
    public void assertEquals(final Object actual, final Object expected, final String desc) {
        final String strM = "Expected :" + expected.toString() + "|Actual :" + actual.toString();
        try {
            Assert.assertEquals(actual.toString(), expected.toString(), desc + "|Details:" + strM);
            Assert.assertTrue(true);
            logger.logPass(desc);
        } catch (final AssertionError e) {
            logger.logFail(desc + "|Details:" + strM);
            System.out.println("*********ASSERTION FAILED : EXPECTED " + expected.toString() + " Actual :" + actual.toString());
            if (stopTestonFail) // if not continue with test
                Assert.assertEquals(actual, expected, desc);
        }

    }

    public void assertTrue(final boolean condition, final String desc) {
        final String strM = "Expected : true | Actual : " + condition;
        try {
            Assert.assertTrue(condition, desc + "|Details:" + strM);
            logger.logPass(desc);
        } catch (final AssertionError e) {
            logger.logFail(desc + "|Details:" + strM);
            System.out.println("*********ASSERTION FAILED : EXPECTED true Actual : " + condition);
            if (stopTestonFail) // if not continue with test
                Assert.assertTrue(condition, desc);
        }
    }
}
