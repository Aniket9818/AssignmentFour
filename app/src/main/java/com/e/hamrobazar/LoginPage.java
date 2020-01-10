package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.e.hamrobazar.Bll.Loginbll;
import com.e.hamrobazar.StrictMode.StrictModeClass;

public class LoginPage extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnRegister, btnForgot;

    private TextView tvRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_page);


        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        tvRegister=findViewById(R.id.tvRegister);
        btnLogin=findViewById(R.id.btnLogin);
        btnForgot=findViewById(R.id.btnForgot);
        btnRegister=findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, Signup.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Loginbll loginBLL = new Loginbll();

        StrictModeClass.StrictMode();
        if (loginBLL.Authentication(email, password)) {
            Toast.makeText(this, "User logged in", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPage.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
            etEmail.requestFocus();
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Enter email");
            etEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            return false;
        }
        return true;
    }


    }

