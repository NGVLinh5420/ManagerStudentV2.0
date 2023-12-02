package com.example.managerstudent.Linh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_Diem_ThongKe_Gioi extends AppCompatActivity {
    private Button btnBack;
    private TextView tvSoLuongSV;
    private ListView lvSinhVien;
    private ArrayAdapter<DTO_SV> adapterLV;

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
    }

    private void setEvent() {

        //--2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_Diem_ThongKe_Gioi.super.onBackPressed();
            }
        });
    }
}