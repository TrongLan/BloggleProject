package com.example.bloggle.repositories;

import com.example.bloggle.entities.BaiViet;
<<<<<<< HEAD
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long>{
    public Long countById(Long id);
=======
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long>{
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
    
}
