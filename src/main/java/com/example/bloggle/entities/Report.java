package com.example.bloggle.entities;

import com.example.bloggle.compositekeys.ReportPK;
import javax.persistence.*;

@Entity
@Table(name="REPORT")
@IdClass(ReportPK.class)
public class Report {
    @Column(name="LYDO", columnDefinition = "nvarchar(255)", nullable = false)
    private String lydo;
    @ManyToOne
    @JoinColumn(name = "IDTK", nullable = false)
    @Id
    private TaiKhoan tk;
    @ManyToOne
    @JoinColumn(name = "IDBV", nullable = false)
    @Id
    private BaiViet bv;

    public Report() {
    }

    public Report(String lydo, TaiKhoan tk, BaiViet bv) {
        this.lydo = lydo;
        this.tk = tk;
        this.bv = bv;
    }
    
    
    public String getLydo() {
        return lydo;
    }

    public void setLydo(String lydo) {
        this.lydo = lydo;
    }

    public TaiKhoan getTk() {
        return tk;
    }

    public void setTk(TaiKhoan tk) {
        this.tk = tk;
    }

    public BaiViet getBv() {
        return bv;
    }

    public void setBv(BaiViet bv) {
        this.bv = bv;
    }
    
}
