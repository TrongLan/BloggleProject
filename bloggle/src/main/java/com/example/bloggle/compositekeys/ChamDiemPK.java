package com.example.bloggle.compositekeys;

import java.io.Serializable;

public class ChamDiemPK implements Serializable{
    private long tk, bv;

    public ChamDiemPK(long tk, long bv) {
        this.tk = tk;
        this.bv = bv;
    }

    public ChamDiemPK() {
    }
}
