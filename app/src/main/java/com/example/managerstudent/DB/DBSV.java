package com.example.managerstudent.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.managerstudent.DTO.DTO_DKMH;
import com.example.managerstudent.DTO.DTO_Diem;
import com.example.managerstudent.DTO.DTO_Khoa;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.DTO.DTO_SV;

import java.util.ArrayList;

public class DBSV extends SQLiteOpenHelper {
    //------------------------------------------------------------------------------------------------
    //--CSDL SQLite
    public DBSV(@Nullable Context context) {
        super(context, "QLSinhVien", null, 1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table SinhVien, Diem
        String quertySinhVien = "CREATE TABLE IF NOT EXISTS tbSINHVIEN(mssv TEXT NOT NULL PRIMARY KEY, ten TEXT, gioitinh TEXT, ngaysinh TEXT, khoa TEXT, namhoc Text)"; //6
        String quertyDiem = "CREATE TABLE IF NOT EXISTS tbDIEM(mamh TEXT NOT NULL, mssv TEXT NOT NULL, diem1 TEXT DEFAULT '0', diem2 TEXT DEFAULT '0')"; //5
        db.execSQL(quertySinhVien);
        db.execSQL(quertyDiem);

        //Table Khoa, Mon
        String sqlKhoa = "CREATE TABLE IF NOT EXISTS tbKHOA(tenkhoa TEXT NOT NULL PRIMARY KEY)";
        db.execSQL(sqlKhoa);

        String sqlMonHoc = "CREATE TABLE IF NOT EXISTS tbMONHOC(mamh TEXT NOT NULL PRIMARY KEY, tenmh TEXT, sotc TEXT, tenkhoa TEXT, hocky TEXT)";
        db.execSQL(sqlMonHoc);

        String sqlDKMH = "CREATE TABLE IF NOT EXISTS tbDKMH(mssv TEXT NOT NULL, maMH TEXT NOT NULL)";
        db.execSQL(sqlDKMH);
    }

    public void FirstData() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<String> arrSQL = new ArrayList<>();

        //--1. Chương Trình Đào Tạo
        arrSQL.add("INSERT INTO tbKHOA VALUES (\"CNTT\")");
        arrSQL.add("INSERT INTO tbKHOA VALUES (\"DO HOA\")");
        arrSQL.add("INSERT INTO tbKHOA VALUES (\"DIEN TU\")");
        arrSQL.add("INSERT INTO tbKHOA VALUES (\"KINH TE\")");
        arrSQL.forEach(sql -> db.execSQL(sql));
        arrSQL.clear();

        //--2. Môn Học
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"1\", \"KTLT1\", \"10\", \"CNTT\", \"HK1\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"2\", \"KTLT2\", \"15\", \"CNTT\", \"HK2\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"3\", \"JAVA\", \"30\", \"CNTT\", \"HK2\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"4\", \"LTDD1\", \"50\", \"CNTT\", \"HK2\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"5\", \"VE\", \"15\", \"DO HOA\", \"HK1\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"6\", \"XAC SUAT\", \"19\", \"KINH TE\", \"HK1\")");
        arrSQL.add("INSERT INTO tbMONHOC VALUES (\"7\", \"MACH DIEN\", \"12\", \"DIEN TU\", \"HK1\")");
        arrSQL.forEach(sql -> db.execSQL(sql));
        arrSQL.clear();

