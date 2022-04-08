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
}
