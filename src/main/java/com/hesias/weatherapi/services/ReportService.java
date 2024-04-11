package com.hesias.weatherapi.services;

import com.hesias.weatherapi.dto.ReportDto;
import com.hesias.weatherapi.mapper.ReportMapper;
import com.hesias.weatherapi.model.Report;
import com.hesias.weatherapi.repositories.ReportRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    public Iterable<ReportDto> getAllReports(int sortType) {
        List<Report> reports = null;
        if (sortType == 0) {
            reports=reportRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
        }
        else {
            reports= reportRepository.findAll(Sort.by(Sort.Direction.ASC, "temperature"));
        }
        if (reports.isEmpty()) {
            return null;
        }
        return reports.stream().map(ReportMapper::toReportDto).toList();
    }

    public ReportDto saveReport(ReportDto reportDto) {
        if (reportDto == null) {
            return null;
        }
        var report = ReportMapper.toReport(reportDto);
        return ReportMapper.toReportDto(reportRepository.save(report));
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

    public Optional<ReportDto> getReportById(final Long id) {

        return reportRepository.findById(id).map(ReportMapper::toReportDto);
    }

    public Iterable<ReportDto> findReportsByLocationAndRadius(Double givenLatitude, Double givenLongitude, Double kmRadius) {
        return reportRepository.findReportsByLocationAndRadius(givenLatitude, givenLongitude, kmRadius).stream().map(ReportMapper::toReportDto).toList();
    }


}
