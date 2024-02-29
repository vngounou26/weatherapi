package com.hesias.weatherapi.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSequence")
    @SequenceGenerator(name = "weatherSequence", sequenceName = "weatherSequence", allocationSize = 1)
    private int id;

    protected Report() {

    }

    public double setLatitude(double latitude) {
        return this.latitude = latitude;
    }

    public double setLongitude(double longitude) {
        return this.longitude = longitude;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    // set by the server
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp=LocalDateTime.now();

    @Getter
    private double latitude;

    @Getter
    private double longitude;

    @Getter
    private String temperature;

    @Getter
    private String humidity;


    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public Report(double latitude, double longitude, String temperature, String humidity) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
