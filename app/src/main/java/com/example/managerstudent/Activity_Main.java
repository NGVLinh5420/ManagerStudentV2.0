package com.example.managerstudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.Linh.L_ThongKe_BarChart;
import com.example.managerstudent.Linh.L_DKHocPhan_Activity;
import com.example.managerstudent.Linh.L_Diem_Activity;
import com.example.managerstudent.Linh.L_SinhVien_Activity;
import com.example.managerstudent.Minh.M_Khoa;
import com.example.managerstudent.fragment.Fragment_Account;
import com.example.managerstudent.fragment.Fragment_Home;
import com.example.managerstudent.fragment.Scrolling_Fragment;
import com.google.android.material.navigation.NavigationView;

public class Activity_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ImageButton btnLogOut;
    ActionBarDrawerToggle toggle;

    DBSV dbSV = new DBSV(this);

    //giup phat hien fragment nao dang mo
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_DIEUKHOAN = 1;
    private static final int FRAGMENT_TAIKHOAN = 2;

    //FRAGMENT DANG CHAY
    //gán nó mặc định là Fragment Home
    private int nowFragment = FRAGMENT_HOME;
    private int dieuKhoanFragment = FRAGMENT_DIEUKHOAN;
    private int taiKhoanFragment = FRAGMENT_TAIKHOAN;

    //---------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.l_gui_nav_action_bar);

        setControls();
        setEvents();
    }

    private void setControls() {
        drawerLayout = findViewById(R.id.drawer_Layout);
        toolbar = findViewById(R.id.nav_top_toobar);
        navigationView = findViewById(R.id.nav_view);
        btnLogOut = findViewById(R.id.nav_top_toobar_btnLogOut);
    }

    private void setEvents() {

        //dua toolbar vao
        setSupportActionBar(toolbar);

        //click open/close nav
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //Log In APP mặc định vào Home Fragment
        replaceFragment(new Fragment_Home());
        navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(true);

        //tao su kien khi nhan menu item.
        navigationView.setNavigationItemSelectedListener(this);

        // Nút Log Out
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(Activity_Main.this, Activity_Login.class);
                startActivity(intent);

                Toast.makeText(Activity_Main.this, "Log Out.", Toast.LENGTH_SHORT).show();
            }
        });

        //First Data SQLite
//        dbSV.FirstData();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_nav_Home) {
            if (nowFragment != FRAGMENT_HOME) {
                replaceFragment(new Fragment_Home());
                nowFragment = FRAGMENT_HOME;

                navigationView.getMenu().findItem(R.id.menu_nav_TaiKhoan).setChecked(false);
                navigationView.getMenu().findItem(R.id.menu_nav_DieuKhoan).setChecked(false);
                navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(true);
            }
        }

        if (id == R.id.menu_nav_Info) {
            Intent intent = new Intent(this, L_SinhVien_Activity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Score) {
            Intent intent = new Intent(this, L_Diem_Activity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Khoa) {
            Intent intent = new Intent(this, M_Khoa.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_DKmon) {
            Intent intent = new Intent(this, L_DKHocPhan_Activity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_ThongKeBarChart) {
            Intent intent = new Intent(this, L_ThongKe_BarChart.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_TaiKhoan) {
            if (nowFragment != FRAGMENT_TAIKHOAN) {
                replaceFragment(new Fragment_Account());
                nowFragment = FRAGMENT_TAIKHOAN;

                navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(false);
                navigationView.getMenu().findItem(R.id.menu_nav_TaiKhoan).setChecked(true);
                navigationView.getMenu().findItem(R.id.menu_nav_DieuKhoan).setChecked(false);
            }
        }

        if (id == R.id.menu_nav_DieuKhoan) {
            if (nowFragment != FRAGMENT_DIEUKHOAN) {
                replaceFragment(new Scrolling_Fragment());
                nowFragment = FRAGMENT_DIEUKHOAN;

                navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(false);
                navigationView.getMenu().findItem(R.id.menu_nav_TaiKhoan).setChecked(false);
                navigationView.getMenu().findItem(R.id.menu_nav_DieuKhoan).setChecked(true);
            }
        }

        if (id == R.id.menu_nav_Dangxuat) {
            finish();
            Intent intent = new Intent(this, Activity_Login.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout_replace, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            //neu nav dang mo, nhan nut 'back' se dong no lai, roi thoat app
        } else {
            super.onBackPressed();
        }
    }

}