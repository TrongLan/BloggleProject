package com.example.bloggle.customTaiKhoanDetails;

import com.example.bloggle.entities.TaiKhoan;
import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomTaiKhoanDetails implements UserDetails{
    private TaiKhoan tk;

    public CustomTaiKhoanDetails(TaiKhoan tk) {
        this.tk = tk;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(tk.getRole()));
    }

    @Override
    public String getPassword() {
        return tk.getPassword();
    }

    @Override
    public String getUsername() {
        return tk.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
