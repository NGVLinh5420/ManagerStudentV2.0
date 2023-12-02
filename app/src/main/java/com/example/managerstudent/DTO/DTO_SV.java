package com.example.managerstudent.DTO;

import java.io.Serializable;

public class DTO_SV implements Serializable {


    String _MSSV;
    String _Ten;
    String _GioiTinh;
    String _NgaySinh;
    String _NamHoc;
    String _Khoa;

    public DTO_SV(String _MSSV, String _Ten, String _GioiTinh, String _NgaySinh, String _NamHoc, String _Khoa) {
        this._MSSV = _MSSV;
        this._Ten = _Ten;
        this._GioiTinh = _GioiTinh;
        this._NgaySinh = _NgaySinh;
        this._NamHoc = _NamHoc;
        this._Khoa = _Khoa;
    }

    public DTO_SV() {
    }

    public String get_MSSV() {
        return _MSSV;
    }

    public void set_MSSV(String _MSSV) {
        this._MSSV = _MSSV;
    }

    public String get_Ten() {
        return _Ten;
    }

    public void set_Ten(String _Ten) {
        this._Ten = _Ten;
    }

    public String get_GioiTinh() {
        return _GioiTinh;
    }

    public void set_GioiTinh(String _GioiTinh) {
        this._GioiTinh = _GioiTinh;
    }

    public String get_NgaySinh() {
        return _NgaySinh;
    }

    public void set_NgaySinh(String _NgaySinh) {
        this._NgaySinh = _NgaySinh;
    }

    public String get_NamHoc() {
        return _NamHoc;
    }

    public void set_NamHoc(String _NamHoc) {
        this._NamHoc = _NamHoc;
    }

    public String get_Khoa() {
        return _Khoa;
    }

    public void set_Khoa(String _Khoa) {
        this._Khoa = _Khoa;
    }

    //METHODS
    @Override
    public String toString() {
        return "[ " + get_MSSV() + " ]" + "  " + get_Ten();
    }


}
