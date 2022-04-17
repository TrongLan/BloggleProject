package com.example.bloggle.repositories;

import com.example.bloggle.compositekeys.ChamDiemPK;
import com.example.bloggle.entities.ChamDiem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamDiemRepository extends JpaRepository<ChamDiem, ChamDiemPK>{
    
}
