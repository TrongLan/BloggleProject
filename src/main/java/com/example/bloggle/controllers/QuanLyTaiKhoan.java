package com.example.bloggle.controllers;

import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.TaiKhoanService;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        return "GiaoDienChung/doiMatKhau";
    }
    
    @PostMapping("/doiMatKhau")
    public String doiMatKhau(HttpServletRequest request){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        HttpSession ss = request.getSession();
        TaiKhoan tk = (TaiKhoan) ss.getAttribute("current_acc");
        String oldPassword = request.getParameter("old");
        String newPassword = request.getParameter("new");
        String rewritedNewPassword = request.getParameter("rewrite_new");
        
        if(tk==null)
            return "redirect:/dangNhap";
        
        // Validate input
        if(oldPassword.isBlank() || oldPassword==null)
            pw_err1 = "Yêu cầu nhập lại mật khẩu hiện tại.";
        else
            if(!passwordEncoder.matches(oldPassword, tk.getPassword()))
                pw_err1 = "Mật khẩu hiện tại không đúng.";
            else
                pw_err1 = "";
        
        pw_err2 = invalidPasswordError(newPassword);
        
        if(rewritedNewPassword.isBlank() || rewritedNewPassword==null)
            pw_err3 = "Yêu cầu xác nhận lại mật khẩu mới.";
        else
            if(!newPassword.equals(rewritedNewPassword))
                pw_err3 = "Mật khẩu xác nhận không khớp.";
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
            return "Nhập mật khẩu mới của bạn.";
        else{
            
            String upperCaseChars = "(.*[A-Z].*)";
            String lowerCaseChars = "(.*[a-z].*)";
            String numbers = "(.*[0-9].*)";
            String specialChars = "(.*[@,#,$,%,!].*$)";
            
            if (password.length() > 20 || password.length() < 8 || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(numbers) || !password.matches(specialChars))
                return "Mật khẩu yêu cầu dài từ 8 đến 20 ký tự, bao gồm ít nhất một ký tự in hoa, in thường, số và ký tự đặc biệt.";
        }
        return "";
    }
}
