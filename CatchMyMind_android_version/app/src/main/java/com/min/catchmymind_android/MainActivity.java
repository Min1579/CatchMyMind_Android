package com.min.catchmymind_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.min.catchmymind_android.api.UserAPI;
import com.min.catchmymind_android.dto.UserLoginRequestDto;
import com.min.catchmymind_android.util.RequestUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Button goToRegisterBtn, goToLoginBtn;
    private EditText userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goToRegisterBtn = findViewById(R.id.from_login_to_register_page);
        goToLoginBtn = findViewById(R.id.from_login_to_waiting_room_page);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);


        goToRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserRegisterActivity.class));
            }
        });

        goToLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = userEmail.getText().toString();
                final String password = userPassword.getText().toString();

                UserAPI userAPI = RequestUtil.createService(UserAPI.class);
                Call<Long> response = userAPI.findUserByEmailAndPassword(new UserLoginRequestDto(email, password));
                response.enqueue(new Callback<Long>() {
                    @Override
                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if (response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), WaitingRoomActivity.class);
                            System.out.println(response.body());
                            intent.putExtra("userId", response.body());
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Long> call, Throwable t) {

                    }
                });

            }
        });

    }
}
