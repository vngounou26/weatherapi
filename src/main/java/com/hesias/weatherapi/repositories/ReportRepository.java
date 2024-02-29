package com.hesias.weatherapi.repositories;

import com.hesias.weatherapi.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // recuperer les rapports dans un rayon donné
    @Query("select r from Report r where SQRT(POWER(r.latitude - ?1, 2) + POWER(r.longitude - ?2, 2)) <= ?3")
    //@Query("select r from Report r where (6371 * acos(cos(radians(?1)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(?2)) + sin(radians(?1)) * sin(radians(r.latitude)))) < ?3")
    Iterable<Report> findReportsInRadius(double latitude, double longitude, double radius);


}
