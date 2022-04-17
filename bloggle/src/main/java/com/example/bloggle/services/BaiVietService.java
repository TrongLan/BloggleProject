package com.example.bloggle.services;

import com.example.bloggle.entities.BaiViet;
import java.util.List;

public interface BaiVietService {   
    List<BaiViet> listAll();
    BaiViet baiVietCoId(Long id);
    BaiViet themBaiViet(BaiViet bv);
    void save(BaiViet baiviet);
    BaiViet get(Long id);
    List<BaiViet> timKiemBaiViet(String keyword);
    void xoa(Long id);
}
