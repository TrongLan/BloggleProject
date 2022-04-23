package com.example.bloggle.controllers;

import com.example.bloggle.compositekeys.BinhLuanPK;
import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.BaiVietService;
import com.example.bloggle.services.ChuDeService;
import com.example.bloggle.services.TaiKhoanService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuanLyBaiViet {
    private BaiVietService bvService;
    private ChuDeService cdService;
    private TaiKhoanService tkService;

    public QuanLyBaiViet(BaiVietService bvService, ChuDeService cdService, TaiKhoanService tkService) {
        this.bvService = bvService;
        this.cdService = cdService;
        this.tkService = tkService;
    }
    
    //CHỨC NĂNG QUẢN LÝ BÀI VIẾT CỦA ADMIN
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
    
    
    // CHỨC NĂNG QUẢN LÝ BÀI VIẾT CỦA NGƯỜI DÙNG
    @GetMapping("/u/danhSachBV")
    public String xemDanhSachBaiViet(Model mod){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        List<BaiViet> dsbv = bvService.danhSachBaiVietCuaTaiKhoan(tk.getId());
        mod.addAttribute("dsbv", dsbv);
        mod.addAttribute("tk", tk);
        return "GiaoDienNguoiDung/xemDsBaiViet";
    }
    
    @GetMapping("/u/editBV")
    public String moTrangTaoBaiViet(Model mod){
        mod.addAttribute("bv", new BaiViet());
        List<ChuDe> dscd = cdService.tatCaChuDe();
        mod.addAttribute("dscd", dscd);
        return "GiaoDienNguoiDung/trangVietBai";
    }
    
    @PostMapping("/u/uploadBV")
    public String dangTaiBaiViet(@ModelAttribute("bv") BaiViet bv){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        bv.setTk(tk);
        LocalDateTime n = LocalDateTime.now();
        bv.setTgdang(n);
        bv.setTgsua(n);
        bvService.luuBaiVietMoi(bv);
        return "redirect:/u/danhSachBV";
    }
    
    @GetMapping("/u/chinhSuaBV/{p}")
    public String moTrangSuaBaiViet(Model mod, @PathVariable("p") Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(id);
        if(tk.equals(bv.getTk())){
            List<ChuDe> dscd = cdService.tatCaChuDe();
            mod.addAttribute("dscd", dscd);
            mod.addAttribute("bv", bv);
            return "GiaoDienNguoiDung/trangChinhSuaBaiViet";
        }
        else
            return "redirect:/u/danhSachBV";
        
    }
    
    @PostMapping("/u/reuploadBV/{p}")
    public String reuploadBaiViet(BaiViet bv, @PathVariable("p") Long id){
//    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//    	String email = authentication.getName();
//        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet baiviet = bvService.baiVietCoId(id);
        bv.setTk(baiviet.getTk());
        bv.setTgdang(baiviet.getTgdang());
        bv.setId(id);
        LocalDateTime n = LocalDateTime.now();
        bv.setTgsua(n);
        bvService.luuBaiVietMoi(bv);            
        return "redirect:/u/danhSachBV";
    }
    
    @GetMapping("/u/xoaBV/{idbv}")
    public String xoaBaiViet(@PathVariable Long idbv){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        if(bv.getTk().getId() == tk.getId()) {
            bvService.xoaBaiViet(idbv);
        }
        return "redirect:/u/danhSachBV";
        
    }
    
    @GetMapping("/xoaBL/{idbv}/{id}")
    public String xoaBinhLuan(@PathVariable("idbv") Long idbv, @PathVariable("id") Long id){
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        BinhLuanPK blpk = new BinhLuanPK(id, idbv);
        BinhLuan cmt = bvService.binhLuanCoId(blpk);
        if(cmt.getTk().equals(tk) || bv.getTk().equals(tk))   
            bvService.xoaBinhLuan(blpk);
        return "redirect:/xemBV/{idbv}";
        
    }
    
}