        //--3. Sinh Viên
        arrSQL.add("INSERT INTO tbSINHVIEN VALUES(\"22211\", \"NGUYEN VAN LINH\", \"NAM\", \"05/04/2000\", \"CNTT\", \"2022\");");
        arrSQL.add("INSERT INTO tbSINHVIEN VALUES(\"22212\", \"NGUYEN VAN LONG\", \"NAM\", \"01/12/2000\", \"CNTT\", \"2021\");");
        arrSQL.add("INSERT INTO tbSINHVIEN VALUES(\"22213\", \"NGUYEN THI LINH\", \"NU\", \"01/02/2004\", \"DO HOA\", \"2020\");");
        arrSQL.add("INSERT INTO tbSINHVIEN VALUES(\"22214\", \"NGUYEN VAN MINH\", \"NAM\", \"04/08/2000\", \"DIEN TU\", \"2023\");");
        arrSQL.add("INSERT INTO tbSINHVIEN VALUES(\"22215\", \"NGUYEN HONG HUE\", \"NU\", \"08/02/2001\", \"KINH TE\", \"2020\");");
        arrSQL.forEach(sql -> db.execSQL(sql));
        arrSQL.clear();

        //--4. ĐK Môn Học
        arrSQL.add("INSERT INTO tbDKMH VALUES (\"22212\", \"1\");");
        arrSQL.add("INSERT INTO tbDKMH VALUES (\"22212\", \"2\");");
        arrSQL.add("INSERT INTO tbDKMH VALUES (\"22212\", \"3\");");
        arrSQL.add("INSERT INTO tbDKMH VALUES (\"22212\", \"4\");");
        arrSQL.forEach(sql -> db.execSQL(sql));
        arrSQL.clear();

