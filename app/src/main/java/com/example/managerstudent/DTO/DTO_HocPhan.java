package com.example.managerstudent.DTO;

import java.io.Serializable;

public class DTO_HocPhan implements Serializable {
    private String maMH, tenMH, soTC, tenKhoa, hocKy;

    public DTO_HocPhan() {
    }

    public DTO_HocPhan(String maMH, String tenMH, String soTC, String tenKhoa, String hocKy) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTC = soTC;
        this.tenKhoa = tenKhoa;
        this.hocKy = hocKy;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getSoTC() {
        return soTC;
    }

    public void setSoTC(String soTC) {
        this.soTC = soTC;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getHocKy() {
        return hocKy;
    }

    public void setHocKy(String hocKy) {
        this.hocKy = hocKy;
    }

    @Override
    public String toString() {
        return "Mã HP: " + getMaMH() + " | Tên HP: " + getTenMH() + " (Số TC: " + getSoTC() + ")";
    }
}
