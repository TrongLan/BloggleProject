package com.example.bloggle;

import com.example.bloggle.entities.ChuDe;
import com.example.bloggle.repositories.ChuDeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pc
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChudeRepoTest {
    @Autowired
    private ChuDeRepository repo;
    
    @Test
    public void themChuDe(){
        ChuDe cd = new ChuDe();
        cd.setId(1);
        cd.setTen("CT");
    }
}
