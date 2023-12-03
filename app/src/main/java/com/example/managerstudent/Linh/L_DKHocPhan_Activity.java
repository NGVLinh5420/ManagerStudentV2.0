package com.example.managerstudent.Linh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_DKHocPhan_Activity extends AppCompatActivity {

    TextView tvSoLuongSV;
    Button btnBack;
    private ListView lvSinhVien;
    static public ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbSV = new DBSV(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_dkhp_activity);

        setControl();
        setEvent();
    }

    private void setControl() {
        btnBack = findViewById(R.id.dkm_btnBack);

        tvSoLuongSV = findViewById(R.id.dkm_tvSoLuongSV);
        lvSinhVien = findViewById(R.id.dkm_lvSinhVien);
        registerForContextMenu(lvSinhVien);
    }

    private void setEvent() {
        //--1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbSV = new DBSV(this);
        dbSV.Doc_SinhVien();

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DBSV.getDsSinhVien());
        lvSinhVien.setAdapter(adapterListView);

        //số lượng ds sinh viên
        String sizeDSSV = "" + DBSV.getDsSinhVien().size();
        tvSoLuongSV.setText(sizeDSSV);


        //--2.Buttons
        // Click ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long viTri) {
                //Truyền MSSV của đối tượng SV đã Click sang class.Thông tin chi tiết
                DBSV.setLuuSinhVien(DBSV.getDsSinhVien().get((int) viTri));

                Intent intent = new Intent(L_DKHocPhan_Activity.this, L_DKHocPhan_ChiTiet.class);
                startActivity(intent);
            }
        });

        // Quay Về Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_DKHocPhan_Activity.super.onBackPressed();
            }
        });
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
            DBSV.setLuuMSSV(DBSV.dsSinhVien.get((int) position).get_MSSV());
            Intent intent = new Intent(L_DKHocPhan_Activity.this, L_SinhVien_ThongTin.class);
            startActivity(intent);
        } else if (item.getTitle() == "Xem Điểm") {
            //Lưu SV vào class DBSV
            DBSV.setLuuSinhVien(DBSV.dsSinhVien.get((int) position));
            Intent intent = new Intent(L_DKHocPhan_Activity.this, L_Diem_SinhVien.class);
            startActivity(intent);
        } else {
            return false;
        }
        return true;
    }
}