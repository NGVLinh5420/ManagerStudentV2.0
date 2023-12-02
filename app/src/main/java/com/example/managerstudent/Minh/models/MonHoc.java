package com.example.managerstudent.Minh.models;

import java.io.Serializable;

public class MonHoc implements Serializable {

    private  String maMonHoc;

    private  String tenMonHoc;

    private String soTinChi;

    private  String maHK;

    public String getMaMonHoc() {
        return maMonHoc;
    }

    public void setMaMonHoc(String maMonHoc) {
        this.maMonHoc = maMonHoc;
    }

    public String getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(String soTinChi) {
        this.soTinChi = soTinChi;
    }

    public String getTenMonHoc() {
        return tenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        this.tenMonHoc = tenMonHoc;
    }

    public String getMaHK() {
        return maHK;
    }

    public void setMaHK(String maHK) {
        this.maHK = maHK;
    }

    public MonHoc(String maMonHoc, String tenMonHoc,String soTinChi,String maHK) {
        this.maMonHoc = maMonHoc;
        this.soTinChi = soTinChi;
        this.tenMonHoc = tenMonHoc;
        this.maHK=maHK;
    }
    public MonHoc() {
    }

    @Override
    public String toString() {
        return "MonHoc{" +
                "maMonHoc='" + maMonHoc + '\'' +
                ", soTinChi=" + soTinChi +
                ", tenMonHoc='" + tenMonHoc + '\'' +
                '}';
    }
}
