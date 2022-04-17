package com.example.bloggle.entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "TAIKHOAN")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTK")
    private Long id;
    @Column(name = "EMAIL", length = 100, unique = true, nullable = false)
    private String email;
    @Column(name = "TENTK", length = 20, unique = true, nullable = false)
    private String username;
    @Column(name = "QUYEN", nullable = false, length = 5)
    private String role;
    @Column(name = "MK", nullable = false, length = 1000)
    private String password;
    @OneToMany(mappedBy = "tk", cascade = CascadeType.REMOVE)
    private List<BinhLuan> dsbl;
    @OneToMany(mappedBy = "tk", cascade = CascadeType.REMOVE)
    private List<BaiViet> dsbv;
    @OneToMany(mappedBy = "tk")
    private List<ChamDiem> dscd;
    @OneToMany(mappedBy = "tk")
    private List<Report> ds_report;
    public TaiKhoan() {
    }

    public TaiKhoan(Long id, String email, String username, String role, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
        this.password = password;
        this.dsbv = new ArrayList<>();
        this.dsbl = new ArrayList<>();
        this.dscd = new ArrayList<>();
        this.ds_report = new ArrayList<>();
    }

    public List<Report> getDs_report() {
        return ds_report;
    }

    public void setDs_report(List<Report> ds_report) {
        this.ds_report = ds_report;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<BinhLuan> getDsbl() {
        return dsbl;
    }

    public void setDsbl(List<BinhLuan> dsbl) {
        this.dsbl = dsbl;
    }

    public List<BaiViet> getDsbv() {
        return dsbv;
    }

    public void setDsbv(List<BaiViet> dsbv) {
        this.dsbv = dsbv;
    }

    public List<ChamDiem> getDscd() {
        return dscd;
    }

    public void setDscd(List<ChamDiem> dscd) {
        this.dscd = dscd;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" + "id=" + id + ", email=" + email + ", username=" + username + ", role=" + role + ", password=" + password + '}';
    }
    
}
