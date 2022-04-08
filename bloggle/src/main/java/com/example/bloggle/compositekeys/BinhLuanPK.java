package com.example.bloggle.compositekeys;

import java.io.Serializable;

public class BinhLuanPK implements Serializable{
    private Long id, bv;

    public BinhLuanPK() {
    }

    public BinhLuanPK(Long id, Long idbv) {
        this.id = id;
        this.bv = idbv;
    }
    
}
