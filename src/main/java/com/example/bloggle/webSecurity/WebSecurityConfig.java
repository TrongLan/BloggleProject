package com.example.bloggle.webSecurity;

import com.example.bloggle.customTaiKhoanDetails.CustomTaiKhoanDetailsService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private UserDetailsService userDetailsService;
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomTaiKhoanDetailsService();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        
        return authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/u","/admin","/doiMatKhau").authenticated()
            .antMatchers("/admin","/admin/**").hasAnyAuthority("admin")
            .anyRequest().permitAll()
            .and()
            .rememberMe().userDetailsService(userDetailsService())
            .and()
            .formLogin()
                .loginPage("/dangNhap")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/u")
                .permitAll()
            .and()
            .logout().logoutSuccessUrl("/dangNhap").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
    
}