        //--5. Điểm
        arrSQL.add("INSERT INTO tbDIEM VALUES (\"1\", \"22212\", \"8\", \"9\");");
        arrSQL.add("INSERT INTO tbDIEM VALUES (\"2\", \"22212\", \"8\", \"10\");");
        arrSQL.add("INSERT INTO tbDIEM VALUES (\"3\", \"22212\", \"9\", \"10\");");
        arrSQL.add("INSERT INTO tbDIEM VALUES (\"4\", \"22212\", \"8\", \"10\");");
        arrSQL.forEach(sql -> db.execSQL(sql));
        arrSQL.clear();
    }

    //--------------------------------------------------------------------------------------------------
    //Các Đối Tượng-Danh Sách Giúp Lưu Lại Dữ Liệu Sau Khi Truy Vấn Csdl, Thay Đổi Theo Thời Gian Thực
    //DANH SÁCH: SINH VIÊN
    public static ArrayList<DTO_SV> dsSinhVien = new ArrayList<>();

    public static ArrayList<DTO_SV> getDsSinhVien() {
        return dsSinhVien;
    }

    public static int sizeDSSV() {
        return dsSinhVien.size();
    }

    //lưu lại đối tượng sinh viên để tái sử dụng trên toàn hệ thống
    public static DTO_SV luuSinhVien;

    public static DTO_SV getLuuSinhVien() {
        return luuSinhVien;
    }

    public static void setLuuSinhVien(DTO_SV luuSinhVien) {
        DBSV.luuSinhVien = luuSinhVien;
    }

    public static String luuMSSV;

    public static String getLuuMSSV() {
        return luuMSSV;
    }

    public static void setLuuMSSV(String luuMSSV) {
        DBSV.luuMSSV = luuMSSV;
    }

    //DANH SÁCH: ĐIỂM
    public static ArrayList<DTO_Diem> dsDiem = new ArrayList<>();

    public static ArrayList<DTO_Diem> getDsDiem() {
        return dsDiem;
    }

    public static void setDSDiem(ArrayList<DTO_Diem> dsDiem) {
        DBSV.dsDiem = dsDiem;
    }

    //DANH SÁCH KHOA
    public static ArrayList<DTO_Khoa> dsKhoa = new ArrayList<>();

    public static ArrayList<DTO_Khoa> getDSKhoa() {
        return dsKhoa;
    }

    //Lưu khoa
    public static String luuTenKhoa;

    public static String getLuuTenKhoa() {
        return luuTenKhoa;
    }

    public static void setLuuTenKhoa(String luuTenKhoa) {
        DBSV.luuTenKhoa = luuTenKhoa;
    }

    //DANH SÁCH MÔN HỌC
    public static ArrayList<DTO_HocPhan> dsMonHoc = new ArrayList<>();

    public static ArrayList<DTO_HocPhan> getDSMonHoc() {
        return dsMonHoc;
    }

    public static void setDSMonHoc(ArrayList<DTO_HocPhan> dsDiem) {
        DBSV.dsMonHoc = dsMonHoc;
    }

    public static int sizeDSMonHoc() {
        return dsMonHoc.size();
    }

    //HỌC KỲ
    public static String luuTenHocKy;

    public static String getLuuTenHocKy() {
        return luuTenHocKy;
    }

    public static void setLuuTenHocKy(String luuTenHocKy) {
        DBSV.luuTenHocKy = luuTenHocKy;
    }

    //DANH SÁCH ĐK MÔN HỌC
    public static ArrayList<DTO_DKMH> dsDKMH = new ArrayList<>();

    public static ArrayList<DTO_DKMH> getDsDKMH() {
        return dsDKMH;
    }

    public static void setDsDKMH(ArrayList<DTO_DKMH> dsDKMH) {
        DBSV.dsDKMH = dsDKMH;
    }

    //----------------------------------------------------------------------------------------------------------
    //CÁC TRUY VẤN
    //ĐỌC CSDL
    public void Doc_SinhVien() {
        dsSinhVien.clear();

        SQLiteDatabase db = getReadableDatabase();
        String quertyDoc = "SELECT * FROM tbSINHVIEN";
        Cursor cursor = db.rawQuery(quertyDoc, null);
        if (cursor.moveToFirst()) {
            do {
                DTO_SV sv = new DTO_SV();
                sv.set_MSSV(cursor.getString(0));
                sv.set_Ten(cursor.getString(1));
                sv.set_GioiTinh(cursor.getString(2));
                sv.set_NgaySinh(cursor.getString(3));
                sv.set_Khoa(cursor.getString(4));
                sv.set_NamHoc(cursor.getString(5));
                dsSinhVien.add(sv);
            } while (cursor.moveToNext());
        }
    }

    public void Doc_SVTheoKhoa(String khoa) {
        dsSinhVien.clear();

        SQLiteDatabase db = getReadableDatabase();
        String quertyDoc = "SELECT * FROM tbSINHVIEN WHERE khoa=?";
        Cursor cursor = db.rawQuery(quertyDoc, new String[]{khoa});
        if (cursor.moveToFirst()) {
            do {
                DTO_SV sv = new DTO_SV();
                sv.set_MSSV(cursor.getString(0));
                sv.set_Ten(cursor.getString(1));
                sv.set_GioiTinh(cursor.getString(2));
                sv.set_NgaySinh(cursor.getString(3));
                sv.set_Khoa(cursor.getString(4));
                sv.set_NamHoc(cursor.getString(5));
                dsSinhVien.add(sv);
            } while (cursor.moveToNext());
        }
    }

    //Đọc danh sách sinh viên có tổng điểm trung bình (là trung bình cộng của diem1 và diem2 của tất cả môn học)
    // lớn hơn hoặc bằng 8 ở khoa và trong HK
    public ArrayList<DTO_SV> Doc_SinhVien_Gioi(String tenKhoa, String hocKy) {
        ArrayList<DTO_SV> dsSVGioi = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String quertyDoc = "SELECT tbSINHVIEN.*\n" + "FROM tbSINHVIEN\n" + "JOIN (\n" + "    SELECT mssv, AVG((diem1 + diem2) / 2.0) AS avgDiem\n" + "    FROM tbDIEM\n" + "    JOIN tbMONHOC ON tbDIEM.mamh = tbMONHOC.mamh\n" + "    WHERE tbMONHOC.tenkhoa=? AND tbMONHOC.hocky=? \n" + "    GROUP BY mssv\n" + ") AS tbDiemTB ON tbSINHVIEN.mssv = tbDiemTB.mssv\n" + "WHERE tbDiemTB.avgDiem >= 8;";

        Cursor cursor = db.rawQuery(quertyDoc, new String[]{tenKhoa, hocKy});
        if (cursor.moveToFirst()) {
            do {
                DTO_SV sv = new DTO_SV();
                sv.set_MSSV(cursor.getString(0));
                sv.set_Ten(cursor.getString(1));
                sv.set_GioiTinh(cursor.getString(2));
                sv.set_NgaySinh(cursor.getString(3));
                sv.set_Khoa(cursor.getString(4));
                sv.set_NamHoc(cursor.getString(5));
                dsSVGioi.add(sv);
            } while (cursor.moveToNext());
        }

        return dsSVGioi;
    }

    public ArrayList<DTO_SV> TimKiem_SV(String mssv, String ten) {
        ArrayList<DTO_SV> ds = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String quertyDoc = "SELECT * FROM tbSINHVIEN WHERE mssv LIKE ? OR ten LIKE ?;";
        Cursor cursor = db.rawQuery(quertyDoc, new String[]{mssv, ten});
        if (cursor.moveToFirst()) {
            do {
                DTO_SV sv = new DTO_SV();
                sv.set_MSSV(cursor.getString(0));
                sv.set_Ten(cursor.getString(1));
                sv.set_GioiTinh(cursor.getString(2));
                sv.set_NgaySinh(cursor.getString(3));
                sv.set_Khoa(cursor.getString(4));
                sv.set_NamHoc(cursor.getString(5));
                ds.add(sv);
            } while (cursor.moveToNext());
        }

        return ds;
    }

    public void Doc_Diem() {
        dsDiem.clear();

        SQLiteDatabase db = getReadableDatabase();
        String quertyDiem = "SELECT * FROM tbDIEM";
        Cursor cursor = db.rawQuery(quertyDiem, null);
        if (cursor.moveToFirst()) {
            do {
                DTO_Diem d = new DTO_Diem();
                d.set_mamh(cursor.getString(0));
                d.set_mssv(cursor.getString(1));
                d.set_diem1(cursor.getString(2));
                d.set_diem2(cursor.getString(3));
                dsDiem.add(d);

            } while (cursor.moveToNext());
        }
    }

    public void Doc_DiemTheoMSSV(String mssv) {
        dsDiem.clear();

        SQLiteDatabase db = getReadableDatabase();
        String quertyDiem = "SELECT * FROM tbDIEM WHERE mssv=?";
        Cursor cursor = db.rawQuery(quertyDiem, new String[]{mssv});
        if (cursor.moveToFirst()) {
            do {
                DTO_Diem d = new DTO_Diem();
                d.set_mamh(cursor.getString(0));
                d.set_mssv(cursor.getString(1));
                d.set_diem1(cursor.getString(2));
                d.set_diem2(cursor.getString(3));
                dsDiem.add(d);

            } while (cursor.moveToNext());
        }
    }

    public DTO_Diem Doc_DiemTheoMSSV_MaMH(String mssv, String mamh) {
        DTO_Diem diem = new DTO_Diem();

        SQLiteDatabase db = getReadableDatabase();
        String quertyDiem = "SELECT * FROM tbDIEM WHERE mamh=? AND mssv=?";
        Cursor cursor = db.rawQuery(quertyDiem, new String[]{mamh, mssv});
        if (cursor.moveToFirst()) {
            do {
                diem.set_mamh(cursor.getString(0));
                diem.set_mssv(cursor.getString(1));
                diem.set_diem1(cursor.getString(2));
                diem.set_diem2(cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return diem;
    }

    public void Doc_Khoa() {
        dsKhoa.clear();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tbKHOA ORDER BY tenkhoa ASC;";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                DTO_Khoa khoa = new DTO_Khoa();
                khoa.setTenKhoa(cursor.getString(0));
                dsKhoa.add(khoa);
            } while (cursor.moveToNext());
        }
    }

    public void Doc_MonHoc() {
        dsMonHoc.clear();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from tbMonHoc";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                DTO_HocPhan monHoc = new DTO_HocPhan();
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setSoTC(cursor.getString(2));
                monHoc.setTenKhoa(cursor.getString(3));
                monHoc.setHocKy(cursor.getString(4));

                dsMonHoc.add(monHoc);
            } while (cursor.moveToNext());
        }
    }

    public DTO_HocPhan Doc_MHTheoMaMH(String mamh) {
        DTO_HocPhan monHoc = new DTO_HocPhan();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from tbMonHoc WHERE mamh=?";
        Cursor cursor = db.rawQuery(sql, new String[]{mamh});
        if (cursor.moveToFirst()) {
            do {
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setSoTC(cursor.getString(2));
                monHoc.setTenKhoa(cursor.getString(3));
                monHoc.setHocKy(cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return monHoc;
    }

    public ArrayList<DTO_HocPhan> Doc_MHTheoKhoa_HK(String khoa, String hk) {
        ArrayList<DTO_HocPhan> ds = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * from tbMONHOC WHERE tenkhoa=? AND hocky=?";
        Cursor cursor = db.rawQuery(sql, new String[]{khoa, hk});
        if (cursor.moveToFirst()) {
            do {
                DTO_HocPhan monHoc = new DTO_HocPhan();
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setSoTC(cursor.getString(2));
                monHoc.setTenKhoa(cursor.getString(3));
                monHoc.setHocKy(cursor.getString(4));

                ds.add(monHoc);
            } while (cursor.moveToNext());
        }

        return ds;
    }

    public ArrayList<DTO_HocPhan> Doc_MHTheoMSSV_HK(String mssv, String hk) {
        ArrayList<DTO_HocPhan> ds = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT tbMONHOC.*\n" + "FROM tbMONHOC\n" + "JOIN tbDKMH ON tbMONHOC.mamh = tbDKMH.mamh\n" + "WHERE tbMONHOC.hocky = ? AND tbDKMH.mssv = ?;";

        Cursor cursor = db.rawQuery(sql, new String[]{hk, mssv});
        if (cursor.moveToFirst()) {
            do {
                DTO_HocPhan monHoc = new DTO_HocPhan();
                monHoc.setMaMH(cursor.getString(0));
                monHoc.setTenMH(cursor.getString(1));
                monHoc.setSoTC(cursor.getString(2));
                monHoc.setTenKhoa(cursor.getString(3));
                monHoc.setHocKy(cursor.getString(4));

                ds.add(monHoc);
            } while (cursor.moveToNext());
        }
        return ds;
    }

    public String Tim_MaMH_TheoTenMH(String tenMH) {
        SQLiteDatabase db = getReadableDatabase();

        String sql = "Select * from tbMONHOC WHERE tenmh=?";
        Cursor cursor = db.rawQuery(sql, new String[]{tenMH});
        cursor.moveToFirst();
        String maMH = cursor.getString(0);

        return maMH;
    }

    public void Doc_DKMH(String mssv) {
        dsDKMH.clear();

        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM tbDKMH WHERE mssv=?";
        Cursor cursor = db.rawQuery(sql, new String[]{mssv});

        if (cursor.moveToFirst()) {
            do {
                DTO_DKMH dkmh = new DTO_DKMH();
                dkmh.setMaSV(cursor.getString(0));
                dkmh.setMaMH(cursor.getString(1));

                dsDKMH.add(dkmh);
            } while (cursor.moveToNext());
        }
    }

    //THÊM DỮ LIỆU
    public void Them_SinhVien(DTO_SV sv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyChen = "INSERT INTO tbSINHVIEN VALUES(?,?,?,?,?,?)";
        database.execSQL(quertyChen, new String[]{sv.get_MSSV(), sv.get_Ten(), sv.get_GioiTinh(), sv.get_NgaySinh(), sv.get_Khoa(), sv.get_NamHoc()});
    }

    public void Them_Diem(DTO_Diem d) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyChen = "INSERT INTO tbDIEM VALUES(?,?,?,?)";
        database.execSQL(quertyChen, new String[]{d.get_mamh(), d.get_mssv(), d.get_diem1(), d.get_diem2()});
    }

    public void Them_Khoa(String tenkhoa) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "insert into tbKhoa values(?)";
        database.execSQL(sql, new String[]{tenkhoa});
    }

    public void Them_MonHoc(DTO_HocPhan monHoc) {
        String sql = "insert into tbMonHoc values(?,?,?,?,?)";
        this.getWritableDatabase().execSQL(sql, new String[]{monHoc.getMaMH(), monHoc.getTenMH(), monHoc.getSoTC(), monHoc.getTenKhoa(), monHoc.getHocKy()});
    }

    public void DK_MonHoc(String mssv, String mamh) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "insert into tbDKMH values(?,?)";
        database.execSQL(sql, new String[]{mssv, mamh});
    }

    //XOÁ DỮ LIỆU
    public void Xoa_SinhVien(String mssv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyXoa = "DELETE FROM tbSINHVIEN WHERE mssv=?";
        database.execSQL(quertyXoa, new String[]{mssv});
    }

    public void Xoa_DiemTheoSV_HP(String mssv, String maMH) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyXoa = "DELETE FROM tbDIEM WHERE mssv=? AND mamh=?";
        database.execSQL(quertyXoa, new String[]{mssv, maMH});
    }

    public void Xoa_Diem_XoaSV(String mssv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertyXoa = "DELETE FROM tbDIEM WHERE mssv=?";
        database.execSQL(quertyXoa, new String[]{mssv});
    }


    public void Xoa_Khoa(String tenkhoa) {
        SQLiteDatabase db = getWritableDatabase();
        String sql1 = "delete from tbKhoa where tenkhoa=?";
        db.execSQL(sql1, new String[]{tenkhoa});

        String sql2 = "delete from tbMonHoc where tenkhoa=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql2, new String[]{tenkhoa});
    }

    public void Xoa_MonHoc(String maMH) {
        String sql = "delete from tbMonHoc where mamh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{maMH});
    }

    public void Xoa_DKMH(String mssv, String mamh) {
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM tbDKMH WHERE mssv=? AND mamh=?";
        database.execSQL(sql, new String[]{mssv, mamh});
    }

    //SỬA DỮ LIỆU
    public void Sua_SinhVien(DTO_SV sv) {
        SQLiteDatabase database = getWritableDatabase();
        String quertySua = "UPDATE tbSINHVIEN SET ten=?, gioitinh=?, ngaysinh=?, khoa=?, namhoc=? WHERE mssv=?";
        database.execSQL(quertySua, new String[]{sv.get_Ten(), sv.get_GioiTinh(), sv.get_NgaySinh(), sv.get_Khoa(), sv.get_NamHoc(), sv.get_MSSV()});
    }

    public void Sua_Diem(DTO_Diem d) {
        SQLiteDatabase db = getWritableDatabase();
        String quertyDiem = "UPDATE tbDIEM SET diem1=?, diem2=? WHERE mssv=? AND mamh=?";
        db.execSQL(quertyDiem, new String[]{d.get_diem1(), d.get_diem2(), d.get_mssv(), d.get_mamh()});
    }

    public void Sua_Khoa(String tencu, String tenmoi) {
        SQLiteDatabase db = getWritableDatabase();
        String sql1 = "DELETE FROM tbKHOA WHERE tenkhoa = ?";
        String sql2 = "insert into tbKHOA values(?)";
        db.execSQL(sql1, new String[]{tencu});
        db.execSQL(sql2, new String[]{tenmoi});
    }

    public void Sua_MonHoc(DTO_HocPhan monHoc) {
        String sql = "Update tbMonHoc set tenmh=?, sotc=? where mamh=?";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.execSQL(sql, new String[]{monHoc.getTenMH(), monHoc.getSoTC(), monHoc.getMaMH()});
    }
}
