package com.e.hamrobazar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.hamrobazar.API.HamroBazarApi;
import com.e.hamrobazar.Model.User;
import com.e.hamrobazar.ServerResponse.ImageResponse;
import com.e.hamrobazar.ServerResponse.UserResponse;
import com.e.hamrobazar.StrictMode.StrictModeClass;
import com.e.hamrobazar.URL.URL;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup extends AppCompatActivity {

    private EditText etEmail, etFullName, etPassword, etRePassword, etPhone, etMobile, etAddress3, etAddress1, etAddress2;
    ImageView imgProfile;
    CheckBox cboTerms;
    Button btnRegister;
    String imagePath;
    private String imageName = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setTitle("Register New Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etEmail = findViewById(R.id.etEmail);
        etFullName = findViewById(R.id.etFullName);
        etPassword = findViewById(R.id.etPassword);
        etRePassword = findViewById(R.id.etRePassword);
        etPhone = findViewById(R.id.etPhone);
        etMobile = findViewById(R.id.etMobile);
        etAddress1 = findViewById(R.id.etAddress1);
        etAddress2 = findViewById(R.id.etAddress2);
        etAddress3 = findViewById(R.id.etAddress3);
        imgProfile = findViewById(R.id.imgProfile);
        cboTerms = findViewById(R.id.cboTerms);
        btnRegister = findViewById(R.id.btnRegister);

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
                BrowseImage();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    try {
                        saveImageOnly();
                    } catch (Exception e) {
                        imageName = "";
                    }
                    signUp();
                } else {
                    Toast.makeText(Signup.this, "Invalid entries", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(etEmail.getText())) {
            etEmail.setError("Enter email");
            etEmail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etFullName.getText())) {
            etFullName.setError("Enter full name");
            etFullName.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etPassword.getText())) {
            etPassword.setError("Enter password");
            etPassword.requestFocus();
            return false;
        } else if (etPassword.getText().toString().length() < 6) {
            etPassword.setError("Minimum 6 characters");
            return false;
        } else if (etPassword.getText().toString().length() > 20) {
            etPassword.setError("Maximum 20 characters");
            return false;
        } else if (TextUtils.isEmpty(etRePassword.getText())) {
            etRePassword.setError("Enter confirm password");
            etRePassword.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etMobile.getText())) {
            etMobile.setError("Enter mobile phone number");
            etMobile.requestFocus();
            return false;
        } else if (!TextUtils.isEmpty(etMobile.getText())) {
            String first = etMobile.getText().toString().charAt(0) + "";
            if (!first.equals("9")) {
                etMobile.setError("invalid mobile number");
                return false;
            }
        } else if (etMobile.getText().toString().length() != 10) {
            etMobile.setError("Mobile number should be 10 digit");
            return false;
        } else if (TextUtils.isEmpty(etAddress2.getText())) {
            etAddress2.setError("Enter area location");
            etAddress2.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(etAddress3.getText())) {
            etAddress3.setError("Enter city name");
            etAddress3.requestFocus();
            return false;
        } else if (!cboTerms.isChecked()) {
            cboTerms.setError("You must agree to terms of use");
            cboTerms.requestFocus();
            return false;
        } else if (!etPassword.getText().toString().equals(etRePassword.getText().toString())) {
            etRePassword.setError("Incorrect confirm password");
            etRePassword.requestFocus();
            return false;
        }
        return true;
    }

    private void BrowseImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        try {
            Uri uri = data.getData();
            imgProfile.setImageURI(uri);
            imagePath = getRealPathFromUri(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(),
                uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly() {
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile",
                file.getName(), requestBody);

        HamroBazarApi hamrobazarAPI = URL.getInstance().create(HamroBazarApi.class);
        Call<ImageResponse> responseBodyCall = hamrobazarAPI.uploadImage(body);

        StrictModeClass.StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            //Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp() {
        String email = etEmail.getText().toString();
        String fullname = etFullName.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();
        String mobile = etMobile.getText().toString();
        String address1 = etAddress1.getText().toString();
        String address2 = etAddress2.getText().toString();
        String address3 = etAddress3.getText().toString();

        User users = new User(email, fullname, password, phone, mobile, address1, address2, address3, imageName);

        HamroBazarApi hamrobazarAPI = URL.getInstance().create(HamroBazarApi.class);
        Call<UserResponse> signUpCall = hamrobazarAPI.registerUser(users);

        signUpCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Signup.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(Signup.this, "User registered successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(Signup.this, "Error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}
