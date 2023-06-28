package com.sk.maa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sk.maa.pojo.ResultSiteReservationInfoData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SiteActivity extends AppCompatActivity {
    private ImageButton id_time_1,id_time_2,id_time_3,id_time_4,id_time_5,id_time_6,id_time_7,id_time_8,id_time_9,id_time_10,id_time_11,id_time_12;
    private Integer siteId;
    private String address;
    private TextView id_address;
    private boolean[] time = new boolean[]{false,false,false,false,false,false,false,false,false,false,false,false};
    private Map<String, Integer> data = new HashMap<>();
    private Map<Integer,ImageButton> imageButtonMap = new HashMap<>();
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    InitView();
                    break;
                case 500:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        InitData();
    }

    private void InitView() {
        id_time_1 = findViewById(R.id.id_time_1);
        id_time_2 = findViewById(R.id.id_time_2);
        id_time_3 = findViewById(R.id.id_time_3);
        id_time_4 = findViewById(R.id.id_time_4);
        id_time_5 = findViewById(R.id.id_time_5);
        id_time_6 = findViewById(R.id.id_time_6);
        id_time_7 = findViewById(R.id.id_time_7);
        id_time_8 = findViewById(R.id.id_time_8);
        id_time_9 = findViewById(R.id.id_time_9);
        id_time_10 = findViewById(R.id.id_time_10);
        id_time_11 = findViewById(R.id.id_time_11);
        id_time_12 = findViewById(R.id.id_time_12);
        id_address = findViewById(R.id.id_address);
        imageButtonMap.put(1, id_time_1);
        imageButtonMap.put(2, id_time_2);
        imageButtonMap.put(3, id_time_3);
        imageButtonMap.put(4, id_time_4);
        imageButtonMap.put(5, id_time_5);
        imageButtonMap.put(6, id_time_6);
        imageButtonMap.put(7, id_time_7);
        imageButtonMap.put(8, id_time_8);
        imageButtonMap.put(9, id_time_9);
        imageButtonMap.put(10, id_time_10);
        imageButtonMap.put(11, id_time_11);
        imageButtonMap.put(12, id_time_12);
        if (address != null && address != ""){
            id_address.setText(address);
        }
        id_time_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time[1] == false){
                    id_time_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                    time[1] = true;
                }
                id_time_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[1] = false;
            }
        });
}

    private void InitData() {
        Intent intent = getIntent();
        siteId = intent.getIntExtra("siteId", 1);
        address = intent.getStringExtra("address");
        retrofit = new Retrofit.Builder()
                .baseUrl(Gloabl.i.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        getSiteReservationInfoLink(siteId);
    }

    private void getSiteReservationInfoLink(Integer siteId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> siteReservationInfo = apiService.getSiteReservationInfo(header, siteId);
                siteReservationInfo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultSiteReservationInfoData resultData = gson.fromJson(body.string(), ResultSiteReservationInfoData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                data = resultData.getData();
                                if (data != null && data.size() != 0 ){
                                    for (int i = 1; i <= data.size(); i++) {
                                        Integer integer = data.get("1");
                                        integer = 1;
                                        if (data.get(i+"") != 0){
                                            ImageButton imageButton = imageButtonMap.get(i);
                                            InitView();
                                        }
                                    }
                                }

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

    public void Cancel(View view) {
        finish();
    }

    public void SubmitData(View view) {

    }
}