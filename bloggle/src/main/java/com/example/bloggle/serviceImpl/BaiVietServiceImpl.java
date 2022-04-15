/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.bloggle.serviceImpl;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.Report;
import com.example.bloggle.repositories.BaiVietRepository;
import com.example.bloggle.repositories.ReportRepository;
import com.example.bloggle.services.BaiVietService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BaiVietServiceImpl implements BaiVietService{
    private BaiVietRepository bvRepo;
    private ReportRepository reportRepo;

    public BaiVietServiceImpl(BaiVietRepository bvRepo, ReportRepository reportRepo) {
        this.bvRepo = bvRepo;
        this.reportRepo = reportRepo;
    }

    @Override
    public List<BaiViet> tatCaBaiViet() {
        return bvRepo.findAll(Sort.by(Sort.Direction.DESC, "tgdang"));
    }

    @Override
    public List<BaiViet> cacBaiVietCoChuDe(Long id) {
        return bvRepo.cacBaiVietCoChuDe(id);
    }

    @Override
    public List<BaiViet> timKiemBaiViet(Long idcd, String keyword) {
        return bvRepo.timBaiVietVoiTuKhoaVaChuDe(idcd, keyword);
    }

    @Override
    public List<BaiViet> timKiemBaiViet(String keyword) {
        return bvRepo.timBaiVietVoiTuKhoa(keyword);
    }

    @Override
    public BaiViet baiVietCoId(Long id) {
        return bvRepo.getById(id);
    }

    @Override
    public void xoaBaiViet(Long id) {
        bvRepo.deleteById(id);
    }

    @Override
    public void danhDauLaKhongBiReport(Long id) {
        reportRepo.removeReportedPost(id);
    }

    @Override
    public List<Long> tatCaReport() {
        return bvRepo.danhSachIdBaiVietBiBaoCao();
    }

	@Override
	public List<BaiViet> danhSachBaiVietTuanHienTaiTheoChuDe(Long idcd) {
		return bvRepo.danhSachBaiVietTrongTuanTheoChuDe(idcd);
	}
    
}
