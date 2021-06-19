package com.example.monitoring_app.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.monitoring_app.R;
import com.example.monitoring_app.app.AppConfig;
import com.example.monitoring_app.Models.SQLiteHandler;
import com.example.monitoring_app.helper.SessionManager;
import com.example.monitoring_app.ui.account.AccountPresenter;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {


    private static final String TAG = SignupActivity.class.getSimpleName();

    private SQLiteHandler db;

    TextInputLayout txtUserName,txtName, txtEmail, txtPassword;
    Button btnSignup;
    TextView lblLogin;
    AccountPresenter accountPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        accountPresenter = new AccountPresenter(getApplicationContext());
        Mapping();

        // SQLite database handler
        //db = new SQLiteHandler(getApplicationContext());


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUserName.getEditText().getText().toString().trim();
                String name = txtName.getEditText().getText().toString().trim();
                String email = txtEmail.getEditText().getText().toString().trim();
                String password = txtPassword.getEditText().getText().toString().trim();

                if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                    accountPresenter.addAccount(username,name,password,email);

                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter your details!", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        lblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, MainActivity.class));
            }
        });
    }

    private void Mapping() {
        txtUserName = (TextInputLayout)  findViewById(R.id.txtUsername);
        txtName = (TextInputLayout) findViewById(R.id.txtName);
        txtEmail = (TextInputLayout) findViewById(R.id.txtEmail);
        txtPassword = (TextInputLayout) findViewById(R.id.txtPassword);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        lblLogin = (TextView) findViewById(R.id.lblLogin);
    }




}