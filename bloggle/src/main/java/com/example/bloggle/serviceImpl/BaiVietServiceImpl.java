package com.example.bloggle.serviceImpl;

import com.example.bloggle.compositekeys.BinhLuanPK;
import com.example.bloggle.compositekeys.ChamDiemPK;
import com.example.bloggle.compositekeys.ReportPK;
import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChamDiem;
import com.example.bloggle.entities.Report;
import com.example.bloggle.repositories.BaiVietRepository;
import com.example.bloggle.repositories.BinhLuanRepository;
import com.example.bloggle.repositories.ChamDiemRepository;
import com.example.bloggle.repositories.ReportRepository;
import com.example.bloggle.services.BaiVietService;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BaiVietServiceImpl implements BaiVietService{
    private BaiVietRepository bvRepo;
    private ReportRepository reportRepo;
    private BinhLuanRepository blRepo;
    private ChamDiemRepository cdRepo;

    public BaiVietServiceImpl(BaiVietRepository bvRepo, ReportRepository reportRepo, BinhLuanRepository blRepo, ChamDiemRepository cdRepo) {
        this.bvRepo = bvRepo;
        this.reportRepo = reportRepo;
        this.blRepo = blRepo;
        this.cdRepo = cdRepo;
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

    @Override
    public List<BaiViet> danhSachBaiVietCuaTaiKhoan(Long idtk) {
        return bvRepo.danhSachBaiVietCuaTaiKhoan(idtk);
    }

    @Override
    public BaiViet luuBaiVietMoi(BaiViet bv) {
        return bvRepo.save(bv);
    }

    @Override
    public List<BaiViet> cacBaiVietGanDay() {
        return bvRepo.cacBaiVietGanDay();
    }

    @Override
    public BinhLuan luuBinhLuanMoi(BinhLuan bl) {
        return blRepo.save(bl);
    }

    @Override
    public List<BinhLuan> cacBinhLuanThuocBaiViet(Long idbv) {
        return blRepo.danhSachBinhLuanCuaBaiViet(idbv);
    }   

    @Override
    public void xoaBinhLuan(BinhLuanPK blpk) {
        blRepo.deleteById(blpk);
    }
    
    @Override
    public BinhLuan binhLuanCoId(BinhLuanPK blpk){
        return blRepo.getById(blpk);
    }

    @Override
    public ChamDiem luuDiem(ChamDiem score) {
        return cdRepo.save(score);
    }

    @Override
    public boolean daChamDiem(ChamDiemPK cdpk) {
        return cdRepo.existsById(cdpk);
    }

    @Override
    public ChamDiem layChamDiem(ChamDiemPK cdpk) {
        return cdRepo.getById(cdpk);
    }

    @Override
    public boolean daReport(ReportPK rpk) {
        return reportRepo.existsById(rpk);
    }

    @Override
    public Report layReport(ReportPK rpk) {
        return reportRepo.getById(rpk);
    }

    @Override
    public Report luuReport(Report r) {
        return reportRepo.save(r);
    }
    
    @Override
    public boolean isReported(Long idbv){
        List<Report> ds = reportRepo.findReportByIdbv(idbv);
        if(ds.isEmpty())
            return false;
        else
            return true;
    }
}
