package com.hesias.weatherapi.repositories;

import com.hesias.weatherapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r, (6371 * ACOS( COS(RADIANS(:givenLatitude)) * COS(RADIANS(r.latitude)) * COS(RADIANS(r.longitude) - RADIANS(:givenLongitude)) + SIN(RADIANS(:givenLatitude)) * SIN(RADIANS(r.latitude)) ) ) AS distance FROM Report r WHERE (6371 * ACOS( COS(RADIANS(:givenLatitude)) * COS(RADIANS(r.latitude)) * COS(RADIANS(r.longitude) - RADIANS(:givenLongitude)) + SIN(RADIANS(:givenLatitude)) * SIN(RADIANS(r.latitude)) ) ) <= :kmRadius ORDER BY  r.timestamp DESC")
    List<Report> findReportsByLocationAndRadius(Double givenLatitude, Double givenLongitude, Double kmRadius);


}
