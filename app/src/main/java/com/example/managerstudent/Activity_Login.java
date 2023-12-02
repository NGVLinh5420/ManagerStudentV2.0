package com.example.managerstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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