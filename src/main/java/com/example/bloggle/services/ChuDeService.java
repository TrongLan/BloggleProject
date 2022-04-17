package com.example.bloggle.services;

import com.example.bloggle.entities.ChuDe;
import java.util.List;

public interface ChuDeService {
    List<ChuDe> tatCaChuDe();
    ChuDe chuDeCoId(Long id);
    ChuDe capNhatChuDe(ChuDe cd);
}
