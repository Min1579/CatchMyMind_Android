package com.min.catchmymind_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.min.catchmymind_android.api.BoardAPI;
import com.min.catchmymind_android.dto.BoardRegisterRequestDto;
import com.min.catchmymind_android.util.RequestUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostBoardActivity extends AppCompatActivity {
    private Button reset,submit, goback;
    private EditText title, content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_board);

        final Long userId = getIntent().getExtras().getLong("userId");

        reset = findViewById(R.id.reset);
        submit = findViewById(R.id.submit_post);
        goback = findViewById(R.id.go_to_waiting_room);

        title = findViewById(R.id.board_title);
        content = findViewById(R.id.board_content);


        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), WaitingRoomActivity.class));
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title.setText("");
                content.setText("");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String inputTitle = title.getText().toString();
                final String inputContent = content.getText().toString();

                if (title.getText().toString().length() <= 6) {
                    Toast.makeText(getApplicationContext(), "글 제목을 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (content.getText().toString().length() <= 0) {
                    Toast.makeText(getApplicationContext(), "본문을 입력해세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                BoardAPI boardAPI = RequestUtil.createService(BoardAPI.class);
                Call<Long> response = boardAPI.boardRegister(userId ,
                                                        new BoardRegisterRequestDto(inputTitle, inputContent));
                response.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if (response.isSuccessful()) {
                            if (response.body() > 0){
                                Intent intent = new Intent(getApplicationContext(), WaitingRoomActivity.class);
                                intent.putExtra("userId", userId);
                                startActivity(intent);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "네트워크 오류..다시시도", Toast.LENGTH_SHORT);
                    }
                });

            }
        });
    }
}
