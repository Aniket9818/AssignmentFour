package com.e.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    private CheckBox cb1, cb2, cb3;
    private Button btnRregister;
    private ImageView imgProfile;
    private EditText etREmail, etName, etRPassword, etRCPassword, etPhone, etMphone, etAddress, etAddress1, etAddress2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etREmail=findViewById(R.id.etREmail);
        etName=findViewById(R.id.etREmail);
        etRPassword=findViewById(R.id.etRPassword);
        etRCPassword=findViewById(R.id.etRCPassword);
        etPhone=findViewById(R.id.etPhone);
        etAddress=findViewById(R.id.etAddress);
        etAddress1=findViewById(R.id.etAddress1);
        etAddress2=findViewById(R.id.etAddress2);
        cb1=findViewById(R.id.cb1);
        cb2=findViewById(R.id.cb2);
        cb3=findViewById(R.id.cb3);
        imgProfile = findViewById(R.id.imgProfile);
        btnRregister=findViewById(R.id.btnRregister);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });







        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb1.isChecked()){

                }
            }
        });

    }

    private void BrowseImage(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (data == null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
            }

        }

        Uri uri = data.getData();
        imgProfile.setImageURI(uri);
    }
}
