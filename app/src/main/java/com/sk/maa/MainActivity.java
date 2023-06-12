package com.sk.maa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sk.maa.pojo.ResultCheckCodeData;
import com.sk.maa.pojo.ResultLoginData;
import com.sk.maa.utils.LoginUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Button id_login,id_register;
    private EditText id_name,id_pwd,id_code;
    private ImageButton ib_code;
    private CheckBox id_cb;
    private SharedPreferences remember;
    private SharedPreferences.Editor editor;
    private Retrofit retrofit ;
    private ApiService apiService ;
    public static String Token;
    private String UUID;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    LoginSuccess(msg.obj);
                    break;
                case 500:
                    LoginFail(msg.obj);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remember = getSharedPreferences("share_login", MODE_PRIVATE);
        Reload();
        InitData();
        InitView();
    }

    private void InitData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCaptchaImage();
            }
        }).start();
    }

    private void InitView() {
        id_name = findViewById(R.id.id_name);
        id_pwd = findViewById(R.id.id_pwd);
        id_login = findViewById(R.id.id_login);
        id_register = findViewById(R.id.id_register);
        ib_code = findViewById(R.id.ib_code);
        id_code = findViewById(R.id.id_code);
        id_cb = findViewById(R.id.id_cb);
    }

    private void getCaptchaImage(){
        Call<ResponseBody> captchaImage = apiService.captchaImage();
        captchaImage.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                try {
                    Gson gson=new Gson();
                    ResultCheckCodeData resultData = gson.fromJson(body.string(), ResultCheckCodeData.class);
                    if (resultData != null && resultData.getCode() == 200){
                        ib_code.setImageBitmap(LoginUtil.stringToBitmap(resultData.getImg(),600,200));
                        UUID = resultData.getUuid();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    private void getLoginLink(String name, String pwd, String code) {
        Map map=new HashMap();
        map.put("username",name);
        map.put("password",pwd);
        map.put("code", code);
        map.put("uuid", UUID);
        String body = new Gson().toJson(map);
        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), body);
                Call<ResponseBody> login = apiService.login(requestBody);
                login.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultLoginData resultData = gson.fromJson(body.string(), ResultLoginData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                Token = resultData.getToken();
                                Message msg = new Message();
                                msg.what = resultData.getCode();
                                msg.obj = resultData.getMsg();
                                mHandler.sendMessage(msg);
                            }else {
                                Message msg = new Message();
                                msg.what = resultData.getCode();
                                msg.obj = resultData.getMsg();
                                mHandler.sendMessage(msg);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        }).start();
    }
    private void Reload() {
        String name = remember.getString("name", "");
        String pwd = remember.getString("pwd", "");
        boolean pwds = remember.getBoolean("cb_pwd", false);
        if (pwds){
            id_name.setText(name);
            id_pwd.setText(pwd);
            id_cb.setChecked(true);
        }
    }
    public void doRegister(View view) {
        finish();
    }
    private void LoginSuccess(Object msg) {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,HomeActivity.class));
    }
    private void LoginFail(Object msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }

    public void doLogin(View view) {
        String name = id_name.getText().toString().trim();
        String pwd = id_pwd.getText().toString().trim();
        String code = id_code.getText().toString().trim();
        if (name.isEmpty()||pwd.isEmpty()){
            Toast.makeText(this, "用户名或密码不得为空!", Toast.LENGTH_SHORT).show();
        }else {
            getLoginLink(name,pwd,code);
        }
    }

    public void doRefresh(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                getCaptchaImage();
            }
        }).start();
    }
}