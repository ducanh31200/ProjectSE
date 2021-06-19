package com.example.monitoring_app.ui.home;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.monitoring_app.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class HomeFragment extends Fragment {

    private static final int RESULT_OK = -1;
    //private HomeViewModel homeViewModel;
    Button btnAdd, btnCancel;
    ImageButton ibtnCamera, ibtnFolder;
    ImageView imgHinh;

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FOLDER = 456;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btnAdd      = (Button) root.findViewById(R.id.buttonAdd);
        btnCancel   = (Button) root.findViewById(R.id.buttonHuy);
        imgHinh     = (ImageView) root.findViewById(R.id.imageViewHinh);
        ibtnCamera  = (ImageButton) root.findViewById(R.id.imageButtonCamera);
        ibtnFolder  = (ImageButton) root.findViewById(R.id.imageButtonFolder);


        ibtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA);
                /*ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA
                        ); */
            }
        });

        ibtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(("image/*"));
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
                /*ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER
                );*/
            }
        });



        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh.setImageBitmap(bitmap);
        }

        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            try {

                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


}