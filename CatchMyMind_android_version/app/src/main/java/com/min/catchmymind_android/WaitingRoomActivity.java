package com.min.catchmymind_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.min.catchmymind_android.api.BoardAPI;
import com.min.catchmymind_android.dto.AllBoardResponseDto;
import com.min.catchmymind_android.util.RequestUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WaitingRoomActivity extends AppCompatActivity {
    private Button webViewPage, loginPage, registerBoard;
    private ListView listView;
    private List<AllBoardResponseDto> boards;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_room);

        BoardAPI boardAPI = RequestUtil.createService(BoardAPI.class);
        Call<List<AllBoardResponseDto>> response = boardAPI.getAllBoards();
        response.enqueue(new Callback<List<AllBoardResponseDto>>() {
            @Override
            public void onResponse(Call<List<AllBoardResponseDto>> call, Response<List<AllBoardResponseDto>> response) {
                if (response.isSuccessful()) {
                    boards = response.body();
                    final ListAdapter adapter = new ListAdapter(getApplicationContext(),boards);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<AllBoardResponseDto>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 에러..", Toast.LENGTH_SHORT).show();
            }
        });

        webViewPage = findViewById(R.id.go_to_webview);
        loginPage = findViewById(R.id.go_to_login_page);
        registerBoard = findViewById(R.id.go_to_post);
        listView = findViewById(R.id.listView);


        final Long userId = getIntent().getExtras().getLong("userId");
        System.out.println("wr : " +userId);
        webViewPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WebGameViewActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        registerBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PostBoardActivity.class);
                intent.putExtra("userId", userId);
                startActivity(intent);
            }
        });
    }
}
