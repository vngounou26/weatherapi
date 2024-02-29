package com.hesias.weatherapi.data;

import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.repositories.ReportRepository;
import com.hesias.weatherapi.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReportDataLoader implements CommandLineRunner {
    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public void run(String... args) {
        loadReports();
    }

    private void loadReports() {
        if(reportRepository.count() == 0) {
            Report report = new Report(45.75975209690773, 3.130053946141058, "Cloudy", "10");
            Report report2 = new Report(45.759901800129796, 3.1315130676846348, "Sunny", "20");
            report2.setHumidity("");
            Report report3 = new Report(45.75707234128988, 3.132864900879419, "Rainy", "30");

            reportService.saveReport(report);
            reportService.saveReport(report2);
            reportService.saveReport(report3);
        }

        System.out.println("Reports Loaded: " + reportRepository.count());
    }
}
