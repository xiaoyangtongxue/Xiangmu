package com.example.bwie.xiangmu;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuidanceActivity extends AppCompatActivity {

    private Boolean A = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidance);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (A == true) {
                    Intent intent = new Intent(GuidanceActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        A = false;
    }

    @OnClick(R.id.im)
    public void onViewClicked() {
        Intent intent = new Intent(GuidanceActivity.this, MainActivity.class);
        startActivity(intent);
        A=false;
        finish();
    }
}
