package com.example.bloggle.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "CHUDE")
public class ChuDe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TENCD", nullable = false, columnDefinition = "nvarchar(30)")
    private String ten;
    
    @OneToMany(mappedBy = "cd")
    private List<BaiViet> dsbv;

    public ChuDe() {
    }

    public ChuDe(Long id, String ten) {
        this.id = id;
        this.ten = ten;
        this.dsbv = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public List<BaiViet> getDsbv() {
        return dsbv;
    }

    public void setDsbv(List<BaiViet> dsbv) {
        this.dsbv = dsbv;
    }
    
    
}
