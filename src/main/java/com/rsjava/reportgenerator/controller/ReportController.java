package com.rsjava.reportgenerator.controller;

import com.rsjava.reportgenerator.reports.ReportsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ReportController {

    private final ReportsService reportsService;

    @GetMapping("reports/cars")
    public void getReport(){
        reportsService.generateReport();
    }

}
