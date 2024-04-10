package com.hesias.weatherapi.service;

import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.repository.ReportRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class ReportService {

    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

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
