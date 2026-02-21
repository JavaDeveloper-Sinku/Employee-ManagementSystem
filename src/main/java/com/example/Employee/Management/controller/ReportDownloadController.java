package com.example.Employee.Management.controller;


import com.example.Employee.Management.model.Report;
import com.example.Employee.Management.repository.ReportRepository;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/download/reports")
public class ReportDownloadController {

    private final ReportRepository reportRepository;

    public ReportDownloadController(ReportRepository reportRepository){
        this.reportRepository = reportRepository;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Resource> downloadReport(@PathVariable Long id ) throws IOException{

        Report report = reportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        File file = new File(report.getFilePath());

        Resource resource = new UrlResource(file.toURI());

        return  ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getName() + "\"")
                                .contentType(MediaType.parseMediaType("text/csv"))
                .body(resource);

    }
}
