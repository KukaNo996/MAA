package com.sk.maa;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.sk.maa.pojo.My;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    private ListView listView;
    private MyAdapter myAdapter;
    private ArrayList<My> myArrayList = new ArrayList<>();
    private View view;
    private TextView id_my_name;

    private Context mContext = getContext();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my, container, false);
        InitView();
        return view;
    }

    private void InitView() {
        id_my_name = view.findViewById(R.id.id_my_name);
        myArrayList.clear();
        InitData();
        listView = view.findViewById(R.id.id_listview_my);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
//                        intent = new Intent(view.getContext(),SettingActivity.class);
//                        startActivity(intent);
                        break;
                    case 1:
//                        intent = new Intent(view.getContext(),InformationActivity.class);
//                        startActivity(intent);
                        break;
                    case 2:
//                        startActivity(new Intent());
                        break;
                    case 3:
//                        startActivity(new Intent());
                        break;
                    case 4:
//                        startActivity(new Intent());
                        break;
                }
            }
        });
    }

    private void InitData() {

        My my = new My();
        my.setIcon(getResources().getDrawable(R.drawable.ic_pwd));
        my.setText("修改密码");
        myArrayList.add(my);

        my = new My();
        my.setIcon(getResources().getDrawable(R.drawable.ic_userinfo));
        my.setText("修改个人信息");
        myArrayList.add(my);

        my = new My();
        my.setIcon(getResources().getDrawable(R.drawable.ic_yuyue));
        my.setText("我的预约");
        myArrayList.add(my);

        my = new My();
        my.setIcon(getResources().getDrawable(R.drawable.ic_tuikuan));
        my.setText("我的退款");
        myArrayList.add(my);

        my = new My();
        my.setIcon(getResources().getDrawable(R.drawable.ic_dingdan));
        my.setText("我的订单");
        myArrayList.add(my);

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myArrayList.size();
        }

        @Override
        public My getItem(int position) {
            return myArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.listview_my_item,parent,false);
            }
            My item = getItem(position);
            ImageView imageView = convertView.findViewById(R.id.id_icon);
            TextView textView = convertView.findViewById(R.id.id_text);

            imageView.setImageDrawable(item.getIcon());
            textView.setText(item.getText().toString());
            return convertView;
        }
    }
}
