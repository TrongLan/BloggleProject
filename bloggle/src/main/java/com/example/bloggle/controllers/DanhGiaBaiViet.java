package com.example.bloggle.controllers;

<<<<<<< HEAD
import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChamDiem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class DanhGiaBaiViet {
    @Autowired
    private BinhLuan blservice;
    
    @Autowired
    private BaiViet bvservice;
    
    @Autowired
    private ChamDiem cdservice;

    public DanhGiaBaiViet() {
    }

    public DanhGiaBaiViet(BinhLuan blservice, BaiViet bvservice, ChamDiem cdservice) {
        this.blservice = blservice;
        this.bvservice = bvservice;
        this.cdservice = cdservice;
    }
=======
public class DanhGiaBaiViet {
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
    
}
