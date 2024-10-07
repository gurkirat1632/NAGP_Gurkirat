package com.nagarro.utils.reporting;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nagarro.config.Configs;

public class ExtentReportManager {

    static Map<Integer, ExtentTest>      extentTestMap  = new HashMap<>();
    static Map<Integer, ScenarioResults> scenarioResult = new HashMap<>();
    public static ExtentReports          reports;

    public static void initializeReporter() {
        reports = new ExtentReports();
        final ExtentSparkReporter reporter = new ExtentSparkReporter(Configs.strReportPath);
        reporter.config().setReportName("NAGP Selenium Report");
        reporter.config().setDocumentTitle("NAGP Results");
        reporter.config().setTheme(Theme.DARK);
        reports.attachReporter(reporter);
        reports.setSystemInfo("URL", Configs.getPropertyConfig("url"));
        reports.setSystemInfo("OS", System.getProperty("os.name"));
        reports.setSystemInfo("BROWSER", Configs.browser);

    }

    public static void flushReport() {
        reports.flush();
    }

    @SuppressWarnings("deprecation")
    public static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    public static synchronized ScenarioResults getScenarioResult() {
        return scenarioResult.get((int) Thread.currentThread().getId());
    }

    public static synchronized ExtentTest startTest(final String strName, final String strDesc) {
        final ExtentTest test = reports.createTest(strName, strDesc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        scenarioResult.put((int) Thread.currentThread().getId(), new ScenarioResults());

        return test;
    }

}
