package com.example.bwie.xiangmu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.bwie.xiangmu.Fragments.FleadFg;
import com.example.bwie.xiangmu.Fragments.HomeFg;
import com.example.bwie.xiangmu.Fragments.MeFg;
import com.example.bwie.xiangmu.View.ColorActivity;
import com.hjm.bottomtabbar.BottomTabBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.huanse)
    LinearLayout huanse;
    @BindView(R.id.rela)
    RelativeLayout rela;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        ButterKnife.bind(this);



        //注册EventBus
        EventBus.getDefault().register(this);

        //获取颜色
        SharedPreferences user = getSharedPreferences("User", MODE_PRIVATE);
        int selectcolor = user.getInt("selectcolor", 0);
        if(selectcolor!=0){
            rela.setBackgroundColor(selectcolor);
        }

        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        bottomTabBar.init(getSupportFragmentManager())
                .setImgSize(30, 30)
                .setFontSize(12)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.BLUE, Color.DKGRAY)
                .addTabItem("Home", R.mipmap.bottom_home, HomeFg.class)
                .addTabItem("Read", R.mipmap.bottom_read, FleadFg.class)
                .addTabItem("Me", R.mipmap.bottom_me, MeFg.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {

                    }
                });

    }

    @OnClick(R.id.huanse)
    public void onViewClicked() {
        Intent intent = new Intent(MainActivity.this, ColorActivity.class);
        startActivity(intent);
    }

    @Subscribe
    public void onEventMainThread(Object event) {
        if (event != null) {
            int color = (int) event;
            if (color != 0) {
                rela.setBackgroundColor(color);

            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}

