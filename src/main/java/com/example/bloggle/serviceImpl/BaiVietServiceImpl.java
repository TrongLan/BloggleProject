/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bloggle.serviceImpl;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.repositories.BaiVietRepository;
import com.example.bloggle.services.BaiVietService;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaiVietServiceImpl implements BaiVietService {

    @Autowired
    private BaiVietRepository bvRepo;

    public BaiVietServiceImpl(BaiVietRepository bvRepo) {
        this.bvRepo = bvRepo;
    }

    @Override
    public BaiViet themBaiViet(BaiViet bv) {
        return bvRepo.save(bv);
    }

    @Override
    public BaiViet baiVietCoId(Long id) {
        return bvRepo.getById(id);
    }

    @Override
    public List<BaiViet> listAll() {
        return (List<BaiViet>) bvRepo.findAll();
    }

    @Override
    public void save(BaiViet baiviet) {
        bvRepo.save(baiviet);
    }

    @Override
    public BaiViet get(Long id) {
        Optional<BaiViet> result = bvRepo.findById(id);
        if (result.isPresent()){
            return result.get();    
        }
        try {
            throw new BaiVietNotFoundException("Không tìm thấy bài viết" + id);
        } catch (BaiVietNotFoundException ex) {
            Logger.getLogger(BaiVietServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<BaiViet> timKiemBaiViet(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void xoa(Long id) {
        Long count = bvRepo.countById(id);
        if (count == null || count==0){
            try {
                throw new BaiVietNotFoundException("Không tìm thấy bài viết" + id);
            } catch (BaiVietNotFoundException ex) {
                Logger.getLogger(BaiVietServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        bvRepo.deleteById(id);
    }
    
}
