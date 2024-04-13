package com.hesias.weatherapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReportDto {
    private Double latitude;
    private Double longitude;
    private int temperature;
    private String observation;
    private String created_at;

    public ReportDto(Double latitude, Double longitude, int temperature, String observation, String created_at){
        this(latitude, longitude, temperature, observation);
        this.created_at = created_at;
    }

    public ReportDto(){
        this.created_at = LocalDateTime.now().toString();
    }

    public ReportDto(Double latitude, Double longitude, int temperature, String observation){
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.observation = observation;
        this.created_at = LocalDateTime.now().toString();
    }
}
