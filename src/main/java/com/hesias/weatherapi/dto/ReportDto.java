package com.hesias.weatherapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDto {
    private Double latitude;
    private Double longitude;
    private String temperature;
    private String observation;
    private String timestamp;

    public ReportDto(Double latitude, Double longitude, String temperature, String observation, String timestamp){
        this(latitude, longitude, temperature, observation);
        this.timestamp = timestamp;
    }

    public ReportDto(Double latitude, Double longitude, String temperature, String observation){
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.observation = observation;
        this.timestamp = LocalDateTime.now().toString();
    }
}
