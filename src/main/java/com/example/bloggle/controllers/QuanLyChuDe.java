
package com.example.bloggle.controllers;

import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.services.ChuDeService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuanLyChuDe {
    
    private ChuDeService cdService;

    public QuanLyChuDe(ChuDeService cdService) {
        this.cdService = cdService;
    }
    
    @GetMapping("/admin/quanLyCD")
    public String trangQuanLyChuDe(Model mod, @RequestParam(value = "idcd", required = false) Long id, HttpServletRequest request){
        mod.addAttribute("dscd",cdService.tatCaChuDe());
        ChuDe cd = new ChuDe();
        ChuDe chudemoi = new ChuDe();
        if(id!=null && id>0){
            cd = cdService.chuDeCoId(id);
            HttpSession ss = request.getSession();
            ss.setAttribute("idcd", cd.getId());
        }
            
        else
            cd = null;
        mod.addAttribute("chude",cd); 
        mod.addAttribute("chudemoi",chudemoi);
        return "GiaoDienAdmin/trangQuanLyChuDe";
    }
    @PostMapping("/admin/quanLyCD")
    public String themChuDe(@ModelAttribute("chudemoi") ChuDe chudemoi){
        cdService.capNhatChuDe(chudemoi);
        return "redirect:/admin/quanLyCD";
    }
    
    @PostMapping("/admin/capNhatCD")
    public String doiTenChuDe(HttpServletRequest request){
        HttpSession ss = request.getSession();
        Long id = (Long) ss.getAttribute("idcd");
        System.out.println(id);
        String ten = request.getParameter("ten");
        ChuDe cd = new ChuDe(id, ten.strip());
        cdService.capNhatChuDe(cd);
        return "redirect:/admin/quanLyCD";
    }
}
