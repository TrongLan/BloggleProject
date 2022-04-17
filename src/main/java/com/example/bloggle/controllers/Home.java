package com.example.bloggle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {
    @GetMapping("/")
    public String loadHomePage(){
        return "GiaoDienChung/index";
    }
}
