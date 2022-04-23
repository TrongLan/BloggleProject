package com.example.bloggle.repositories;

import com.example.bloggle.compositekeys.BinhLuanPK;
import com.example.bloggle.entities.BinhLuan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BinhLuanRepository extends JpaRepository<BinhLuan, BinhLuanPK>{
    @Query(value = "SELECT * FROM BINHLUAN WHERE IDBV = :idbv ORDER BY ID DESC", nativeQuery = true)
    List<BinhLuan> danhSachBinhLuanCuaBaiViet(@Param("idbv") Long id);
}
