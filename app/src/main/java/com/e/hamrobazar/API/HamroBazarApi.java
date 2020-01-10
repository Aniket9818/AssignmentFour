package com.e.hamrobazar.API;

import com.e.hamrobazar.Model.Product;
import com.e.hamrobazar.Model.User;
import com.e.hamrobazar.ServerResponse.ImageResponse;
import com.e.hamrobazar.ServerResponse.UserResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface HamroBazarApi {

    @POST("signup")
    Call<UserResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("products")
    Call<List<Product>>getProduct();
}
