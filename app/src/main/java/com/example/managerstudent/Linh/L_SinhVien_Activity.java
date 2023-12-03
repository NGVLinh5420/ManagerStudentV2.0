package com.example.managerstudent.Linh;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_SinhVien_Activity extends AppCompatActivity {

    TextView tvSoLuongSV;
    Button btnBack, btnThem, btnTimKiem;
    private ListView lvSinhVien;
    static public ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbsv = new DBSV(this);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_sv_activity);

        setControl();
        setEvent();
    }

    private void setControl() {
        btnBack = findViewById(R.id.tt_btnBack);
        btnThem = findViewById(R.id.tt_btnthem);
        btnTimKiem = findViewById(R.id.tt_btnTimKiem);

        tvSoLuongSV = findViewById(R.id.tt_tvSoLuongSV);
        lvSinhVien = findViewById(R.id.thongtin_lvSinhVien);
    }

    private void setEvent() {
        //--1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbsv = new DBSV(this);
        dbsv.Doc_SinhVien();

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DBSV.getDsSinhVien());
        lvSinhVien.setAdapter(adapterListView);
        registerForContextMenu(lvSinhVien);

        //số lượng ds sinh viên
        String sizeDSSV = "" + DBSV.getDsSinhVien().size();
        tvSoLuongSV.setText(sizeDSSV);


        //--2.Buttons
        // Click ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long viTri) {
                //Tìm đối tượng sv ở vị trí đã Click và lưu MSSV của nó vào DBSV
                ClickListView(DBSV.getDsSinhVien().get((int) viTri).get_MSSV());
            }
        });

        //Thêm Mới SV
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_Them.class);
                startActivity(intent);
            }
        });

        //Tìm kiếm SV
        btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_TimKiem_Activity.class);
                startActivity(intent);
            }
        });

        // Quay Về Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_SinhVien_Activity.super.onBackPressed();
            }
        });
    }


    // Click List View
    private void ClickListView(String mssv) {
        //Truyền MSSV của đối tượng SV đã Click sang class.Thông tin chi tiết
        DBSV.setLuuMSSV(mssv);

        Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_ThongTin.class);
        startActivity(intent);
    }


    //--Context Menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Xem Điểm");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position; // Vị trí của item được chọn trong ListView
        if (item.getTitle() == "Xem Điểm") {
            //Lưu SV vào class DBSV
            DBSV.setLuuMSSV(DBSV.dsSinhVien.get(position).get_MSSV());
            Intent intent = new Intent(L_SinhVien_Activity.this, L_SinhVien_ThongTin.class);
            startActivity(intent);
        }
        return true;
    }
}
