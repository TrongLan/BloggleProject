package com.example.bloggle.controllers;

import com.example.bloggle.entities.BaiViet;
import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.services.BaiVietService;
import com.example.bloggle.services.ChuDeService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {
    
    @Autowired
    private BaiVietService bvService;
    @Autowired
    private ChuDeService cdService;
    
    @GetMapping("/")
    public String loadHomePage(Model mod, @RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "idcd", required = false) Long idcd){
        List<BaiViet> dsbv = new ArrayList<>();
        List<ChuDe> dscd = cdService.tatCaChuDe();
        Collections.sort(dscd);
        List<ChuDe> temp = dscd.stream().limit(5).collect(Collectors.toList());
        if(keyword!=null && !keyword.isBlank())
            dsbv = bvService.timKiemBaiViet(keyword);
        else
            dsbv = bvService.cacBaiVietGanDay();
        if(idcd!=null&&idcd>0){
            dsbv = bvService.cacBaiVietCoChuDe(idcd);
            mod.addAttribute("cd", cdService.chuDeCoId(idcd));
        }
        mod.addAttribute("keyword", keyword);
        mod.addAttribute("dsbv", dsbv);
        mod.addAttribute("dscd", temp);
        return "GiaoDienChung/index";
    }
}
