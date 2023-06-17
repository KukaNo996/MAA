package com.sk.maa;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.sk.maa.pojo.PGItem;
import com.sk.maa.pojo.ResultPGData;
import com.sk.maa.pojo.ResultSiteAddressData;
import com.sk.maa.pojo.SiteInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private View view;
    private ListView id_right,id_left;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;
    private static ArrayList<PGItem> pgItems = new ArrayList<>();
    private static ArrayList<SiteInfo> siteInfoItems = new ArrayList<>();
    private static ArrayList<Integer> siteIds = new ArrayList<>();
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    InitView();
                    LinkSuccess(msg.obj);
                    break;
                case 500:
                    LinkFail(msg.obj);
                    break;
            }
        }
    };

    private void LinkSuccess(Object obj) {

    }

    private void LinkFail(Object obj) {
        Toast.makeText(view.getContext(), obj.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main, container, false);
        InitData();
        InitView();
        return view;
    }

    private void InitData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Gloabl.i.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
        getPGItemLink(1,10);
        getSiteInfoItemLink(1);
    }
    private void InitView() {
        id_left = view.findViewById(R.id.id_left);
        id_right = view.findViewById(R.id.id_right);
        leftAdapter = new LeftAdapter();
        rightAdapter = new RightAdapter();
        id_left.setAdapter(leftAdapter);
        id_left.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getSiteInfoItemLink(position+1);
            }
        });
        id_right.setAdapter(rightAdapter);
    }

    private void getPGItemLink(Integer pageNum, Integer pageSize) {
        HashMap<String, Integer> map=new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> pgItem = apiService.PGItem(header, map);
                pgItem.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultPGData resultData = gson.fromJson(body.string(), ResultPGData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                pgItems = (ArrayList<PGItem>) resultData.getRows();
                                for (int i = 0; i < pgItems.size(); ++i) {
                                    siteIds.add(pgItems.get(i).getId());
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
    private void getSiteInfoItemLink(Integer type){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> siteInfoItem = apiService.SiteInfoItem(header,type);
                siteInfoItem.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultSiteAddressData resultData = gson.fromJson(body.string(), ResultSiteAddressData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                siteInfoItems = new ArrayList<>();
                                for (int i = 0; i < resultData.getRows().size(); i++) {
                                    siteInfoItems.add(resultData.getRows().get(i));
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

    private class LeftAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pgItems.size();
        }

        @Override
        public PGItem getItem(int position) {
            return pgItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.listview_left_item,parent,false);
            }
            PGItem item = getItem(position);
            TextView textView = convertView.findViewById(R.id.id_text);
            textView.setText(item.getName().toString());
            return convertView;
        }
    }
    private class RightAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return siteInfoItems.size();
        }

        @Override
        public SiteInfo getItem(int position) {
            return siteInfoItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.listview_right_item,parent,false);
            }
            SiteInfo item = getItem(position);
            TextView textView = convertView.findViewById(R.id.id_text);
            textView.setText(item.getAddress().toString());
            return convertView;
        }
    }
}
