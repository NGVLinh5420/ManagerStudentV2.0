package com.example.managerstudent;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.DB.DBAccount;
import com.example.managerstudent.Minh.models.M_Account;

import java.util.ArrayList;
import java.util.List;

public class Activity_Login extends AppCompatActivity {

    //DECLARE
    Button btnSignIn, btnSignUp;
    EditText edtUser, edtPassword;
    private Handler handler = new Handler();
    private Runnable runnable;
    TextView timeTextView;

    List<M_Account> Acc = new ArrayList<>();
    DBAccount dbacconut = new DBAccount(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_login);

        Acc = dbacconut.getListAccount();
        setControls();
        setEvents();
    }


    private void setEvents() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M_Account acount = new M_Account();
                for (M_Account item : dbacconut.getListAccount()) {
                    if (item.getNameAcc().equals(edtUser.getText().toString().trim())) {
                        if (item.getPass().equals(edtPassword.getText().toString().trim())) {
                            Intent inte = new Intent(Activity_Login.this, Activity_Main.class);
                            Toast.makeText(Activity_Login.this, "Đăng Nhập Thành Công.", Toast.LENGTH_SHORT).show();
                            startActivity(inte);
                            break;
                        } else if (!item.getPass().equals(edtPassword.getText().toString().trim())) {
                            Toast.makeText(Activity_Login.this, "Sai Mật Khẩu/Tài Khoản", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    } else {
                        Toast.makeText(Activity_Login.this, "Tài Khoản Không Tồn Tại.", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, Activity_SignUp.class);
                startActivity(intent);
            }
        });
    }

    private void setControls() {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.fragment_account, null);
        timeTextView = view.findViewById(R.id.acc_tvTime);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
//        finish();
//        super.onBackPressed();

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Đóng Ứng Dụng")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAndRemoveTask();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    @Override
    public void onResume() {
        super.onResume();
        long startTime = System.currentTimeMillis();
        runnable = new Runnable() {
            @Override
            public void run() {
                long millis = System.currentTimeMillis() - startTime;
                int seconds = (int) (millis / 1000) % 60;
                int minutes = (int) ((millis / (1000 * 60)) % 60);
                int hours = (int) ((millis / (1000 * 60 * 60)) % 24);
                timeTextView.setText("Time Line\n" + String.format("%02d : %02d : %02d", hours, minutes, seconds));
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

}