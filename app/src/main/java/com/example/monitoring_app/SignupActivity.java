package com.example.monitoring_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    private AwesomeValidation awesomeValidation;

    TextInputLayout txtUsername, txtPassword, txtPasswordToo;
    Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        Mapping();

        awesomeValidation.addValidation(this, R.id.txtUsername, "[a-zA-Z\\s]+", R.string.nameerror);
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(this, R.id.txtPassword, regexPassword, R.string.err_password);
        awesomeValidation.addValidation(this, R.id.txtPasswordToo, R.id.txtPassword, R.string.err_password_confirmation);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                }
            }
        });
    }

    private void Mapping() {
        txtUsername = (TextInputLayout) findViewById(R.id.txtUsername);
        txtPassword = (TextInputLayout) findViewById(R.id.txtPassword);
        txtPasswordToo = (TextInputLayout) findViewById(R.id.txtPasswordToo);
        btnSignup = (Button) findViewById(R.id.btnSignup);
    }
}