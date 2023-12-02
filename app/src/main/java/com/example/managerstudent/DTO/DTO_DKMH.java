package com.example.managerstudent.DTO;

public class DTO_DKMH {
    private String maSV, maMH;

    public DTO_DKMH() {
    }

    public DTO_DKMH(String maSV, String maMH) {
        this.maSV = maSV;
        this.maMH = maMH;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }
}
