package com.example.monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;

    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;
    Button btnLogin;
    TextView lblForgotPassword, lblSignup;

    Database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        Mapping();

        awesomeValidation.addValidation(this, R.id.txtUsername, "[a-zA-Z\\s]+", R.string.nameerror);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(this, R.id.txtPassword, regexPassword, R.string.err_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textInputLayoutUsername.getEditText().getText().toString().trim();
                String password = textInputLayoutPassword.getEditText().getText().toString().trim();

                // Giả sử
                if (awesomeValidation.validate()) {

                    // CALL API LOGIN
                    doLogin(username, password);

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

    // Function Call API Login
    private void doLogin(String username, String password) {

    }

}