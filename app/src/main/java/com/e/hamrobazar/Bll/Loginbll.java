package com.e.hamrobazar.Bll;

import com.e.hamrobazar.API.HamroBazarApi;
import com.e.hamrobazar.ServerResponse.UserResponse;
import com.e.hamrobazar.URL.URL;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Loginbll {

    boolean loginFlag = false;

    public boolean Authentication(String email, String password) {

        HamroBazarApi hamrobazarAPI = URL.getInstance().create(HamroBazarApi.class);
        Call<UserResponse> usersCall = hamrobazarAPI.checkUser(email, password);

        try {
            Response<UserResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                URL.token += loginResponse.body().getToken();
                loginFlag = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginFlag;
    }
}
