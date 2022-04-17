package com.example.bloggle.repositories;

import com.example.bloggle.entities.BaiViet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long>{
    public Long countById(Long id);
    
}
