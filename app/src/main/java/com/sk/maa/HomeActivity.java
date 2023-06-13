package com.sk.maa;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;//用于加载Fragment的容器控件
    private FragmentAdapter fragmentAdapter;//适配器
    private Fragment[] fragment = new Fragment[3];//数据源
    private TextView textViewtab1,textViewtab2,textViewtab3;
    private ImageButton imageButtontab1,imageButtontab2,imageButtontab3;
    private TextView[] textViewtabs = new TextView[3];//页签
    private ImageButton[] imageButtontabs = new ImageButton[3];
    private Drawable[] ImageFalse = new Drawable[3];
    private Drawable[] ImageTrue = new Drawable[3];
    private TextView id_textView_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Reload();
        InitView();
    }

    public void Reload() {
        Intent intent = getIntent();
        boolean message = intent.getBooleanExtra("Message", false);
        if (message){
            InitView();
        }
    }

    private void InitView() {
        Init();
        id_textView_name = findViewById(R.id.id_textView_name);
        fragment[0] = new MainFragment();
        fragment[1] = new NewsFragment();
        fragment[2] = new MyFragment();

        textViewtabs[0] = textViewtab1;
        textViewtabs[1] = textViewtab2;
        textViewtabs[2] = textViewtab3;

        imageButtontabs[0] = imageButtontab1;
        imageButtontabs[1] = imageButtontab2;
        imageButtontabs[2] = imageButtontab3;

        ImageFalse[0] = getResources().getDrawable(R.drawable.ic_home_false);
        ImageFalse[1] = getResources().getDrawable(R.drawable.ic_gg_false);
        ImageFalse[2] = getResources().getDrawable(R.drawable.ic_my_false);

        ImageTrue[0] = getResources().getDrawable(R.drawable.ic_home_true);
        ImageTrue[1] = getResources().getDrawable(R.drawable.ic_gg_true);
        ImageTrue[2] = getResources().getDrawable(R.drawable.ic_my_true);

        for (int i = 0; i < textViewtabs.length; i++) {
            final int finalI = i;
            textViewtabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPageChangeMethod(finalI,v);
                }
            });
        }
        for (int i = 0; i < imageButtontabs.length; i++) {
            final int finalI = i;
            imageButtontabs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPageChangeMethod(finalI,v);
                }
            });
        }
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (TextView tab : textViewtabs) {
                    tab.setTextColor(getColor(R.color.BarFalse));
                }
                textViewtabs[position].setTextColor(getColor(R.color.BarTrue));
                for (int i = 0; i < imageButtontabs.length; i++) {
                    imageButtontabs[i].setImageDrawable(ImageFalse[i]);
                }
                imageButtontabs[position].setImageDrawable(ImageTrue[position]);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void onPageChangeMethod(int finalI, View v) {
        viewPager.setCurrentItem(finalI);//切换到目标页

        //排他思想 排除所有的包括本身 再给自身设置对应的样式
        for (TextView tab : textViewtabs) {
            tab.setTextColor(getColor(R.color.BarFalse));
        }
        textViewtabs[finalI].setTextColor(getColor(R.color.BarTrue));

        for (int i = 0; i < imageButtontabs.length; i++) {
            imageButtontabs[i].setImageDrawable(ImageFalse[i]);
        }
        imageButtontabs[finalI].setImageDrawable(ImageTrue[finalI]);
    }

    private void Init() {
        drawerLayout = findViewById(R.id.id_layout);
        viewPager = findViewById(R.id.id_viewpages);
        textViewtab1 = findViewById(R.id.textViewtab1);
        textViewtab2 = findViewById(R.id.textViewtab2);
        textViewtab3 = findViewById(R.id.textViewtab3);
        imageButtontab1 = findViewById(R.id.imageButtontab1);
        imageButtontab2 = findViewById(R.id.imageButtontab2);
        imageButtontab3 = findViewById(R.id.imageButtontab3);
    }

    public void Open(View view) {
        //找到侧滑菜单的布局

        //打开侧滑菜单
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public void Close(View view){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void Collect(View view) {
        //Intent intent = new Intent(this,CollectActivity.class);
        //startActivity(intent);
    }

    public void Updata(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("当前APP已经是最新版本")
                .setMessage("当前APP的版本为Android_Version 1.0.0,暂无可用更新!")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//关闭对话框
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(R.mipmap.ic_appimg);
        builder.show();    }

    public void About(View view) {
//        Intent intent = new Intent(this, AboutActivity.class);
//        startActivity(intent);
    }

    public void Setting(View view) {
//        Intent intent = new Intent(this, SettingActivity.class);
//        startActivity(intent);
    }

    public void Loginout(View view) {
        finish();
    }

    public void Add(View view) {
//        startActivity(new Intent(this,AddActivity.class));
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment[position];
        }

        @Override
        public int getCount() {
            return fragment.length;
        }
    }
}