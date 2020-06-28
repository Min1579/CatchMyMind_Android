package com.min.catchmymind_android.api;

import com.min.catchmymind_android.dto.BoardRegisterRequestDto;
import com.min.catchmymind_android.dto.AllBoardResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BoardAPI {
    @POST("api/board/register/{userId}")
    Call<Long> boardRegister(@Path("userId")final Long userId, @Body final BoardRegisterRequestDto request);

    @GET("api/board")
    Call<List<AllBoardResponseDto>> getAllBoards();
}
