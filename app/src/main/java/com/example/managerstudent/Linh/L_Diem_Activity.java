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
import android.widget.ListView;
import android.widget.TextView;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.DTO.DTO_SV;
import com.example.managerstudent.R;

public class L_Diem_Activity extends AppCompatActivity {

    private Button btnBack, btnThongKe;
    private TextView tvSoLuongSV;
    private ListView lvSinhVien;
    private ArrayAdapter<DTO_SV> adapterListView;

    //DataBase
    private DBSV dbSV = new DBSV(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_diem_activity);

        setControls();
        setEvents();
    }

    private void setControls() {
        lvSinhVien = findViewById(R.id.d_lvSinhVien);
        registerForContextMenu(lvSinhVien);

        btnBack = findViewById(R.id.d_btnBack);
        btnThongKe = findViewById(R.id.d_btnThongKeGioi);

        tvSoLuongSV = findViewById(R.id.d_tvSoLuongSV);
    }

    private void setEvents() {
        // --1.
        //Chay DB, đọc db và lấy ds sinhvien
        dbSV.Doc_SinhVien();
        dbSV.Doc_SinhVien();

        adapterListView = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, DBSV.getDsSinhVien());
        lvSinhVien.setAdapter(adapterListView);

        String sizeDSSV = "" + DBSV.sizeDSSV();
        tvSoLuongSV.setText(sizeDSSV);

        // ListView
        lvSinhVien.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int i, long viTri) {
                //Truyền đối tượng SV đã Click sang Activity tiếp theo
                DBSV.luuSinhVien = DBSV.dsSinhVien.get((int) viTri);

                Intent intent = new Intent(L_Diem_Activity.this, L_Diem_SinhVien.class);
                startActivity(intent);
            }
        });

        // --2.Buttons
        //Button Back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                L_Diem_Activity.super.onBackPressed();
            }
        });

        //Button Back
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(L_Diem_Activity.this, L_Diem_ThongKe_Gioi.class);
                startActivity(intent);
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
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position; // Vị trí của item được chọn trong ListView
        if (item.getTitle() == "Xem Thông Tin") {
            //Lưu SV vào class DBSV
            DBSV.setLuuMSSV(DBSV.dsSinhVien.get(position).get_MSSV());
            Intent intent = new Intent(L_Diem_Activity.this, L_SinhVien_ThongTin.class);
            startActivity(intent);
        }
        return true;
    }
}