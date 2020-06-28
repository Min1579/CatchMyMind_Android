package com.min.catchmymind_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.min.catchmymind_android.api.UserAPI;
import com.min.catchmymind_android.dto.User;
import com.min.catchmymind_android.util.CustomCallback;
import com.min.catchmymind_android.util.RequestUtil;

import retrofit2.Call;
import retrofit2.Response;

public class UserRegisterActivity extends AppCompatActivity {

    private Button goToLoginPage, sendDataToServer;
    private EditText emailInput, nameInput, pwdInput, pwdChkInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_register);

        goToLoginPage = findViewById(R.id.from_register_to_login_page);
        sendDataToServer = findViewById(R.id.from_register_send_data_and_to_login_page);

        emailInput = findViewById(R.id.emailInput);
        nameInput = findViewById(R.id.nameInput);
        pwdInput = findViewById(R.id.pwdInput);
        pwdChkInput = findViewById(R.id.pwdChkInput);


        goToLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });


        sendDataToServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString();
                final String name = nameInput.getText().toString();
                String pwd = pwdInput.getText().toString();
                String pwdChk = pwdChkInput.getText().toString();

                if (!email.contains("@") || email.length() < 10) {
                    Toast.makeText(getApplicationContext(), "email 형식이 정확하지 않아요!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (name.length() < 2) {
                    Toast.makeText(getApplicationContext(), "이름 최소 2글자 이상 적어주세요 ", Toast.LENGTH_LONG).show();
                    return;
                }

                if (!pwd.equals(pwdChk)) {
                    Toast.makeText(getApplicationContext(), "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                    return;
                }
                UserAPI userAPI = RequestUtil.createService(UserAPI.class);
                Call<Long> response = userAPI.userRegister(new User(email, name, pwd));
                response.enqueue(new CustomCallback<Long>() {
                    @Override

                    public void onResponse(Call<Long> call, Response<Long> response) {
                        if (response.isSuccessful()) {
                            Toast successToast = Toast.makeText(getApplicationContext(), String.format("%s님 로그인 해주세요!", name), Toast.LENGTH_LONG);
                            successToast.show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast successToast = Toast.makeText(getApplicationContext(), "네트워크 오류가 있습니다. 다시 시도해 주세요!", Toast.LENGTH_LONG);
                            successToast.show();
                        }
                    }
                });
            }
        });
    }


}
