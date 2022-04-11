package com.example.bloggle.repositories;

import com.example.bloggle.compositekeys.ReportPK;
import com.example.bloggle.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReportRepository extends JpaRepository<Report, ReportPK>{
    @Modifying
    @Transactional
    @Query(value="DELETE FROM REPORT WHERE IDBV = :id", nativeQuery = true)
    int removeReportedPost(@Param("id") Long idbv);
}
