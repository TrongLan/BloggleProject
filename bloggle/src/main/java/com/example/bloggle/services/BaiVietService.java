package com.example.bloggle.services;

import com.example.bloggle.entities.BaiViet;
import java.util.List;

public interface BaiVietService {
    List<BaiViet> tatCaBaiViet();
    List<BaiViet> cacBaiVietCoChuDe(Long id);
    List<BaiViet> timKiemBaiViet(Long idcd, String keyword);
    List<BaiViet> timKiemBaiViet(String keyword);
    BaiViet baiVietCoId(Long id);
}
