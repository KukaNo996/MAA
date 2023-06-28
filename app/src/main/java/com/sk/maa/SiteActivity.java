package com.sk.maa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.sk.maa.pojo.ResultData;
import com.sk.maa.pojo.ResultSiteReservationInfoData;
import com.sk.maa.utils.MAAUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
    private ArrayList<Integer> maaData = new ArrayList<>();
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    InitView();
                    SubmitSuccess(msg.obj);
                    break;
                case 500:
                    SubmitFail(msg.obj);
                    break;
                case 601:
                    SubmitFail(msg.obj);
                    break;
            }
        }
    };

    private void SubmitFail(Object obj) {
        Toast.makeText(this, ""+obj, Toast.LENGTH_SHORT).show();
    }

    private void SubmitSuccess(Object obj) {
        if (obj.equals("预约成功")) {
            Toast.makeText(this, ""+obj, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_site);
        InitData();
        InitView();
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
                                        if (data.get(i+"") != 0){
                                            ImageButton imageButton = imageButtonMap.get(i);
                                            imageButton.setEnabled(false);
                                            imageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_jinyong));
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
    private void getCommitDataLink(Integer siteId,ArrayList timeIds){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> siteReservationInfo = apiService.submitData(header, siteId, MAAUtil.arrayListToString(maaData));
                siteReservationInfo.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultData resultData = gson.fromJson(body.string(), ResultData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                getSiteReservationInfoLink(siteId);
                                maaData.clear();
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
        Collections.sort(maaData);
        getCommitDataLink(siteId, maaData);
    }

    public void Click(View view) {
        if (view.getId() == R.id.id_time_1) {
            if (time[0] == false) {
                id_time_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[0] = true;
                maaData.add(1);
                InitView();
            } else {
                id_time_1.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[0] = false;
                maaData.removeAll(Collections.singleton(1));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_2) {
            if (time[1] == false) {
                id_time_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[1] = true;
                maaData.add(2);
                InitView();
            } else {
                id_time_2.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[1] = false;
                maaData.removeAll(Collections.singleton(2));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_3) {
            if (time[2] == false) {
                id_time_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[2] = true;
                maaData.add(3);
                InitView();
            } else {
                id_time_3.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[2] = false;
                maaData.removeAll(Collections.singleton(3));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_4) {
            if (time[3] == false) {
                id_time_4.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[3] = true;
                maaData.add(4);
                InitView();
            } else {
                id_time_4.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[3] = false;
                maaData.removeAll(Collections.singleton(4));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_5) {
            if (time[4] == false) {
                id_time_5.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[4] = true;
                maaData.add(5);
                InitView();
            } else {
                id_time_5.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[4] = false;
                maaData.removeAll(Collections.singleton(5));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_6) {
            if (time[5] == false) {
                id_time_6.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[5] = true;
                maaData.add(6);
                InitView();
            } else {
                id_time_6.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[5] = false;
                maaData.removeAll(Collections.singleton(6));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_7) {
            if (time[6] == false) {
                id_time_7.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[6] = true;
                maaData.add(7);
                InitView();
            } else {
                id_time_7.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[6] = false;
                maaData.removeAll(Collections.singleton(7));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_8) {
            if (time[7] == false) {
                id_time_8.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[7] = true;
                maaData.add(8);
                InitView();
            } else {
                id_time_8.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[7] = false;
                maaData.removeAll(Collections.singleton(8));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_9) {
            if (time[8] == false) {
                id_time_9.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[8] = true;
                maaData.add(9);
                InitView();
            } else {
                id_time_9.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[8] = false;
                maaData.removeAll(Collections.singleton(9));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_10) {
            if (time[9] == false) {
                id_time_10.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[9] = true;
                maaData.add(10);
                InitView();
            } else {
                id_time_10.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[9] = false;
                maaData.removeAll(Collections.singleton(10));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_11) {
            if (time[10] == false) {
                id_time_11.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[10] = true;
                maaData.add(11);
                InitView();
            } else {
                id_time_11.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[10] = false;
                maaData.removeAll(Collections.singleton(11));
                InitView();
            }
        }
        if (view.getId() == R.id.id_time_12) {
            if (time[11] == false) {
                id_time_12.setImageDrawable(getResources().getDrawable(R.drawable.ic_xuanzhong));
                time[11] = true;
                maaData.add(12);
                InitView();
            } else {
                id_time_12.setImageDrawable(getResources().getDrawable(R.drawable.ic_weixuanzhong));
                time[11] = false;
                maaData.removeAll(Collections.singleton(12));
                InitView();
            }
        }
    }
}