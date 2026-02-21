package com.example.Employee.Management.service;


import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Component
public class CsvReportGenerator {

    private  static final String REPORT_DIR = "reports";

    public String generateCsv(List<String> data) throws IOException{

        File directory = new File(REPORT_DIR);
        if (!directory.exists()){
            directory.mkdirs();  // folder auto create

        }
        String fileName = "report_" + System.currentTimeMillis() + ".csv";
        File file = new File(directory, fileName);

        try(FileWriter writer = new FileWriter(file)){
            for (String row : data){
                writer.write(row);
                writer.write("\n");
            }
        }

        return file.getAbsolutePath();   // DB me save karo
    }
}

// Report Service 2  connected this file
