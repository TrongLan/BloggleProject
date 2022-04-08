package com.example.bloggle.serviceImpl;

import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.repositories.TaiKhoanRepository;
import com.example.bloggle.services.TaiKhoanService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService{
    private TaiKhoanRepository tkRepo;

    public TaiKhoanServiceImpl(TaiKhoanRepository tkRepo) {
        this.tkRepo = tkRepo;
    }
    
    @Override
    public List<TaiKhoan> danhSachTatCaTaiKhoan() {
        return tkRepo.danhSachTatCaNguoiDung();
    }

    @Override
    public TaiKhoan taiKhoanCoID(Long id) {
        return tkRepo.getById(id);
    }

    @Override
    public boolean daTonTaiEmail(String email) {
        TaiKhoan tk = tkRepo.findTaiKhoanByEmail(email);
        if(tk == null) return false;
        else return true;
    }

    @Override
    public boolean daTonTaiUsername(String username) {
        TaiKhoan tk = tkRepo.findTaiKhoanByUsername(username);
        if(tk == null) return false;
        else return true;
    }

    @Override
    public TaiKhoan taiKhoanCoEmail(String email) {
        return tkRepo.findTaiKhoanByEmail(email);
    }

    @Override
    public TaiKhoan taiKhoanAdmin() {
        return tkRepo.findTaiKhoanAdmin();
    }

    @Override
    public TaiKhoan taiKhoanCoUsername(String username) {
        return tkRepo.findTaiKhoanByUsername(username);
    }

    @Override
    public void xoaTaiKhoan(Long id) {
        tkRepo.deleteById(id);
    }

    @Override
    public TaiKhoan capNhatTaiKhoan(TaiKhoan tk) {
        return tkRepo.save(tk);
    }

    @Override
    public List<TaiKhoan> timKiemTaiKhoan(String keyword) {
        return tkRepo.findTaiKhoanByKeyword(keyword);
    }
    
}
