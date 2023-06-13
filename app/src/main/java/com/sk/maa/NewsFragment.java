package com.sk.maa;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sk.maa.pojo.News;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private ListView listView;
    private MyAdapter myAdapter;
    private ArrayList<News> newsArrayList = new ArrayList<>();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        InitView();
        return view;
    }

    private void InitView() {
        InitData();
        listView = view.findViewById(R.id.id_listview_news);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    private void InitData() {
        News news = new News();
        news.setIcon(getResources().getDrawable(R.drawable.baseline_error_24));
        news.setText("当前APP未上线,请勿进行支付操作!");
        newsArrayList.add(news);
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return newsArrayList.size();
        }

        @Override
        public News getItem(int position) {
            return newsArrayList.get(position);
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
            News item = getItem(position);
            ImageView imageView = convertView.findViewById(R.id.id_icon);
            TextView textView = convertView.findViewById(R.id.id_text);

            imageView.setImageDrawable(item.getIcon());
            textView.setText(item.getText().toString());
            return convertView;
        }
    }

}
