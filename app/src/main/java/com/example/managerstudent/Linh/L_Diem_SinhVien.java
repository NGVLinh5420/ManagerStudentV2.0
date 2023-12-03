package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_Diem;
import com.example.managerstudent.DTO.DTO_HocPhan;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_Diem_SinhVien extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView tvMssvTen, tvKhoa, tvSLHP;
    private EditText edtDiem1, edtDiem2, edtDiemTB;
    private Spinner spHocKy;
    private Button btnBack;
    private ListView lvHocPhan;
    private ArrayAdapter<DTO_HocPhan> adapterLV;
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

        edtDiem1 = findViewById(R.id.d_edtDiem1);
        edtDiem2 = findViewById(R.id.d_edtDiem2);
        edtDiemTB = findViewById(R.id.d_edtDiemTB);

        lvHocPhan = findViewById(R.id.d_lvMonHoc);

        btnBack = findViewById(R.id.d_btnBack2);


        //Spinner
        spHocKy = findViewById(R.id.d_spHocKy);
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this, R.array.HocKy, android.R.layout.select_dialog_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.select_dialog_item);
        spHocKy.setAdapter(adapterSpinner);
        spHocKy.setOnItemSelectedListener(this);
    }

    private void setEvent() {
        //--1.
        tvMssvTen.setText(DBSV.getLuuSinhVien().get_MSSV() + "\n" + DBSV.getLuuSinhVien().get_Ten());
        tvKhoa.setText(DBSV.getLuuSinhVien().get_Khoa());


        //--2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_Diem_SinhVien.super.onBackPressed();
            }
        });

        //ListView
        lvHocPhan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int viTri, long id) {
                //Unlock TextBox nhập điểm
                DTO_Diem diem = dbSV.Doc_DiemTheoMSSV_MaMH(DBSV.getLuuSinhVien().get_MSSV(), dsHocPhan.get(viTri).getMaMH());

                edtDiem1.setEnabled(true);
                edtDiem2.setEnabled(true);

                edtDiem1.setText(diem.get_diem1());
                double diem1 = Double.parseDouble(diem.get_diem1());
                edtDiem2.setText(diem.get_diem2());
                double diem2 = Double.parseDouble(diem.get_diem2());

                double diemTB = (diem1 + diem2) / 2;
                edtDiemTB.setText(String.valueOf(diemTB));
            }
        });

    }

    //--METHOD
    //Spinner adapter
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        dsHocPhan = dbSV.Doc_MHTheoMSSV_HK(DBSV.getLuuSinhVien().get_MSSV(), text);
        adapterLV = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsHocPhan);
        lvHocPhan.setAdapter(adapterLV);

        tvSLHP.setText("" + dsHocPhan.size());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}