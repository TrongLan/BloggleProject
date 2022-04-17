<<<<<<< HEAD
 package com.example.bloggle.entities;
=======
package com.example.bloggle.entities;
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "BAIVIET")
public class BaiViet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TIEUDE", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String tieude;
    @Column(name = "NOIDUNG", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String noidung;
    @Column(name = "TOMTAT", columnDefinition = "nvarchar(MAX)", nullable = false)
    private String tomtat;
<<<<<<< HEAD
    @Column(name = "TGDANG", columnDefinition = "smalldatetime", nullable = true)
    private LocalDateTime tgdang;
    @Column(name = "TGSUA", columnDefinition = "smalldatetime", nullable = true)
=======
    @Column(name = "TGDANG", columnDefinition = "smalldatetime", nullable = false)
    private LocalDateTime tgdang;
    @Column(name = "TGSUA", columnDefinition = "smalldatetime", nullable = false)
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
    private LocalDateTime tgsua;
    @OneToMany(mappedBy = "bv", cascade = CascadeType.REMOVE)
    private List<BinhLuan> dsbl;
    @OneToMany(mappedBy = "bv")
    private List<ChamDiem> dscd;
    @OneToMany(mappedBy = "bv")
    private List<Report> ds_report;
    @ManyToOne
<<<<<<< HEAD
    @JoinColumn(name = "IDTK", nullable = true, columnDefinition = "bigint")
    private TaiKhoan tk;
    @ManyToOne
    @JoinColumn(name = "IDCD", nullable = true, columnDefinition = "bigint")
=======
    @JoinColumn(name = "IDTK", nullable = false, columnDefinition = "bigint")
    private TaiKhoan tk;
    @ManyToOne
    @JoinColumn(name = "IDCD", nullable = false, columnDefinition = "bigint")
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
    private ChuDe cd;
    public BaiViet() {
    }

    public BaiViet(Long id, String tieude, String noidung, String tomtat, LocalDateTime tgdang, LocalDateTime tgsua) {
        this.id = id;
        this.tieude = tieude;
        this.noidung = noidung;
        this.tomtat = tomtat;
        this.tgdang = tgdang;
        this.tgsua = tgsua;
        this.dsbl = new ArrayList<>();
        this.dscd = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getTomtat() {
        return tomtat;
    }

    public void setTomtat(String tomtat) {
        this.tomtat = tomtat;
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

    public List<BinhLuan> getDsbl() {
        return dsbl;
    }

    public void setDsbl(List<BinhLuan> dsbl) {
        this.dsbl = dsbl;
    }

    public List<ChamDiem> getDscd() {
        return dscd;
    }

    public void setDscd(List<ChamDiem> dscd) {
        this.dscd = dscd;
    }

    public TaiKhoan getTk() {
        return tk;
    }

    public void setTk(TaiKhoan tk) {
        this.tk = tk;
    }

    public ChuDe getCd() {
        return cd;
    }

    public void setCd(ChuDe cd) {
        this.cd = cd;
    }

    public List<Report> getDs_report() {
        return ds_report;
    }

    public void setDs_report(List<Report> ds_report) {
        this.ds_report = ds_report;
    }
<<<<<<< HEAD

    @Override
    public String toString() {
        return "BaiViet{" + "id=" + id + ", tieude=" + 
                tieude + ", noidung=" + noidung + ", tomtat=" + 
                tomtat + ", tgdang=" + tgdang + ", tgsua=" + 
                tgsua + ", dsbl=" + dsbl + ", dscd=" + 
                dscd + ", ds_report=" + ds_report + ", tk=" + tk + ", cd=" + cd + '}';
    }
=======
>>>>>>> 25d2a53234d69928eac2f3ef1afb0de349a616ad
    
}
