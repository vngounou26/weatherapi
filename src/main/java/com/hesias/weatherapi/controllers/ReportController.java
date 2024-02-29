package com.hesias.weatherapi.controllers;

import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/api/v1")
@CrossOrigin(origins = "localhost:5500")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public ResponseEntity<Iterable<Report>> getAllReports() {
        try {
            var reports = reportService.getAllReports();
            if (reports == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable final Long id) {
        try {
            Optional<Report> report = reportService.getReportById(id);
            return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> saveReport(@Validated @RequestBody Report _report) {
        try {
            var report = reportService.saveReport(_report);
            if (report == null) {
                return ResponseEntity.notFound().build();
            }
            return new ResponseEntity<>(report, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/reports/{id}")
    public ResponseEntity<Boolean> deleteReportById(final Long id) {
        var report= reportService.deleteReportById(id);
        if (!report) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(report);
    }

    @PutMapping("/reports/{id}")
    public ResponseEntity<Report> updateReportById(@PathVariable final Long id,@Validated @RequestBody Report _report) {
        var report= reportService.getReportById(id);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reportValue = report.get();
        reportValue.setHumidity(_report.getHumidity());
        reportValue.setTemperature(_report.getTemperature());
        reportValue.setLatitude(_report.getLatitude());
        reportValue.setLongitude(_report.getLongitude());
        reportService.saveReport(report.get());
        return ResponseEntity.ok(report.get());
    }

    @GetMapping("/reports/radius")
    public ResponseEntity<Iterable<Report>> getReportsInRadius(@RequestParam double latitude, @RequestParam double longitude, @RequestParam double radius) {
        var reports = reportService.getReportsInRadius(latitude, longitude, radius);
        if (reports == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

}
