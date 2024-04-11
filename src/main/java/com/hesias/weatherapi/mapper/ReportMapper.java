package com.hesias.weatherapi.mapper;

import com.hesias.weatherapi.dto.ReportDto;
import com.hesias.weatherapi.model.Report;

public class ReportMapper {
    public static Report toReport(ReportDto reportDto) {
        if (reportDto == null) {
            return null;
        }
        return new Report(reportDto.getLatitude(), reportDto.getLongitude(), reportDto.getTemperature(), reportDto.getObservation());
    }

    public static ReportDto toReportDto(Report report) {
        if (report == null) {
            return null;
        }
        return new ReportDto(report.getLatitude(), report.getLongitude(), report.getTemperature(), report.getObservation(), report.getTimestamp().toString());
    }
}
