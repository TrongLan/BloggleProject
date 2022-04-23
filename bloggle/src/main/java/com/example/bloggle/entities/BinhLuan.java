package com.example.bloggle.entities;

import com.example.bloggle.compositekeys.BinhLuanPK;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
    @ManyToOne
    @Id
    @JoinColumn(name = "IDBV", nullable = false, columnDefinition = "bigint")
    private BaiViet bv;
    @ManyToOne
    @JoinColumn(name = "IDTK", nullable = false, columnDefinition = "bigint")
    private TaiKhoan tk;

    public BinhLuan() {
    }

    public BinhLuan(Long id, String noidung, LocalDateTime tgdang, BaiViet bv) {
        this.id = id;
        this.noidung = noidung;
        this.tgdang = tgdang;
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
    
    public String getTgdangFormat() {
        return tgdang.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public void setTgdang(LocalDateTime tgdang) {
        this.tgdang = tgdang;
    }

    public BaiViet getBv() {
        return bv;
    }

    public void setBv(BaiViet bv) {
        this.bv = bv;
    }

    public TaiKhoan getTk() {
        return tk;
    }

    public void setTk(TaiKhoan tk) {
        this.tk = tk;
    }
    
    public static class Comparators{
    	public static Comparator<BinhLuan> TGDANG = new Comparator<BinhLuan>() {
                @Override
                public int compare(BinhLuan o1, BinhLuan o2) {
                    return o1.getTgdang().compareTo(o2.getTgdang());
                }
    	};
    }
}
