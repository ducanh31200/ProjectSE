package com.example.monitoring_app.ui.changepassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoring_app.R;
import com.example.monitoring_app.ui.gallery.GalleryViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends Fragment {

    TextInputLayout textInputLayoutOldPassword, textInputLayoutNewPassword, textInputLayoutNewPasswordAgain;
    Button btnChange, btnCancelChangePwd;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_change_password, container, false);

        Mapping();
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
        textInputLayoutNewPassword          = (TextInputLayout) root.findViewById(R.id.txtOldPassword);
        textInputLayoutNewPassword          = (TextInputLayout) root.findViewById(R.id.txtNewPassword);
        textInputLayoutNewPasswordAgain     = (TextInputLayout) root.findViewById(R.id.txtNewPasswordAgain);
        btnChange                           = (Button) root.findViewById(R.id.btnChangePassword);
        btnCancelChangePwd                  = (Button) root.findViewById(R.id.btnCancelChangePwd);
    }
}
