package com.hesias.weatherapi.repositories;

import com.hesias.weatherapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r, (6371 * ACOS( COS(RADIANS(:givenLatitude)) * COS(RADIANS(r.latitude)) * COS(RADIANS(r.longitude) - RADIANS(:givenLongitude)) + SIN(RADIANS(:givenLatitude)) * SIN(RADIANS(r.latitude)) ) ) AS distance FROM Report r WHERE (6371 * ACOS( COS(RADIANS(:givenLatitude)) * COS(RADIANS(r.latitude)) * COS(RADIANS(r.longitude) - RADIANS(:givenLongitude)) + SIN(RADIANS(:givenLatitude)) * SIN(RADIANS(r.latitude)) ) ) <= :kmRadius ORDER BY  r.timestamp DESC")
//    @Query("SELECT r FROM Report r WHERE sdo_geom.sdo_distance ( sdo_geometry(2001, 4326, sdo_point_type(?, ?, null), null, null), sdo_geometry(2001, 4326, sdo_point_type(r.latitude, r.longitude, null), null, null), 0.01,'unit=KM') <= ? AND trunc(r.timestamp) = trunc(sysdate)")
    List<Report> findReportsByLocationAndRadius(Double givenLatitude, Double givenLongitude, Double kmRadius);


}
