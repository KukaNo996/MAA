package com.sk.maa;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sk.maa.pojo.ResultData;
import com.sk.maa.pojo.ResultUserInfoData;
import com.sk.maa.pojo.User;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MPIActivity extends AppCompatActivity {
    private EditText id_username,id_phone,id_email;
    private RadioGroup id_sex;
    private RadioButton id_mail,id_femail;
    private static User user;
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    UpdataSuccess(msg.obj);
                    break;
                case 500:
                    UpdataFail(msg.obj);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpiactivity);
        InitView();
        InitData();
    }

    private void InitView() {
        id_username = findViewById(R.id.id_username);
        id_phone = findViewById(R.id.id_phone);
        id_email = findViewById(R.id.id_email);
        id_sex = findViewById(R.id.id_sex);
        id_mail = findViewById(R.id.id_mail);
        id_femail = findViewById(R.id.id_femail);
    }

    public void Submit(View view) {
        String username = id_username.getText().toString().trim();
        String phone = id_phone.getText().toString().trim();
        String email = id_email.getText().toString().trim();
        String sex = "0";
        if (id_mail.isChecked()){
            getUpdateUserInfoLink(username,phone,email,sex);
        }else {
            sex = "1";
            getUpdateUserInfoLink(username,phone,email,sex);
        }
    }

    private void getUpdateUserInfoLink(String username, String phone, String email, String sex) {
        user.setUserName(username);
        user.setPhonenumber(phone);
        user.setEmail(email);
        user.setSex(sex);
        String body = new Gson().toJson(user);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),body);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> updateUserInfo = apiService.updateUserInfo(header, requestBody);
                updateUserInfo.enqueue(new Callback<ResponseBody>() {
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

    private void InitData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Gloabl.i.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        getUserInfo();
    }

    private void getUserInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> userInfo = apiService.userInfo(header);
                userInfo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultUserInfoData resultData = gson.fromJson(body.string(), ResultUserInfoData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                user = resultData.getUser();
                                id_username.setText(resultData.getUser().getUserName());
                                id_phone.setText(resultData.getUser().getPhonenumber());
                                id_email.setText(resultData.getUser().getEmail());
                                if (resultData.getUser().getSex().equals("0")){
                                    id_mail.setChecked(true);
                                }else {
                                    id_femail.setChecked(true);
                                }
//                                Message msg = new Message();
//                                msg.what = resultData.getCode();
//                                msg.obj = resultData.getMsg();
//                                mHandler.sendMessage(msg);
                            }else {
//                                Message msg = new Message();
//                                msg.what = resultData.getCode();
//                                msg.obj = resultData.getMsg();
//                                mHandler.sendMessage(msg);
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
    private void UpdataSuccess(Object obj) {
        Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void UpdataFail(Object obj) {
        Toast.makeText(this, obj.toString(), Toast.LENGTH_SHORT).show();
    }
    public void InformationExit(View view) {
        finish();
    }
}