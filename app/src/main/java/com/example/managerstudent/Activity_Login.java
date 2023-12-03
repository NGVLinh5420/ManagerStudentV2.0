package com.example.managerstudent;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.managerstudent.DB.DBAccount;
import com.example.managerstudent.Minh.models.M_Account;

import java.util.ArrayList;
import java.util.List;

public class Activity_Login extends AppCompatActivity {

    //DECLARE
    Button btnSignIn, btnSignUp;
    EditText edtUser, edtPassword;
    List<M_Account> Acc = new ArrayList<>();

    DBAccount dbacconut = new DBAccount(this, null, null, 1);
    public static int minutes = 0;
    public static int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gui_login);

        // Bắt đầu đếm thời gian khi ứng dụng được mở
        long startTime = System.currentTimeMillis();
        new CountDownTimer(Long.MAX_VALUE, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = System.currentTimeMillis() - startTime;
                seconds = (int) (millis / 1000);
                minutes = seconds / 60;
                seconds = seconds % 60;
            }

            public void onFinish() {
            }
        }.start();

        //
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
                Intent inte = new Intent(Activity_Login.this, Activity_SignUp.class);
                startActivity(inte);
            }
        });

    }

    private void setControls() {
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        edtUser = findViewById(R.id.edtUser);
        edtPassword = findViewById(R.id.edtPassword);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

}