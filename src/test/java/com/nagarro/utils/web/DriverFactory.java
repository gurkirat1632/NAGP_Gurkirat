package com.nagarro.utils.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private final static ThreadLocal<WebDriver> tl_Driver = new ThreadLocal<>();

    private static WebDriver driver;

    private DriverFactory() {
        // singleton Design pattern
    }

    public static void initDriver(final String strBrowser) {

        switch (strBrowser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                final Chrome optionsObject = new Chrome();
                tl_Driver.set(new ChromeDriver(optionsObject.options));

                break;
            case "firefox":
                tl_Driver.set(new FirefoxDriver());
                break;
            case "edge":
                tl_Driver.set(new EdgeDriver());
                break;
            default:
                driver = null;

        }

        driver = getDriver();

    }

    public static WebDriver getDriver() {
        return tl_Driver.get();
    }

}
