package com.hesias.weatherapi.data;

import com.hesias.weatherapi.dto.ReportDto;
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
    public void run(String... args) throws Exception {
        loadReports();
    }

    private void loadReports() {
        if(reportRepository.count() == 0) {
            var report = new ReportDto(1.2, 30.0, "15", "Snow");
            var report2 = new ReportDto(2.6, 25.0, "20", "Rain");
            report2.setObservation("");
            var report3 = new ReportDto(3.0, 20.0, "25", "Clear");

            reportService.saveReport(report);
            reportService.saveReport(report2);
            reportService.saveReport(report3);
        }

        System.out.println("Reports Loaded: " + reportRepository.count());
    }
}
