package com.example.bloggle.entities;

import com.example.bloggle.compositekeys.ChamDiemPK;
import javax.persistence.*;

@Entity
@Table(name="CHAMDIEM")
@IdClass(ChamDiemPK.class)
public class ChamDiem {
    @Column(name = "DIEM", columnDefinition = "int", nullable = false)
    private int diem;
    @ManyToOne
    @JoinColumn(name="IDTK", columnDefinition = "bigint", nullable = false)
    @Id
    private TaiKhoan tk;
    @ManyToOne
    @JoinColumn(name="IDBV", columnDefinition = "bigint", nullable = false)
    @Id
    private BaiViet bv;

    public ChamDiem(int diem, TaiKhoan tk, BaiViet bv) {
        this.diem = diem;
        this.tk = tk;
        this.bv = bv;
    }

    public ChamDiem() {
    }
    
    public int getDiem() {
            return diem;
    }
    public void setDiem(int diem) {
            this.diem = diem;
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
