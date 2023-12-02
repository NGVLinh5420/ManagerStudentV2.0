package com.example.managerstudent.Minh.models;

import java.io.Serializable;

public class ChuyenNganh implements Serializable {

    private String maNganh;
    private String tenChuyenNganh;

    private String maKhoa;

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenChuyenNganh() {
        return tenChuyenNganh;
    }

    public void setTenChuyenNganh(String tenChuyenNganh) {
        this.tenChuyenNganh = tenChuyenNganh;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }


    public ChuyenNganh(String maNganh, String tenChuyenNganh, String maKhoa) {
        this.maNganh = maNganh;
        this.tenChuyenNganh = tenChuyenNganh;
        this.maKhoa=maKhoa;
    }

    public ChuyenNganh() {
    }
}
