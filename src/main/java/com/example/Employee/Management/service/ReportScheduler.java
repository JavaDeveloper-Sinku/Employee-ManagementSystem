package com.example.Employee.Management.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportScheduler {

    private final ReportService reportService;

    public ReportScheduler(ReportService reportService){
        this.reportService = reportService;


    }

    
    @Scheduled(cron = "0 0 1 * * ?") //daily 1 AM
    public void generateDailyReport(){
        reportService.generateReport();
    }
}


// Every day at 1:00 AM
// “I used Spring’s @Scheduled with cron expressions to automate daily report generation.”
// Scheduling any task  so Thread  only work at fixed time like  1 AM