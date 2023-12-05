package com.hesias.weatherapi.data;

import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.repositories.ReportRepository;
import com.hesias.weatherapi.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.geo.Point;
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
            Report report = new Report(new Point(1, 1),"24","Ciel Bleu");
            Report report2 = new Report(new Point(1, 1),"24","Eclairer");
            report2.setHumidity("");
            Report report3 = new Report(new Point(1, 1),"25","Nuageux");

            reportService.saveReport(report);
            reportService.saveReport(report2);
            reportService.saveReport(report3);
        }

        System.out.println("Reports Loaded: " + reportRepository.count());
    }
}
