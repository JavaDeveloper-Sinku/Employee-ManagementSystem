package com.example.Employee.Management.controller;


import com.example.Employee.Management.service.EmployeeService;
import com.example.Employee.Management.service.ReportThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api1/report")
public class ReportController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/generate")
    public String generateReport(){
        ReportThread thread = new ReportThread(service);
        thread.start();                                                                 // background me report generate
        return " Report generation started in background. ";
    }
}


// if you can run heavy task in background in you service then  use Threads
//Java mai Thread ek separate execution path hta  hai.
//Agar aap heavy task ko background me run karna chahte ho, to thread use karte ho.
//User take instance response , and report generate in  background process  .

//
//✅ Advantages
//Easy to understand
//No extra configuration
//Good for learning

//❌ Disadvantages
//Thread management difficult hota hai
//Resource heavy
//Large apps me bugs and performance issues