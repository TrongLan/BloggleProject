package com.example.bloggle.services;

import com.example.bloggle.compositekeys.BinhLuanPK;
import com.example.bloggle.compositekeys.ChamDiemPK;
import com.example.bloggle.compositekeys.ReportPK;
import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChamDiem;
import com.example.bloggle.entities.Report;
import java.util.List;

public interface BaiVietService {
    List<BaiViet> tatCaBaiViet();
    List<BaiViet> cacBaiVietGanDay();
    List<BaiViet> cacBaiVietCoChuDe(Long id);
    List<BaiViet> timKiemBaiViet(Long idcd, String keyword);
    List<BaiViet> timKiemBaiViet(String keyword);
    BaiViet baiVietCoId(Long id);
    void xoaBaiViet(Long id);
    void danhDauLaKhongBiReport(Long id);
    List<Long> tatCaReport();
    List<BaiViet> danhSachBaiVietTuanHienTaiTheoChuDe(Long idcd);
    List<BaiViet> danhSachBaiVietCuaTaiKhoan(Long idtk);
    BaiViet luuBaiVietMoi(BaiViet bv);
    BinhLuan luuBinhLuanMoi(BinhLuan bl);
    List<BinhLuan> cacBinhLuanThuocBaiViet(Long idbv);
    void xoaBinhLuan(BinhLuanPK blpk);
    public BinhLuan binhLuanCoId(BinhLuanPK blpk);
    ChamDiem luuDiem(ChamDiem score);
    Report luuReport(Report r);
    boolean daChamDiem(ChamDiemPK cdpk);
    boolean daReport(ReportPK rpk);
    ChamDiem layChamDiem(ChamDiemPK cdpk);
    Report layReport(ReportPK rpk);
    boolean isReported(Long idbv);
}
