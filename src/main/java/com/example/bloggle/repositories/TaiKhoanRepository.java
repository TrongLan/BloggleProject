package com.example.bloggle.repositories;

import com.example.bloggle.entities.TaiKhoan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long>{
    @Query(value = "SELECT * FROM TAIKHOAN WHERE EMAIL = :e", nativeQuery = true)
    TaiKhoan findTaiKhoanByEmail(@Param("e") String email);
    
    @Query(value = "SELECT * FROM TAIKHOAN WHERE TENTK = :name", nativeQuery = true)
    TaiKhoan findTaiKhoanByUsername(@Param("name") String nickname);
    
    @Query(value = "SELECT * FROM TAIKHOAN WHERE TENTK = :input OR EMAIL = :input", nativeQuery = true)
    TaiKhoan findTaiKhoanByEmailOrUsername(@Param("input") String input);
    
    @Query(value = "SELECT * FROM TAIKHOAN WHERE QUYEN = 'admin'", nativeQuery = true)
    TaiKhoan findTaiKhoanAdmin();
    
    @Query(value = "SELECT * FROM TAIKHOAN WHERE QUYEN = 'user'", nativeQuery = true)
    List<TaiKhoan> danhSachTatCaNguoiDung();
    
    @Query(value = "SELECT * FROM TAIKHOAN WHERE (EMAIL LIKE %:input% OR TENTK LIKE %:input%) AND QUYEN = 'user'", nativeQuery = true)
    List<TaiKhoan> findTaiKhoanByKeyword(@Param("input") String keyword);
    
}
