package com.example.bloggle.controllers;

import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.TaiKhoanService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuanLyTaiKhoan {
    private TaiKhoanService tkService;
    public static String pw_err1 = "", pw_err2 = "", pw_err3 = "";
    
    public QuanLyTaiKhoan(TaiKhoanService tkService) {
        this.tkService = tkService;
    }
    
    @GetMapping("/admin")
    public String moTrangAdmin(Model mod){
        TaiKhoan tk = tkService.taiKhoanAdmin();
        mod.addAttribute("tk", tk);
        return "GiaoDienAdmin/trangAdmin";
    }
    @GetMapping("/admin/quanLyTK")
    public String trangQuanLyTaiKhoan(Model mod, @RequestParam(value = "keyword", required = false) String keyword){
        List<TaiKhoan> dstk = new ArrayList<>();
        if(keyword==null||keyword.isBlank())
            dstk = tkService.danhSachTatCaTaiKhoan();
        else
            dstk = tkService.timKiemTaiKhoan(keyword);
        mod.addAttribute("dstk", dstk);
        return "GiaoDienAdmin/trangQuanLyTaiKhoan";
    }
    
    @GetMapping("/admin/xoaTK/{id}")
    public String xoaTaiKhoan(@PathVariable Long id){
        tkService.xoaTaiKhoan(id);
        return "redirect:/admin/quanLyTK";
    }
    
    @GetMapping("/doiMatKhau")
    public String moTrangDoiMatKhau(Model mod){
        mod.addAttribute("e1",pw_err1);
        mod.addAttribute("e2",pw_err2);
        mod.addAttribute("e3",pw_err3);
        pw_err1 = "";pw_err2 = "";pw_err3 = "";
        return "GiaoDienChung/doiMatKhau";
    }
    
    @PostMapping("/doiMatKhau")
    public String doiMatKhau(HttpServletRequest request){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String email = authentication.getName();
        TaiKhoan tk = tkService.taiKhoanCoEmail(email);
        String oldPassword = request.getParameter("old");
        String newPassword = request.getParameter("new");
        String rewritedNewPassword = request.getParameter("rewrite_new");
        
        if(tk==null)
            return "redirect:/dangNhap";
        
        // Validate input
        if(oldPassword.isBlank() || oldPassword==null)
            pw_err1 = "Y??u c???u nh???p l???i m???t kh???u hi???n t???i.";
        else
            if(!passwordEncoder.matches(oldPassword, tk.getPassword()))
                pw_err1 = "M???t kh???u hi???n t???i kh??ng ????ng.";
            else
                pw_err1 = "";
        
        pw_err2 = invalidPasswordError(newPassword);
        
        if(rewritedNewPassword.isBlank() || rewritedNewPassword==null)
            pw_err3 = "Y??u c???u x??c nh???n l???i m???t kh???u m???i.";
        else
            if(!newPassword.equals(rewritedNewPassword))
                pw_err3 = "M???t kh???u x??c nh???n kh??ng kh???p.";
            else
                pw_err3 = "";
        
        if(pw_err1.isEmpty() && pw_err2.isEmpty() && pw_err3.isEmpty()){
            String newPasswordEncoded = passwordEncoder.encode(newPassword);
            tk.setPassword(newPasswordEncoded);
            tkService.capNhatTaiKhoan(tk);
            return "redirect:/u";
        }
            
        else
            return "redirect:/doiMatKhau";
    }
    
    public static String invalidPasswordError(String password){
        if(password==null || password.isBlank())
            return "Nh???p m???t kh???u m???i c???a b???n.";
        else{
            
            String upperCaseChars = "(.*[A-Z].*)";
            String lowerCaseChars = "(.*[a-z].*)";
            String numbers = "(.*[0-9].*)";
            String specialChars = "(.*[@,#,$,%,!].*$)";
            
            if (password.length() > 20 || password.length() < 8 || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(numbers) || !password.matches(specialChars))
                return "M???t kh???u y??u c???u d??i t??? 8 ?????n 20 k?? t???, bao g???m ??t nh???t m???t k?? t??? in hoa, in th?????ng, s??? v?? k?? t??? ?????c bi???t.";
        }
        return "";
    }
}
