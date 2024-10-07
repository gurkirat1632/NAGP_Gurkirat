package com.nagarro.utils.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadExcelAsDB {

    public static synchronized Map<String, String> getExcelDataInDictionary(final String scenarioName, final String columns) throws FilloException {
        final Fillo fillo = new Fillo();
        Connection connection;

        connection = fillo.getConnection("");
        String[] arrayColumns;
        Recordset recordset;
        String strQuery;
        final Map<String, String> dictMap = new HashMap<>();

        strQuery = "Select " + columns + " from TestData where ScenarioID='" + scenarioName + "'";

        recordset = connection.executeQuery(strQuery);

        arrayColumns = columns.split(",");

        if (recordset.getCount() == 0) {
            throw new IllegalArgumentException(scenarioName + " Scenario Id Does not exist in the Sheet");
        }

        while (recordset.next()) {
            for (final String arrayColumn : arrayColumns) {
                dictMap.put(arrayColumn, recordset.getField(arrayColumn));
            }
        }
        recordset.close();
        connection.close();

        return dictMap;
    }

    public static synchronized List<String> getColumnDataInList(final String strPath, final String sheetName, final String columnName) throws FilloException {
        final Fillo fillo = new Fillo();
        Connection connection;

        connection = fillo.getConnection(strPath);
        Recordset recordset;
        String strQuery;
        final List<String> list_Data = new ArrayList<>();

        strQuery = "Select * from " + sheetName;

        recordset = connection.executeQuery(strQuery);

        if (recordset.getCount() == 0) {
            throw new IllegalArgumentException(columnName + " Does not exist in the Sheet");
        }

        while (recordset.next()) {

            list_Data.add(recordset.getField(columnName));

        }
        recordset.close();
        connection.close();

        return list_Data;
    }

}
