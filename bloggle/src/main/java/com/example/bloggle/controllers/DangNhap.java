package com.example.bloggle.controllers;
import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.TaiKhoanService;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DangNhap {
    private TaiKhoanService tkService;
    public static String err;

    public DangNhap(TaiKhoanService tkService) {
        this.tkService = tkService;
    }
    
    @GetMapping("/dangNhap")
    public String trangDangNhap(Model mod, HttpServletRequest request, @RequestParam(value = "error", required = false) String error){
        err = "";
        if(error!=null)
            err = "Sai thông tin đăng nhập. Vui lòng kiểm tra lại email/tên tài khoản và mật khẩu.";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            mod.addAttribute("err", err);
            return "GiaoDienChung/trangDangNhap";
        }
        return "redirect:/u";
    }
    
    @GetMapping("/u")
    public String sauDangNhap(Principal p, Model mod, HttpServletRequest request){
        if(p != null){
            TaiKhoan tk = tkService.taiKhoanCoEmail(p.getName());
            mod.addAttribute("tk", tk);
            HttpSession ss = request.getSession();
            ss.setAttribute("current_acc", tk);
            if(tk.getRole().equals("user"))
                return "GiaoDienNguoiDung/trangNguoiDung";
            else
                return "redirect:/admin";
        }
        return "redirect:/";
    }
}
