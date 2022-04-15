package com.example.bloggle.controllers;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.services.BaiVietService;
import com.example.bloggle.services.ChuDeService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuanLyBaiViet {
    private BaiVietService bvService;
    private ChuDeService cdService;

    public QuanLyBaiViet(BaiVietService bvService, ChuDeService cdService) {
        this.bvService = bvService;
        this.cdService = cdService;
    }
    
    @GetMapping("/admin/quanLyBV")
    public String trangQuanLyBV(Model mod, @RequestParam(value = "m", required = true) String mode, @RequestParam(value = "idcd", required = false) Long idcd){
        List<BaiViet> dsbv = new ArrayList<>();
        List<ChuDe> dscd = new ArrayList<>();
        ChuDe cd = new ChuDe();
        String url = "";
        if(mode.equals("report_list")){
            List<Long> ds_idbv = bvService.tatCaReport();
            if(!ds_idbv.isEmpty())
            	for(Long id: ds_idbv) {
            		BaiViet bv = bvService.baiVietCoId(id);
            		dsbv.add(bv);
            	}
            url = "GiaoDienAdmin/danhSachReport";
        }
        if(mode.equals("weekly_statistics")) {
        	dscd = cdService.tatCaChuDe();
        	if(idcd != null) {
        		cd = cdService.chuDeCoId(idcd);
        		dsbv = bvService.danhSachBaiVietTuanHienTaiTheoChuDe(idcd);
        		Collections.sort(dsbv, BaiViet.Comparators.DIEMDANHGIA);
        		
        	}
        	url = "GiaoDienAdmin/xemThongKe";	
        }
        mod.addAttribute("dscd", dscd);
        mod.addAttribute("dsbv", dsbv);
        mod.addAttribute("chude", cd);
        return url;
    }
    
    @GetMapping("/admin/quanLyBV/{idbv}/reasons")
    public String xemLyDoBaoCao(Model mod, @PathVariable("idbv") Long idbv){
        BaiViet bv = bvService.baiVietCoId(idbv);
        System.out.println(bv.getTieude());
        mod.addAttribute("baiviet", bv);
        return "GiaoDienAdmin/trangLyDo";
    }
    
    @GetMapping("/admin/xoaBV/{idbv}")
    public String xoaBaiViet(@PathVariable Long idbv,@RequestParam(value = "m", required = true) String mode){
        if(mode.equals("delete"))
            bvService.xoaBaiViet(idbv);
        if(mode.equals("remove"))
            bvService.danhDauLaKhongBiReport(idbv);
        return "redirect:/admin/quanLyBV?m=report_list";
    }
    
}
