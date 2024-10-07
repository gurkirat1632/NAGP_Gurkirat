package com.nagarro.utils.web;

import org.openqa.selenium.chrome.ChromeOptions;

public class Chrome {

    ChromeOptions options;

    public Chrome() {

        options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");
        // options.addArguments("headless");

    }

}
