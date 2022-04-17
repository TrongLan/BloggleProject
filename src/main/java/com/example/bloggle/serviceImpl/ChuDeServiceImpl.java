package com.example.bloggle.serviceImpl;

import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.repositories.ChuDeRepository;
import com.example.bloggle.services.ChuDeService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ChuDeServiceImpl implements ChuDeService{

    private ChuDeRepository cdRepo;

    public ChuDeServiceImpl(ChuDeRepository cdRepo) {
        this.cdRepo = cdRepo;
    }
    
    @Override
    public List<ChuDe> tatCaChuDe() {
        return cdRepo.findAll();
    }

    @Override
    public ChuDe chuDeCoId(Long id) {
        return cdRepo.getById(id);
    }

    @Override
    public ChuDe capNhatChuDe(ChuDe cd) {
        return cdRepo.save(cd);
    }
    
}
