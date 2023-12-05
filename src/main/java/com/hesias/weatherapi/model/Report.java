package com.hesias.weatherapi.model;

import jakarta.persistence.*;
import lombok.Getter;
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

    public void setPoint(Point point) {
        this.point = point;
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
    private Point point;

    @Getter
    private String temperature;

    @Getter
    private String humidity;


    public void setTimestamp(LocalDateTime timestamp) {

        this.timestamp = timestamp;
    }

    public Report( Point point, String temperature, String humidity) {
        this.point = point;
        this.temperature = temperature;
        this.humidity = humidity;
    }
}
