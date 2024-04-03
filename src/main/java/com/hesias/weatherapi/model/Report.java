package com.hesias.weatherapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weatherSequence")
    @SequenceGenerator(name = "weatherSequence", sequenceName = "weatherSequence", allocationSize = 1)
    private int id;

    protected Report() {

    }

    // set by the server
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestamp", nullable = false, updatable = false)
    private LocalDateTime timestamp=LocalDateTime.now();

    @Setter
    @Getter
    private Double latitude;

    @Setter
    @Getter
    private Double longitude;

    @Setter
    @Getter
    private String temperature;

    @Setter
    @Getter
    private String humidity;


    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public Report(Double latitude, Double longitude, String temperature, String humidity) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.temperature = temperature;
        this.humidity = humidity;
    }

}
