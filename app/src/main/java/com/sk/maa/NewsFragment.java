package com.sk.maa;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.sk.maa.pojo.Notice;
import com.sk.maa.pojo.ResultNoticeData;

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
public class NewsFragment extends Fragment {
    private ListView listView;
    private MyAdapter myAdapter;
    private static ArrayList<Notice> notices = new ArrayList<>();
    private View view;
    private Retrofit retrofit ;
    private ApiService apiService ;
    private Handler mHandler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what) {
                case 200:
                    noticeInit();
                    noticeSuccess(msg.obj);
                    break;
                case 500:
                    noticeFail(msg.obj);
                    break;
            }
        }
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        InitData();
        InitView();
        return view;
    }

    private void InitView() {
        listView = view.findViewById(R.id.id_listview_news);
        myAdapter = new MyAdapter();
        getNoticeLink(1,10);
        listView.setAdapter(myAdapter);
    }
    private void InitData() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Gloabl.i.baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    private void noticeInit() {
        InitView();
    }
    private void getNoticeLink(Integer pageNum, Integer pageSize) {
        HashMap<String, Integer> map=new HashMap<>();
        map.put("pageNum",pageNum);
        map.put("pageSize",pageSize);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> header = new HashMap<>();
                header.put("Content-Type","application/json");
                header.put("Authorization",Gloabl.i.authorizationType + Gloabl.i.token);
                Call<ResponseBody> notice = apiService.notice(header, map);
                notice.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        ResponseBody body = response.body();
                        try {
                            Gson gson=new Gson();
                            ResultNoticeData resultData = gson.fromJson(body.string(), ResultNoticeData.class);
                            if (resultData != null && resultData.getCode() == 200){
                                notices = resultData.getRows();
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
    private void noticeSuccess(Object msg) {
        //Toast.makeText(view.getContext(), "获取成功", Toast.LENGTH_SHORT).show();
        //startActivity(new Intent(this,HomeActivity.class));
    }
    private void noticeFail(Object msg) {
        Toast.makeText(view.getContext(), msg.toString(), Toast.LENGTH_SHORT).show();
    }
    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return notices.size();
        }

        @Override
        public Notice getItem(int position) {
            return notices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.listview_news_item,parent,false);
            }
            Notice item = getItem(position);
            ImageView imageView = convertView.findViewById(R.id.id_icon);
            TextView textView = convertView.findViewById(R.id.id_text);

            imageView.setImageDrawable(getResources().getDrawable(R.drawable.baseline_error_24));
            textView.setText(item.getNoticeTitle().toString());
            return convertView;
        }
    }

}
