package com.example.managerstudent.Linh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

import java.util.ArrayList;

public class L_SinhVien_TimKiem_Activity extends AppCompatActivity {
    EditText edtNhap;
    TextView textView;
    Button btnBack;
    ImageButton btnTimKiem;
    private ListView lvSinhVien;
    static public ArrayAdapter<DTO_SV> adapterLV;


    //DataBase
    private DBSV dbsv = new DBSV(this);
    private ArrayList<DTO_SV> dsSV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_tim_kiem);

        setControl();
        setEvent();
    }

    private void setControl() {
        btnBack = findViewById(R.id.tk_btnBack);
        btnTimKiem = findViewById(R.id.tk_btnTimKiem);
        edtNhap = findViewById(R.id.tk_edtNhap);
        textView = findViewById(R.id.tk_TextView);

        lvSinhVien = findViewById(R.id.tk_lvSinhVien);
        registerForContextMenu(lvSinhVien);
    }

    private void setEvent() {
        //--1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbsv = new DBSV(this);
        dbsv.Doc_SinhVien();

        adapterLV = new ArrayAdapter<>(L_SinhVien_TimKiem_Activity.this, android.R.layout.simple_list_item_1, dsSV);

        //--2.Buttons
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Gọi phương thức performLongClick() để hiển thị ContextMenu
                view.performLongClick();
            }
        });
        // Button tìm kiếm
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = "%";
                key += edtNhap.getText().toString();
                key += "%"; // key = "%key_cần_tìm%"

                dsSV = dbsv.TimKiem_SV(key, key);

                if (dsSV.size() != 0) {
                    textView.setVisibility(View.INVISIBLE);

                    //** Làm mới listview theo thời gian thực
                    adapterLV.clear();
                    adapterLV.addAll(dsSV);
                    adapterLV.notifyDataSetChanged();
                    lvSinhVien.setAdapter(adapterLV);

                } else
                    Toast.makeText(L_SinhVien_TimKiem_Activity.this, "Không Có Dữ Liệu.", Toast.LENGTH_SHORT).show();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_SinhVien_TimKiem_Activity.super.onBackPressed();
            }
        });
    }

    // Click List View
    private void ClickListView(String mssv) {
        //Lưu SV vào class DBSV
        DBSV.setLuuMSSV(mssv);
        Intent intent = new Intent(L_SinhVien_TimKiem_Activity.this, L_SinhVien_ThongTin.class);
        startActivity(intent);
    }


    //--Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Xem Thông Tin");
        menu.add(0, v.getId(), 0, "Xem Điểm");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position; // Vị trí của item được chọn trong ListView
        if (item.getTitle() == "Xem Thông Tin") {
            DBSV.setLuuMSSV(dsSV.get((int) position).get_MSSV());
            Intent intent = new Intent(L_SinhVien_TimKiem_Activity.this, L_SinhVien_ThongTin.class);
            startActivity(intent);
        } else if (item.getTitle() == "Xem Điểm") {
            //Lưu SV vào class DBSV
            DBSV.setLuuSinhVien(dsSV.get((int) position));
            Intent intent = new Intent(L_SinhVien_TimKiem_Activity.this, L_Diem_SinhVien.class);
            startActivity(intent);
        } else {
            return false;
        }
        return true;
    }
}