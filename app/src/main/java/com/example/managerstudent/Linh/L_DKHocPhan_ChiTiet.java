package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Diem;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_DKHocPhan_ChiTiet extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView tvMssvTen, tvKhoa, tvSLHPDK, tvSLHP;
    private Spinner spHocKy, spMonHoc;
    private Button btnBack, btnDangKy, btnHuyHP;
    private ListView lvHocPhan;
    private ArrayAdapter<DTO_HocPhan> adapterLV;

    public static String luuMaMonHoc;
    int vtriHP = -1;
    String maSV = DBSV.getLuuSinhVien().get_MSSV();
    static String tenMH;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_dkhp_chi_tiet);

        setControl();
        setEvent();
    }

    private void setControl() {
        tvMssvTen = findViewById(R.id.dkm_tvMssvTen);
        tvKhoa = findViewById(R.id.dkm_tvKhoa);
        tvKhoa.setText(DBSV.getLuuSinhVien().get_Khoa());
        tvSLHPDK = findViewById(R.id.dkm_tvSLMonDK);
        tvSLHP = findViewById(R.id.dkm_tvSLMon);

        btnBack = findViewById(R.id.dkm_btnBack);
        btnDangKy = findViewById(R.id.dkm_btnDangKy);
        btnHuyHP = findViewById(R.id.dkm_btnRemove);

        lvHocPhan = findViewById(R.id.dkm_lvMonDK);

        spHocKy = findViewById(R.id.dkm_spHocKy);
        spMonHoc = findViewById(R.id.dkm_spHocPhan);

        //Spinner Học Kỳ
        ArrayAdapter<CharSequence> adapterSPHocKy = ArrayAdapter.createFromResource(this, R.array.HocKy, android.R.layout.select_dialog_item);
        adapterSPHocKy.setDropDownViewResource(android.R.layout.select_dialog_item);
        spHocKy.setAdapter(adapterSPHocKy);
        spHocKy.setOnItemSelectedListener(this);
    }


    private void setEvent() {
        //--1.
        tvMssvTen.setText(maSV + "\n" + DBSV.getLuuSinhVien().get_Ten());
        tvKhoa.setText(DBSV.getLuuSinhVien().get_Khoa());

        adapterLV = new ArrayAdapter<>(L_DKHocPhan_ChiTiet.this, android.R.layout.simple_list_item_1, LayDSMonHocDaDK());

        //--2.Buttons
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spMonHoc.getSelectedItemPosition() != -1) {
                    //Tìm mã MH bằng tên MH
                    tenMH = spMonHoc.getSelectedItem().toString();
                    String maMH = dbSV.Tim_MaMH_TheoTenMH(tenMH);

                    Boolean flag = true;
                    for (DTO_HocPhan hp : LayDSMonHocDaDK()) {
                        if (hp.getMaMH().equals(maMH)) {
                            Toast.makeText(L_DKHocPhan_ChiTiet.this, "SV Đã ĐK HP này.", Toast.LENGTH_SHORT).show();
                            flag = false;
                        }
                    }

                    // ĐK
                    if (flag) {
                        dbSV.DK_MonHoc(maSV, maMH);
                        dbSV.Them_Diem(new DTO_Diem(maMH, maSV, "0", "0"));
                        dbSV.Doc_DKMH(maSV);

                        //** Làm mới listview theo thời gian thực
                        adapterLV.clear();
                        adapterLV.addAll(LayDSMonHocDaDK());
                        adapterLV.notifyDataSetChanged();
                        lvHocPhan.setAdapter(adapterLV);
                        tvSLHPDK.setText("" + lvHocPhan.getCount());
                        //**

                        Toast.makeText(L_DKHocPhan_ChiTiet.this, "ĐK Thành Công.", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(L_DKHocPhan_ChiTiet.this, "ĐK Lỗi.", Toast.LENGTH_SHORT).show();
            }
        });

        lvHocPhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                vtriHP = position;
            }
        });

        btnHuyHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vtriHP > -1) {
                    String maMH = DBSV.dsDKMH.get(vtriHP).getMaMH();
                    String maSV = DBSV.dsDKMH.get(vtriHP).getMaSV();

                    dbSV.Xoa_DKMH(maSV, maMH);
                    dbSV.Xoa_DiemTheoSV_HP(maSV, maMH);
                    dbSV.Doc_DKMH(maSV);

                    //** Làm mới listview theo thời gian thực
                    adapterLV.clear();
                    adapterLV.addAll(LayDSMonHocDaDK());
                    adapterLV.notifyDataSetChanged();
                    lvHocPhan.setAdapter(adapterLV);
                    tvSLHPDK.setText("" + lvHocPhan.getCount());
                    //**

                    vtriHP = -1;
                    Toast.makeText(L_DKHocPhan_ChiTiet.this, "Đã Huỷ Mã HP: " + maMH, Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(L_DKHocPhan_ChiTiet.this, "Chưa Chọn Học Phần.", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_DKHocPhan_ChiTiet.super.onBackPressed();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String hocky = parent.getItemAtPosition(position).toString();

        dbSV.Doc_DKMH(DBSV.getLuuSinhVien().get_MSSV());
        lvHocPhan.setAdapter(adapterLV);
        tvSLHPDK.setText("" + lvHocPhan.getCount());

        //spinner Học Phần
        ArrayAdapter<String> adapterSPMonHoc = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_item, LayDSMonHoc(hocky));
        spMonHoc.setAdapter(adapterSPMonHoc);
    }

    public ArrayList<String> LayDSMonHoc(String hk) {
        ArrayList<String> ds = new ArrayList<>();
        ArrayList<DTO_HocPhan> dsmon = new ArrayList<>();
        dsmon = dbSV.Doc_MHTheoKhoa_HK(DBSV.getLuuSinhVien().get_Khoa(), hk);

        for (int i = 0; i < dsmon.size(); i++) {
            DTO_HocPhan monHoc = dsmon.get(i);
            ds.add(monHoc.getTenMH());
        }

        tvSLHP.setText("" + ds.size());
        return ds;
    }

    public ArrayList<DTO_HocPhan> LayDSMonHocDaDK() {
        dbSV.Doc_DKMH(maSV);

        ArrayList<DTO_HocPhan> dsMHDaDK = new ArrayList<>();
        for (int i = 0; i < DBSV.dsDKMH.size(); i++) {
            DTO_HocPhan monHoc = new DTO_HocPhan();
            monHoc = dbSV.Doc_MHTheoMaMH(DBSV.dsDKMH.get(i).getMaMH());
            dsMHDaDK.add(monHoc);
        }
        return dsMHDaDK;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}