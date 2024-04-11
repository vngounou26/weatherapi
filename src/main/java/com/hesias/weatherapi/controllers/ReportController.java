package com.hesias.weatherapi.controllers;

import com.hesias.weatherapi.dto.PostRequest;
import com.hesias.weatherapi.dto.ReportDto;
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

    @GetMapping("/reports/{sortType}")
    public ResponseEntity<Iterable<ReportDto>> getAllReports(@PathVariable int sortType) {
        try {
            var reports = reportService.getAllReports(sortType);
            // reorder the reports by timestamp in descending order


            if (reports == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reports, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/reports/{id}")
//    public ResponseEntity<ReportDto> getReportById(@PathVariable final Long id) {
//        try {
//            Optional<ReportDto> report = reportService.getReportById(id);
//            return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

    @PostMapping("/reports")
    public ResponseEntity<ReportDto> saveReport(@Validated @RequestBody ReportDto _report) {
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
    public ResponseEntity<ReportDto> updateReportById(@PathVariable final Long id,@Validated @RequestBody ReportDto _report) {
        var report= reportService.getReportById(id);
        if (report.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var reportValue = report.get();
        reportValue.setObservation(_report.getObservation());
        reportValue.setTemperature(_report.getTemperature());
        reportValue.setLatitude(_report.getLatitude());
        reportValue.setLongitude(_report.getLongitude());
        reportService.saveReport(report.get());
        return ResponseEntity.ok(report.get());
    }

    @PostMapping("/reports/find")
    public ResponseEntity<Iterable<ReportDto>> findReportsByLocationAndRadius(@RequestBody PostRequest postRequest) {
        var reports = reportService.findReportsByLocationAndRadius(postRequest.getLatitude(), postRequest.getLongitude(), postRequest.getKmRadius());
        if (reports == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(reports, HttpStatus.OK);

    }

}
