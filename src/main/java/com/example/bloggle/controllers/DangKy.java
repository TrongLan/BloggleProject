package com.example.bloggle.controllers;

import com.example.bloggle.entities.TaiKhoan;
import com.example.bloggle.services.TaiKhoanService;
import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DangKy {
    private TaiKhoanService tkService;
    public static String err1, err2, err3;

    public DangKy(TaiKhoanService tkService) {
        this.tkService = tkService;
    }
    /*
        - Chức năng đăng ký tài khoản (chỉ đăng ký tài khoản người dùng)
    */
    
    @GetMapping("/dangKy")
    public String moTrangDangKy(Model mod){
        mod.addAttribute("tk", new TaiKhoan());
        mod.addAttribute("err1", err1);
        mod.addAttribute("err2", err2);
        mod.addAttribute("err3", err3);
        return "GiaoDienNguoiDung/trangDangKy";
    }
    @PostMapping("/dangKy")
    public String daDangKy(@ModelAttribute TaiKhoan tk, Model mod){
        // Validate data
        String email = tk.getEmail(), username = tk.getUsername(), password = tk.getPassword();
        err1 = "";
        err2 = "";
        err3 = "";
        if(email==null || email.isBlank())
            err1 = "Hãy nhập một email của bạn.";
        else{
            if(tkService.daTonTaiEmail(tk.getEmail().strip()))
                err1 = "Email này đã được sử dụng.";
        }
        if(username==null || username.isBlank())
            err2 = "Hãy nhập tên tài khoản của bạn.";
        else{
            if(username.length()>20 || username.length()<3)
                err2 = "Username yêu cầu từ 3 đến 20 ký tự.";
            if(tkService.daTonTaiUsername(tk.getUsername().strip()))
                err2 = "Tên tài khoản này đã được sử dụng, thử nhập một tên khác.";
            String specialChars = "(.*[ ,+,-,:,<,>,^,;,`,~,|,?,/,=,@,#,$,%,!].*$)";
            if(username.matches(specialChars) || !username.matches("\\w+"))
                err2 = "Tên tài khoản chỉ có thể chứa ký tự chữ cái Latin, số và dấu gạch dưới.";
            
        }
        if(password==null || password.isBlank())
            err3 = "Không được để trống password.";
        else{
            
            String upperCaseChars = "(.*[A-Z].*)";
            String lowerCaseChars = "(.*[a-z].*)";
            String numbers = "(.*[0-9].*)";
            String specialChars = "(.*[@,#,$,%,!].*$)";
            
            if (password.length() > 20 || password.length() < 8 || !password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(numbers) || !password.matches(specialChars))
                err3 = "Mật khẩu yêu cầu dài từ 8 đến 20 ký tự, bao gồm ít nhất một ký tự in hoa, in thường, số và ký tự đặc biệt.";
        }
            
        
        if(err1.isEmpty() && err2.isEmpty() && err3.isEmpty()){
            BCryptPasswordEncoder encodedPassword = new BCryptPasswordEncoder();
            tk.setPassword(encodedPassword.encode(tk.getPassword().strip()));              // Mã hóa mật khẩu
            tk.setUsername(tk.getUsername().strip());
            tk.setRole("user");
            tkService.capNhatTaiKhoan(tk);
            return "redirect:/";
        }
        else{
            return "redirect:/dangKy";
        }
    }
}
