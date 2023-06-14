package com.sk.maa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sk.maa.pojo.ResultData;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeclassifyActivity extends AppCompatActivity {
    private EditText id_old_pwd,id_new_pwd,id_qrmm;
    private Button id_submit;
    private TextView id_username_setting;
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    updateSuccess(msg.obj);
                    break;
                case 500:
                    updateFail(msg.obj);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declassify);
        InitData();
        InitView();
    }

    private void InitView() {
        id_old_pwd = findViewById(R.id.id_old_pwd);
        id_new_pwd = findViewById(R.id.id_new_pwd);
        id_qrmm = findViewById(R.id.id_qrmm);
        id_submit = findViewById(R.id.id_submit);
        id_username_setting = findViewById(R.id.id_username_setting);
    }
    private void InitData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Gloabl.i.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void Submit(View view) {
        String oldpwd = id_old_pwd.getText().toString();
        String newpwd = id_new_pwd.getText().toString();
        String qrmm = id_qrmm.getText().toString();
        if (oldpwd.isEmpty()|| newpwd.isEmpty()||qrmm.isEmpty()){
            Toast.makeText(this, "旧密码、新密码或确认密码不得为空!", Toast.LENGTH_SHORT).show();
        }else {
            if (!newpwd.equals(qrmm)){
                Toast.makeText(this, "确认密码与新密码不一致，请修改后重试!", Toast.LENGTH_SHORT).show();
            }
            getDeclassifyLink(oldpwd,newpwd);
        }
    }

    private void getDeclassifyLink(String oldpwd, String newpwd) {
        HashMap<String, String> map=new HashMap<String, String>();
        map.put("oldPassword",oldpwd);
        map.put("newPassword",newpwd);

        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> update = apiService.update(header, map);
                update.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultData resultData = gson.fromJson(body.string(), ResultData.class);
                            if (resultData != null && resultData.getCode() == 200){
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
    private void updateSuccess(Object msg) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,HomeActivity.class));
    }
    private void updateFail(Object msg) {
        Toast.makeText(this, msg.toString(), Toast.LENGTH_SHORT).show();
    }
    public void SettingExit(View view) {
        finish();
    }
}