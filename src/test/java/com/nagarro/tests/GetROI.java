package com.nagarro.tests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.nagarro.base.BaseClass;
import com.nagarro.utils.generic.ReadExcelAsDB;

public class GetROI extends BaseClass {

    // before and after class can be used in case we have specific req for class settings
    @BeforeClass
    public void beforeClass() {

    }

    @AfterClass
    public void afterClass() {

    }

    @Test(groups = { "Smoke", "Regression" })
    public void uploadTestCaseToKiwi() throws FilloException {
        final int intTestCaseID = 22;
        // launching and moving to page is part of before method
        // launching and moving to page is part of before method
        test = startTest("Get ROI", "Checking ROI is fteched or NOT");
        test.assignCategory("Login");
        final List<String> list_Summary = ReadExcelAsDB.getColumnDataInList("C:\\Users\\gurkiratsingh\\Desktop\\TestCaseFile.xlsx", "Sheet1", "Summary");
        final List<String> list_Notes = ReadExcelAsDB.getColumnDataInList("C:\\Users\\gurkiratsingh\\Desktop\\TestCaseFile.xlsx", "Sheet1", "Notes");
        final List<String> list_Automated = ReadExcelAsDB.getColumnDataInList("C:\\Users\\gurkiratsingh\\Desktop\\TestCaseFile.xlsx", "Sheet1", "Automated");
        final List<String> list_Priorty = ReadExcelAsDB.getColumnDataInList("C:\\Users\\gurkiratsingh\\Desktop\\TestCaseFile.xlsx", "Sheet1", "Priority");
        final List<String> list_Tag = ReadExcelAsDB.getColumnDataInList("C:\\Users\\gurkiratsingh\\Desktop\\TestCaseFile.xlsx", "Sheet1", "Tag");

        final String strFile = "C:\\Users\\gurkiratsingh\\Downloads\\UploadFileWithHandling.py";

        for (int i = 0; i < list_Summary.size(); i++) {

            try {
                // Specify the Python script and method to run
                final String[] command = { "python", strFile, list_Summary.get(i), list_Notes.get(i), list_Automated.get(i), list_Priorty.get(i),
                        list_Tag.get(i) };

                // Create a ProcessBuilder for the command
                final ProcessBuilder processBuilder = new ProcessBuilder(command);

                // Redirect the standard output and error to the Java process
                processBuilder.redirectErrorStream(true);

                // Start the Python process
                final Process process = processBuilder.start();

                // Read and print the Python script's output
                final InputStream inputStream = process.getInputStream();
                final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("Python Output: " + line);
                }

                // Wait for the Python process to complete
                final int exitCode = process.waitFor();
                System.out.println("Python Process exited with code: " + exitCode);
                if (exitCode == 0) {
                    System.out.println("Success Uploading Test Case for  row :" + i + " i.e " + list_Summary.get(i));
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Test(groups = { "Smoke", "Regression" })
    public void testLogout() {
        // launching and moving to page is part of before method
        // launching and moving to page is part of before method
        test = startTest("Get ROI", "Checking ROI is fteched or NOT");
        test.assignCategory("Login");
        Assert.assertEquals(true, false);

    }

}