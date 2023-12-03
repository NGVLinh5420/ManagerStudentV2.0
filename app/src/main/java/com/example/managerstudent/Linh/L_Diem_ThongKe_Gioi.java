package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Khoa;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_Diem_ThongKe_Gioi extends AppCompatActivity {
    private Button btnBack;
    private Spinner spKhoa, spHocKy;
    private TextView tvSoLuongSV;
    private ListView lvSinhVien;
    private ArrayAdapter<DTO_SV> adapterLV;
    private ArrayList<DTO_SV> dsSVGioi = new ArrayList<>();
    private DBSV dbSV = new DBSV(this);
    String Khoa;
    String HocKy = "HK1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_diem_thong_ke_gioi);

        setControl();
        setEvent();
    }

    private void setControl() {
        lvSinhVien = findViewById(R.id.dtk_lvSinhVien);
        btnBack = findViewById(R.id.dtk_btnBack);
        tvSoLuongSV = findViewById(R.id.dtk_tvSoLuongSV);

        spKhoa = findViewById(R.id.dtk_SpinnerKhoa);
        spHocKy = findViewById(R.id.dtk_SpinnerHK);

        //Spinner Học Kỳ
        ArrayAdapter<CharSequence> adapterSPHocKy = ArrayAdapter.createFromResource(this, R.array.HocKy, android.R.layout.select_dialog_item);
        adapterSPHocKy.setDropDownViewResource(android.R.layout.select_dialog_item);
        spHocKy.setAdapter(adapterSPHocKy);

        //Spinner Khoa
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this,
                android.R.layout.select_dialog_item, LayDSKhoa());
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spKhoa.setAdapter(adapterSpinner);
    }

    public ArrayList<String> LayDSKhoa() {
        ArrayList<String> dsKhoa = new ArrayList<>();

        dbSV.Doc_Khoa();
        for (int i = 0; i < DBSV.dsKhoa.size(); i++) {
            DTO_Khoa khoa = DBSV.dsKhoa.get(i);

            dsKhoa.add(khoa.getTenKhoa());
        }
        return dsKhoa;

    }

    private void setEvent() {
        //--1.
        adapterLV = new ArrayAdapter<>(L_Diem_ThongKe_Gioi.this, android.R.layout.simple_list_item_1, dsSVGioi);

        //--2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_Diem_ThongKe_Gioi.super.onBackPressed();
            }
        });

        spKhoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Khoa = parent.getItemAtPosition(position).toString();

                spHocKy.setSelection(0); //đặt hk về lại HK1, sau mỗi lần chọn Khoa.
                dsSVGioi = dbSV.Doc_SinhVien_Gioi(Khoa, HocKy);
                adapterLV.clear();
                adapterLV.addAll(dsSVGioi);
                adapterLV.notifyDataSetChanged();
                lvSinhVien.setAdapter(adapterLV);

                tvSoLuongSV.setText(String.valueOf(lvSinhVien.getCount()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spHocKy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HocKy = parent.getItemAtPosition(position).toString();

                dsSVGioi = dbSV.Doc_SinhVien_Gioi(Khoa, HocKy);
                adapterLV.clear();
                adapterLV.addAll(dsSVGioi);
                adapterLV.notifyDataSetChanged();
                lvSinhVien.setAdapter(adapterLV);

                tvSoLuongSV.setText(String.valueOf(lvSinhVien.getCount()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBSV.setLuuMSSV(dsSVGioi.get(position).get_MSSV());

                Intent intent = new Intent(L_Diem_ThongKe_Gioi.this, L_SinhVien_ThongTin.class);
                startActivity(intent);
            }
        });
    }
}