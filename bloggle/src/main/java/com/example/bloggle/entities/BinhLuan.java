package com.example.bloggle.entities;

import com.example.bloggle.compositekeys.BinhLuanPK;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="BINHLUAN")
@IdClass(BinhLuanPK.class)
public class BinhLuan {
    @Id
    private Long id;
    @Column(name = "NOIDUNG", columnDefinition = "nvarchar(1000)", nullable = false)
    private String noidung;
    @Column(name = "TGDANG", columnDefinition = "smalldatetime", nullable = false)
    private LocalDateTime tgdang;
    @Column(name = "TGSUA", columnDefinition = "smalldatetime", nullable = false)
    private LocalDateTime tgsua;
    @ManyToOne
    @Id
    @JoinColumn(name = "IDBV", nullable = false, columnDefinition = "bigint")
    private BaiViet bv;
    @ManyToOne
    @JoinColumn(name = "IDTK", nullable = false, columnDefinition = "bigint")
    private TaiKhoan tk;

    public BinhLuan() {
    }

    public BinhLuan(Long id, String noidung, LocalDateTime tgdang, LocalDateTime tgsua, BaiViet bv) {
        this.id = id;
        this.noidung = noidung;
        this.tgdang = tgdang;
        this.tgsua = tgsua;
        this.bv = bv;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public LocalDateTime getTgdang() {
        return tgdang;
    }

    public void setTgdang(LocalDateTime tgdang) {
        this.tgdang = tgdang;
    }

    public LocalDateTime getTgsua() {
        return tgsua;
    }

    public void setTgsua(LocalDateTime tgsua) {
        this.tgsua = tgsua;
    }

    public BaiViet getBv() {
        return bv;
    }

    public void setBv(BaiViet bv) {
        this.bv = bv;
    }
    
}
