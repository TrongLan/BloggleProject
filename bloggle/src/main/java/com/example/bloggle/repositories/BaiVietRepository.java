package com.example.bloggle.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.bloggle.entities.BaiViet;

public interface BaiVietRepository extends JpaRepository<BaiViet, Long>{
    @Query(value = "SELECT * FROM BAIVIET WHERE TGDANG >= dateadd(day,datediff(day,3,GETDATE()),0) ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> cacBaiVietGanDay();
    
    @Query(value = "SELECT * FROM BAIVIET WHERE IDCD = :id ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> cacBaiVietCoChuDe(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM BAIVIET WHERE IDCD = :id AND TGDANG >= dateadd(day, 1-datepart(dw, getdate()), CONVERT(date,getdate())) AND TGDANG <  dateadd(day, 8-datepart(dw, getdate()), CONVERT(date,getdate()))", nativeQuery = true)
    List<BaiViet> danhSachBaiVietTrongTuanTheoChuDe(@Param("id") Long id);
    
    @Query(value = "SELECT * FROM BAIVIET WHERE IDCD = :id AND TIEUDE LIKE %:keyword% ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> timBaiVietVoiTuKhoaVaChuDe(@Param("id") Long id, @Param("keyword") String keyword);
    
    @Query(value = "SELECT * FROM BAIVIET WHERE TIEUDE LIKE %:keyword% ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> timBaiVietVoiTuKhoa(@Param("keyword") String keyword);
    
    @Query(value = "SELECT REPORT.IDBV FROM BAIVIET, REPORT WHERE BAIVIET.ID = REPORT.IDBV GROUP BY REPORT.IDBV", nativeQuery = true)
    List<Long> danhSachIdBaiVietBiBaoCao();
    
    @Query(value = "SELECT * FROM BAIVIET WHERE IDTK = :idtk ORDER BY TGDANG DESC", nativeQuery = true)
    List<BaiViet> danhSachBaiVietCuaTaiKhoan(@Param("idtk") Long id);
    
}
