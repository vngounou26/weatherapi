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
            var report = new ReportDto(45.75798944121002, 3.1323735428663837, 15, "Snow");
            var report2 = new ReportDto(45.76160410108799, 3.1296820830990053, 20, "Rain");
            var report3 = new ReportDto(45.759303890077916, 3.1259813259188602, 25, "Clear");

            reportService.saveReport(report);
            reportService.saveReport(report2);
            reportService.saveReport(report3);
        }

        System.out.println("Reports Loaded: " + reportRepository.count());
    }
}
