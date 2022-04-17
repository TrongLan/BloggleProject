package com.example.bloggle.compositekeys;

import java.io.Serializable;

public class ReportPK implements Serializable{
    private Long tk, bv;

    public ReportPK() {
    }

    public ReportPK(Long tk, Long bv) {
        this.tk = tk;
        this.bv = bv;
    }
    
}
