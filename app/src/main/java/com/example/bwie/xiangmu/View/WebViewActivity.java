package com.example.bwie.xiangmu.View;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bwie.xiangmu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.rela)
    RelativeLayout rela;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        //获取颜色
        SharedPreferences user = getSharedPreferences("User", MODE_PRIVATE);
        int selectcolor = user.getInt("selectcolor", 0);
        if(selectcolor!=0){
            rela.setBackgroundColor(selectcolor);
        }

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        webview.loadUrl(url);
    }

    @OnClick(R.id.im_back)
    public void onViewClicked() {
        finish();
    }
}
