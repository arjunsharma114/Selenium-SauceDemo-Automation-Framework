package com.arjun.data;

import org.testng.annotations.DataProvider;

import com.arjun.utils.CSVReader;

public class TestDataProvider {

    @DataProvider(name = "searchData")
    public Object[][] searchData() {

        String filePath = "src/test/resources/testdata/SearchData.csv";

        return CSVReader.readCSV(filePath);
    }
}