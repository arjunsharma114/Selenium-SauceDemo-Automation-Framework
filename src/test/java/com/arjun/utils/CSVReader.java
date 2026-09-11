package com.arjun.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static Object[][] readCSV(String filePath) {

        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            // Skip header
            br.readLine();

            String line;

            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    data.add(new Object[]{line.trim()});
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data.toArray(new Object[0][]);
    }
}