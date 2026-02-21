package com.example.Employee.Management.repository;

import com.example.Employee.Management.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
