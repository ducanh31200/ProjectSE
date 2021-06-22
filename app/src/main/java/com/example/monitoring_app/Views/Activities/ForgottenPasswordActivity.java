package com.example.monitoring_app.Views.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.monitoring_app.JavaMailAPI;
import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.account.Account;
import com.example.monitoring_app.ui.account.AccountPresenter;

import java.util.Random;

public class ForgottenPasswordActivity extends AppCompatActivity {

    private EditText edt_ForgotPass,edt_Username;
    private Button btn_ForgotPass;
    private AccountPresenter accountPresenter;
    private Account account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotten_password);

        edt_ForgotPass = findViewById(R.id.editTextForPwd);
        btn_ForgotPass = findViewById(R.id.buttonSend);
        edt_Username = findViewById(R.id.edittext_Username);

        btn_ForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void sendMail()
    {
        String Email = edt_ForgotPass.getText().toString().trim();
        String Username = edt_Username.getText().toString().trim();
        accountPresenter = new AccountPresenter(ForgottenPasswordActivity.this);
        account = accountPresenter.getAccount(Username);
        Random random = new Random();
        String ps = "AWcwawd90awth77Gwdjko05746569wdwdj77CF";
        int ran = random.nextInt(20) + 4;
        String newPass = ps.substring(6,ran);
        //Log.i("NewPass",newPass);
        if(account!=null) {
            String EmailCurrent = account.getMail();
           if(Email.equals(EmailCurrent)) {
               account.setPassword(newPass);
               accountPresenter.editAccount(account);
               //khúc này là gửi mail
               String subject = "Monitoring App";
               String Mess = "Mật khẩu mới của bạn là: " + newPass;
               JavaMailAPI javaMailAPI = new JavaMailAPI(this, Email, subject, Mess);
               javaMailAPI.execute();
           }
           else {
               Toast.makeText(this, "Your Email not exist", Toast.LENGTH_SHORT).show();
           }
        }
        else {
            Toast.makeText(this, "Username not exist", Toast.LENGTH_SHORT).show();
        }


    }

}