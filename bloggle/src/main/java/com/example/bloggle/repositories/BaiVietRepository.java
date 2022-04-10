package com.example.bloggle.repositories;

import com.example.bloggle.entities.BaiViet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long>{
    @Query(value = "SELECT * FROM BAIVIET WHERE IDCD = :id ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> cacBaiVietCoChuDe(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM BAIVIET WHERE IDCD = :id AND TIEUDE LIKE %:keyword% ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> timBaiVietVoiTuKhoaVaChuDe(@Param("id") Long id, @Param("keyword") String keyword);
    
    @Query(value = "SELECT * FROM BAIVIET WHERE TIEUDE LIKE %:keyword% ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> timBaiVietVoiTuKhoa(@Param("keyword") String keyword);
}
