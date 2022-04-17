package com.example.bloggle.repositories;

import com.example.bloggle.compositekeys.ReportPK;
import com.example.bloggle.entities.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, ReportPK>{
    
}
