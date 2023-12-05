package com.hesias.weatherapi.services;

import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.repositories.ReportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Data
@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Iterable<Report> getAllReports() {
        var reports = reportRepository.findAll();
        if (reports.isEmpty()) {
            return null;
        }
        return reports;
    }

    public Report saveReport(Report report) {
        if (report == null) {
            return null;
        }
//        report.setTimestamp(LocalDateTime.now());
        return reportRepository.save(report);
    }

    public boolean deleteReportById(final Long id) {
        var report=getReportById(id);
        if (report.isPresent()) {
            reportRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }

    public Optional<Report> getReportById(final Long id) {
        return reportRepository.findById(id);
    }


}
