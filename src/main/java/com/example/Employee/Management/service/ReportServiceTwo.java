package com.example.Employee.Management.service;


import com.example.Employee.Management.model.Report;
import com.example.Employee.Management.repository.ReportRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceTwo {

    private final ReportRepository reportRepository;
    private final CsvReportGenerator csvReportGenerator;

    public ReportServiceTwo(ReportRepository reportRepository,
                         CsvReportGenerator csvReportGenerator){
        this.reportRepository = reportRepository;
        this.csvReportGenerator = csvReportGenerator;

    }


    // there taskExecutor are  mean we config  limits off Thread POOl
    @Async("taskExecutor")
    public void generateAndSaveReport() throws IOException{


        try {
            // example report data
            List<String> reportData = List.of(
                    "ID,NAME,AMOUNT",
                    "1,Order-1,500",
                    "2,Order-2,1200"
            );

            // Generate CSV
            String filePath = csvReportGenerator.generateCsv(reportData);

            // save report info in db
            Report report = new Report();
            report.setReportName("Daily Sales Report");
            report.setFilePath(filePath);
            report.setCreatedAt(LocalDateTime.now());

            reportRepository.save(report);

            System.out.println(" Report saved in DB & File System  ");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


// “Heavy report generation is executed asynchronously
// using a custom thread pool to avoid blocking the main thread.”