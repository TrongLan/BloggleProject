package com.example.bloggle.controllers;

import com.example.bloggle.compositekeys.ChamDiemPK;
import com.example.bloggle.compositekeys.ReportPK;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChamDiem;
import com.example.bloggle.entities.Report;
import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.BaiVietService;
import com.example.bloggle.services.TaiKhoanService;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DanhGiaBaiViet {
    private BaiVietService bvService;
    private TaiKhoanService tkService;
    public DanhGiaBaiViet(BaiVietService bvService, TaiKhoanService tkService) {
    	this.bvService = bvService;
    	this.tkService = tkService;
	}
    
    @GetMapping("/xemBV/{idbv}")
    public String xemBaiViet(Model mod, @PathVariable("idbv") Long idbv) {
        int user_type = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        if(tk!=null){
            if(tk.getRole().equals("admin"))
                user_type = 1;
            if(tk.getRole().equals("user")){
                ChamDiemPK cdpk = new ChamDiemPK(tk.getId(), bv.getId());
                if(bvService.daChamDiem(cdpk))
                    mod.addAttribute("chamdiem", bvService.layChamDiem(cdpk));
                else
                    mod.addAttribute("chamdiem", new ChamDiem());
                user_type = 2;
            }    
        }

        List<BinhLuan> dsbl = bvService.cacBinhLuanThuocBaiViet(idbv);
        Collections.sort(dsbl, BinhLuan.Comparators.TGDANG);
        Collections.reverse(dsbl);
    	mod.addAttribute("bv", bv);
    	mod.addAttribute("tk", tk);
    	mod.addAttribute("dsbl", dsbl);
        mod.addAttribute("user_type", user_type);
        mod.addAttribute("binhluan", new BinhLuan());
    	return "GiaoDienChung/xemBaiViet";
    }
    
    @PostMapping("/uploadBL/{idbv}")
    public String dangBinhLuan(BinhLuan bl, @PathVariable("idbv") Long idbv){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        bl.setBv(bv);
        bl.setTk(tk);
        // tạo id cho bình luận của bài viết
        List<BinhLuan> dsbl = bvService.cacBinhLuanThuocBaiViet(idbv);
        long idbl = 0;
        if(dsbl.isEmpty())
            idbl = 1;
        else{
            BinhLuan last_comment = dsbl.get(0);
            idbl = last_comment.getId()+1;
        }
        bl.setId(idbl);
        LocalDateTime n = LocalDateTime.now();
        bl.setTgdang(n);
        bvService.luuBinhLuanMoi(bl);
        return "redirect:/xemBV/{idbv}";
    }
    
    @PostMapping("/chamDiem/{idbv}")
    public String chamDiemBaiViet(ChamDiem score, @PathVariable("idbv") Long idbv){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        score.setBv(bv);
        score.setTk(tk);
        bvService.luuDiem(score);
        return "redirect:/xemBV/{idbv}";
    }
    
    @GetMapping("/u/report/{p}")
    public String moTrangReport(Model mod, @PathVariable("p") Long idbv){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        if(tk!=null){
            ReportPK rpk = new ReportPK(tk.getId(), idbv);
            if(bvService.daReport(rpk)){
                mod.addAttribute("report", bvService.layReport(rpk));
            }
            else
                mod.addAttribute("report", new Report());      
        }
        mod.addAttribute("bv", bv);
        mod.addAttribute("tk", tk);
        return "GiaoDienNguoiDung/trangReport";
    }
    
    @PostMapping("/u/report/{p}")
    public String reportUpload(@PathVariable("p") Long idbv, Report r){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        BaiViet bv = bvService.baiVietCoId(idbv);
        r.setBv(bv);
        r.setTk(tk);
        bvService.luuReport(r);
        return "redirect:/xemBV/{p}";
    }
}
