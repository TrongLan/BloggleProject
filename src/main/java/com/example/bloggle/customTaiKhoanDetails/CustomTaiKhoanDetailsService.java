package com.example.bloggle.customTaiKhoanDetails;

import com.example.bloggle.customTaiKhoanDetails.CustomTaiKhoanDetails;
import com.example.bloggle.entities.TaiKhoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.bloggle.repositories.TaiKhoanRepository;

public class CustomTaiKhoanDetailsService implements UserDetailsService{

    @Autowired
    private TaiKhoanRepository tkRepo;

    public CustomTaiKhoanDetailsService() {
    }
    
    
    @Override
    public UserDetails loadUserByUsername(String data) throws UsernameNotFoundException {
        TaiKhoan tk = tkRepo.findTaiKhoanByEmailOrUsername(data);
        if(tk == null){
            throw new UsernameNotFoundException("Not found");
        }
        return new CustomTaiKhoanDetails(tk);
    }
    
}
