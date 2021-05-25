package com.example.monitoring_app.ui.changepassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.monitoring_app.R;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends Fragment {

    EditText currentPwd, newPwd, RePwd;
    Button btnChange, btnNotChange;
    View root;

    private AwesomeValidation awesomeValidation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_change_password, container, false);

        //Mapping();

        /*String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        awesomeValidation.addValidation(getActivity(), R.id.txtOldPassword, regexPassword, R.string.err_password);
        awesomeValidation.addValidation(getActivity(), R.id.txtNewPassword, regexPassword, R.string.err_password);
        awesomeValidation.addValidation(getActivity(), R.id.txtNewPasswordAgain, R.id.txtNewPassword, R.string.err_password_confirmation);*/


        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private void Mapping() {

    }
}
