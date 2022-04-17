package com.example.bloggle.repositories;

import com.example.bloggle.compositekeys.BinhLuanPK;
import com.example.bloggle.entities.BinhLuan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, BinhLuanPK>{
    
}
