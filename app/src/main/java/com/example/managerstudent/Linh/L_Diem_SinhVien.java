package com.example.managerstudent.Linh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Diem;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_Diem_SinhVien extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView tvMssvTen, tvKhoa, tvSLHP, tvTenHP;
    private EditText edtDiem1, edtDiem2, edtDiemTB;
    private Spinner spHocKy;
    private Button btnBack;
    private ImageButton btnSave, btnRefresh;
    private ListView lvHocPhan;
    private ArrayAdapter<DTO_HocPhan> adapterLV;

    String maSV = DBSV.getLuuSinhVien().get_MSSV();
    String maMonHoc = "";
    String tenMonHoc = "";
    private ArrayList<DTO_HocPhan> dsHocPhan;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_diem);

        setControl();
        setEvent();
    }

    private void setControl() {
        tvMssvTen = findViewById(R.id.d_tvMssvTen);
        tvKhoa = findViewById(R.id.d_tvKhoa);
        tvSLHP = findViewById(R.id.d_tvSLMon);
        tvTenHP = findViewById(R.id.d_tvTenHocPhan);

        edtDiem1 = findViewById(R.id.d_edtDiem1);
        edtDiem2 = findViewById(R.id.d_edtDiem2);
        edtDiemTB = findViewById(R.id.d_edtDiemTB);

        lvHocPhan = findViewById(R.id.d_lvMonHoc);

        btnBack = findViewById(R.id.d_btnBack2);
        btnSave = findViewById(R.id.d_ibtnSave);
        btnRefresh = findViewById(R.id.d_ibtnRefresh);

        //Spinner
        spHocKy = findViewById(R.id.d_spHocKy);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.HocKy, android.R.layout.select_dialog_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spHocKy.setAdapter(adapterSpinner);
        spHocKy.setOnItemSelectedListener(this);
    }

    private void setEvent() {
        //--1.
        tvMssvTen.setText(maSV + "\n" + DBSV.getLuuSinhVien().get_Ten());
        tvKhoa.setText(DBSV.getLuuSinhVien().get_Khoa());


        //--2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_Diem_SinhVien.super.onBackPressed();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double diem1, diem2;
                String edt1 = edtDiem1.getText().toString();
                String edt2 = edtDiem2.getText().toString();

                if (!edt1.isEmpty() || !edt2.isEmpty()) {
                    diem1 = Double.parseDouble(edtDiem1.getText().toString());
                    diem2 = Double.parseDouble(edtDiem2.getText().toString());
                    Boolean flag = true;

                    if (diem1 < 0 || diem1 > 10) {
                        flag = false;
                        edtDiem1.setError("0-10");
                    }
                    if (diem2 < 0 || diem2 > 10) {
                        flag = false;
                        edtDiem2.setError("0-10");
                    }

                    if (flag) {
                        double diemTB = (diem1 + diem2) / 2;
                        edtDiemTB.setText(String.valueOf(diemTB));
                    }
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double diem1, diem2;
                String edt1 = edtDiem1.getText().toString();
                String edt2 = edtDiem2.getText().toString();

                if (!edt1.isEmpty() || !edt2.isEmpty()) {
                    diem1 = Double.parseDouble(edtDiem1.getText().toString());
                    diem2 = Double.parseDouble(edtDiem2.getText().toString());
                    Boolean flag = true;

                    if (diem1 < 0 || diem1 > 10) {
                        flag = false;
                        edtDiem1.setError("0-10");
                    }
                    if (diem2 < 0 || diem2 > 10) {
                        flag = false;
                        edtDiem2.setError("0-10");
                    }

                    if (flag) {
                        double diemTB = (diem1 + diem2) / 2;
                        dbSV.Sua_Diem(new DTO_Diem(maMonHoc, maSV, String.valueOf(diem1), String.valueOf(diem2)));
                        Toast.makeText(L_Diem_SinhVien.this, "Đã Save", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(L_Diem_SinhVien.this, "Có Lỗi!!", Toast.LENGTH_SHORT).show();

            }
        });

        //ListView
        lvHocPhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int viTri, long id) {
                //Unlock TextBox nhập điểm
                DTO_Diem diem = dbSV.Doc_DiemTheoMSSV_MaMH(maSV, dsHocPhan.get(viTri).getMaMH());

                edtDiem1.setEnabled(true);
                edtDiem2.setEnabled(true);

                if (diem.get_mssv() != null) {
                    maMonHoc = dsHocPhan.get(viTri).getMaMH();
                    tenMonHoc = dsHocPhan.get(viTri).getTenMH();
                    tvTenHP.setText(tenMonHoc);
                    edtDiem1.setText(diem.get_diem1());
                    double diem1 = Double.parseDouble(diem.get_diem1());
                    edtDiem2.setText(diem.get_diem2());
                    double diem2 = Double.parseDouble(diem.get_diem2());

                    double diemTB = (diem1 + diem2) / 2;
                    edtDiemTB.setText(String.valueOf(diemTB));
                }
            }
        });

        //--3. Exception
        //Giới hạn nhập điểm từ 0.00 tới 10
//        double diem1 = Double.parseDouble(edtDiem1.getText().toString());
//        if (diem1 < 0 || diem1 > 10) edtDiem1.setError("!");
    }


    //--METHOD
    //Spinner adapter
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        dsHocPhan = dbSV.Doc_MHTheoMSSV_HK(maSV, text);
        adapterLV = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsHocPhan);

        lvHocPhan.setAdapter(adapterLV);

        edtDiem1.setText("");
        edtDiem2.setText("");
        edtDiemTB.setText("");

        tvSLHP.setText("" + dsHocPhan.size());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}