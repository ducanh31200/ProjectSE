package com.example.monitoring_app.ui.changepassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.monitoring_app.R;
import com.example.monitoring_app.Views.Activities.HomeActivity;
import com.example.monitoring_app.Views.Activities.MainActivity;
import com.example.monitoring_app.ui.account.Account;
import com.example.monitoring_app.ui.account.AccountPresenter;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends Fragment {

    EditText currentPwd, newPwd, RePwd;
    Button btnChange;
    View root;
    String Username;
    AccountPresenter accountPresenter;
    Account account;
    private AwesomeValidation awesomeValidation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_change_password, container, false);

        currentPwd = (EditText) root.findViewById(R.id.editTextCurrentPwd);
        newPwd = (EditText) root.findViewById(R.id.editTextNewPwd);
        RePwd = (EditText) root.findViewById(R.id.editTextRePwd);
        btnChange = (Button) root.findViewById(R.id.btnChange);

        accountPresenter = new AccountPresenter(getContext());
        Username = accountPresenter.getCurrent();
        account = accountPresenter.getAccount(Username);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String curpass = currentPwd.getText().toString().trim();
                String newpass = newPwd.getText().toString().trim();
                String repass = RePwd.getText().toString().trim();
                if (!newpass.isEmpty() && !curpass.isEmpty() && !repass.isEmpty()) {
                    if (accountPresenter.checkLogin(Username, curpass)) {
                        if (newpass.equals(repass))
                        {
                            account.setPassword(newpass);
                            accountPresenter.editAccount(account);
                            currentPwd.setText("");
                            newPwd.setText("");
                            RePwd.setText("");
                        }
                        else
                        {
                            Toast.makeText(getContext(),
                                    "NewPass and RePass is not same!", Toast.LENGTH_LONG)
                                    .show();
                            Log.e("khonggiong","khonggiong");
                        }
                    } else {
                        Toast.makeText(getContext(),
                                "Username or password is incorrect!", Toast.LENGTH_LONG)
                                .show();
                        Log.e("saipass","saipass");
                    }
                } else {
                    Toast.makeText(getContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                    Log.e("trong","trong");
                }
            }
        });
        return root;
    }

}
