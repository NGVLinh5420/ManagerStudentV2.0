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

import com.example.managerstudent.DB.DBSV;
import com.example.managerstudent.Linh.L_ThongKe_BarChart;
import com.example.managerstudent.Linh.L_DKMon_DS;
import com.example.managerstudent.Linh.L_DiemActivity;
import com.example.managerstudent.Linh.L_SinhVienActivity;
import com.example.managerstudent.Minh.M_Khoa;
import com.example.managerstudent.fragment.Fragment_Home;
import com.google.android.material.navigation.NavigationView;

public class Activity_Main extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    ActionBarDrawerToggle toggle;

    DBSV dbSV = new DBSV(this);

    //giup phat hien fragment nao dang mo
    private static final int FRAGMENT_HOME = 0;

    //FRAGMENT DANG CHAY
    //gán nó mặc định là Fragment Home
    private int nowFragment = FRAGMENT_HOME;

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
                navigationView.getMenu().findItem(R.id.menu_nav_Home).setChecked(true);
            }
        }

        if (id == R.id.menu_nav_Info) {
            Intent intent = new Intent(this, L_SinhVienActivity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Score) {
            Intent intent = new Intent(this, L_DiemActivity.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Khoa) {
            Intent intent = new Intent(this, M_Khoa.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_DKmon) {
            Intent intent = new Intent(this, L_DKMon_DS.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_ThongKeDiem) {
            Intent intent = new Intent(this, L_ThongKe_BarChart.class);
            startActivity(intent);
        }

        if (id == R.id.menu_nav_Dangxuat) {
            finish();
            Intent intent = new Intent(this, Activity_Login.class);
            startActivity(intent);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment) {
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