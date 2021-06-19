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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.monitoring_app.R;
import com.example.monitoring_app.app.AppConfig;
import com.example.monitoring_app.app.AppController;
import com.example.monitoring_app.Models.SQLiteHandler;
import com.example.monitoring_app.helper.SessionManager;
import com.example.monitoring_app.ui.account.AccountPresenter;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = SignupActivity.class.getSimpleName();
    TextInputLayout textInputLayoutEmail, textInputLayoutPassword;
    Button btnLogin;
    TextView lblForgotPassword, lblSignup;
    AccountPresenter accountPresenter;
    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new SQLiteHandler(getApplicationContext());

        Mapping();
        accountPresenter = new AccountPresenter(getApplicationContext());
        // SQLite database handler


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textInputLayoutEmail.getEditText().getText().toString().trim();
                String password = textInputLayoutPassword.getEditText().getText().toString().trim();

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (accountPresenter.checkLogin(username, password)) {
                        accountPresenter.setCurrent(username);
                        Intent intent = new Intent(MainActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(),
                                "Username or password is incorrect!", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
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
        textInputLayoutEmail     = (TextInputLayout) findViewById(R.id.txtEmail);
        textInputLayoutPassword     = (TextInputLayout) findViewById(R.id.txtPassword);
        btnLogin                    = (Button) findViewById(R.id.btnLogin);
        lblForgotPassword           = (TextView) findViewById(R.id.lblForgetPassword);
        lblSignup                   = (TextView) findViewById(R.id.lblSignup);
    }
}