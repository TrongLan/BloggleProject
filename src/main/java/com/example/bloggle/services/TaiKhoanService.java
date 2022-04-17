package com.example.bloggle.services;

import com.example.bloggle.entities.TaiKhoan;
import java.util.List;

public interface TaiKhoanService {
    List<TaiKhoan> danhSachTatCaTaiKhoan();
    TaiKhoan taiKhoanCoID(Long id);
    boolean daTonTaiEmail(String email);
    boolean daTonTaiUsername(String username);
    TaiKhoan taiKhoanCoEmail(String email);
    TaiKhoan taiKhoanAdmin();
    TaiKhoan taiKhoanCoUsername(String username);
    void xoaTaiKhoan(Long id);
    TaiKhoan capNhatTaiKhoan(TaiKhoan tk);
    List<TaiKhoan> timKiemTaiKhoan(String keyword);
}
