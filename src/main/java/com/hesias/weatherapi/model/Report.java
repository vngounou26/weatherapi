package com.hesias.weatherapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSequence")
    @SequenceGenerator(name = "weatherSequence", sequenceName = "weatherSequence", allocationSize = 1)
    private int id;

//    protected Report() {
//
//    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp;

    private Double latitude;

    private Double longitude;

    private int temperature;

    private String observation;

    public Report() {
        this.timestamp = LocalDateTime.now();
    }


    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public Report(Double latitude, Double longitude, int temperature, String observation) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.observation = observation;
        this.timestamp = LocalDateTime.now();
    }

}
