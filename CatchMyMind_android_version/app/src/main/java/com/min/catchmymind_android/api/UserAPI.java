package com.min.catchmymind_android.api;

import com.min.catchmymind_android.dto.UserLoginRequestDto;
import com.min.catchmymind_android.dto.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface UserAPI {
    @GET("api/user/{userId}")
    Call<User> findUserById(@Path("userId") Long userId);

    @POST("api/user/register")
    Call<Long> userRegister(@Body User user);

    @POST("api/user/login")
    Call<Long> findUserByEmailAndPassword(@Body UserLoginRequestDto request);
}
