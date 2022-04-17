package com.example.bloggle.controllers;

<<<<<<< HEAD
import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.BinhLuan;
import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.services.BaiVietService;
import com.example.bloggle.services.ChuDeService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuanLyBaiViet {

    @Autowired
    private BaiVietService bvService;
    
    @Autowired
    private ChuDeService cdService;

    public QuanLyBaiViet() {
    }

    public QuanLyBaiViet(BaiVietService bvService) {
        this.bvService = bvService;
    }
    
    @GetMapping("/user/quanLyBV")
    public String trangQuanLyBaiViet(Model model) {
        List<BaiViet> listBaiViet = bvService.listAll();
        model.addAttribute("listBaiViet", listBaiViet);
        return "GiaoDienNguoiDung/trangQuanLyBaiViet";
    }

    @GetMapping("/user/quanLyBV/newPost")
    public String showNewPost(Model model, @RequestParam(value = "idbv", required = false) Long id,
            HttpServletRequest request,  @RequestParam(value = "idcd", required = false) Long idcd,
            @RequestParam(value = "idtk", required = false) Long idtk) {
        
        model.addAttribute("dscd",cdService.tatCaChuDe());
        ChuDe cd = new ChuDe();
        if(id!=null && id>0){
            cd = cdService.chuDeCoId(idcd);
            HttpSession ss = request.getSession();
            ss.setAttribute("idcd", cd.getId());
        }
        else
            cd = null;
        
        model.addAttribute("chude", cd);
        model.addAttribute("baivietmoi", new BaiViet());
        return "GiaoDienNguoiDung/trangDangBai";
    }

    @PostMapping("/user/quanLyBV/savePost")
    public String savePost(@ModelAttribute("baivietmoi") BaiViet baiviet, HttpServletRequest
             request) {
        
        bvService.save(baiviet);
        return "redirect:/user/quanLyBV";
    }
    
    @GetMapping("/user/quanLyBV/edit/{id}")
    public String trangSua(@PathVariable("id") Long id, Model model){
        try{
            BaiViet baiviet = bvService.get(id);
            model.addAttribute("baiviet", baiviet);
            return "GiaoDienNguoiDung/trangSuaBai"; 
        } catch (UsernameNotFoundException e){
            e.printStackTrace();
            return "redirect:user/quanLyBV";
        }
    }
    
    @GetMapping("/user/quanLyBV/delete/{id}")
    public String xoaBaiViet(@PathVariable("id") Long id){
        try{
            bvService.xoa(id);
        } catch (UsernameNotFoundException e){
            e.printStackTrace();
        }
        return "redirect:/user/quanLyBV";
    }
    
    @GetMapping("/xemBV")
    public String xemBV(Model model){
        List<BaiViet> listBaiViet = bvService.listAll();
        model.addAttribute("listBaiViet", listBaiViet);
        return "GiaoDienNguoiDung/xemBaiViet";
    }
    
    @GetMapping("/BV/{id}")
    public String xemChiTiet(Model model, @PathVariable("id") Long id, BaiViet baiviet){
        baiviet = bvService.get(id);
        String tieude = baiviet.getTieude();
        String tomtat = baiviet.getTomtat();
        String noidung = baiviet.getNoidung();
        model.addAttribute("tieude", tieude);
        model.addAttribute("tomtat", tomtat);
        model.addAttribute("noidung", noidung);
        return "GiaoDienChung/XemChiTietBV";
    }
=======
public class QuanLyBaiViet {
    
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
}
