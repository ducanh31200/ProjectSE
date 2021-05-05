package com.example.monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    Button btnLogin;
    TextView lblForgotPassword, lblSignup;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Mapping();

        database = new Database(this, "GiamSat.sqlite", null, 1);

        database.QueryData("CREATE TABLE IF NOT EXISTS User(Id INTEGER PRIMARY KEY AUTOINCREMENT, Username VARCHAR(150), Password VARCHAR(200), Description VARCHAR(200))");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textInputLayoutUsername.getEditText().getText().toString().trim();
                String password = textInputLayoutPassword.getEditText().getText().toString().trim();

                // Giả sử
                if(username == "congchien" && password == "123") {
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }else {
                    //Toast.makeText(MainActivity.this, "Username: " + username + " Phone: " + password, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, HomeActivity.class));
                }
            }
        });

        lblSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });

        lblForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgottenPasswordActivity.class));
            }
        });
    }

    private  void Mapping() {
        textInputLayoutUsername     = (TextInputLayout) findViewById(R.id.txtUsername);
        textInputLayoutPassword     = (TextInputLayout) findViewById(R.id.txtPassword);
        btnLogin                    = (Button) findViewById(R.id.btnLogin);
        lblForgotPassword           = (TextView) findViewById(R.id.lblForgetPassword);
        lblSignup                   = (TextView) findViewById(R.id.lblSignup);
    }

}