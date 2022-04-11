package com.example.bloggle.services;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.Report;
import java.util.List;

public interface BaiVietService {
    List<BaiViet> tatCaBaiViet();
    List<BaiViet> cacBaiVietCoChuDe(Long id);
    List<BaiViet> timKiemBaiViet(Long idcd, String keyword);
    List<BaiViet> timKiemBaiViet(String keyword);
    BaiViet baiVietCoId(Long id);
    void xoaBaiViet(Long id);
    void danhDauLaKhongBiReport(Long id);
    List<Report> tatCaReport();
}
