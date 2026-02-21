package com.example.Employee.Management.controller;


import com.example.Employee.Management.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api2/report")
public class ReportAsyncController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/generate")
    public String generate(){
        reportService.generateReport(); // background
        return " Report started in background using @Async";

    }

}


//✅ Advantages
//Spring manages threads (Thread pool)
//Better performance
//Safe for production
//Easy to configure and scale

//❌ Disadvantages
//Slightly advanced concept
//Need configuration for thread pool (optional but recommended)