package com.example.bwie.xiangmu.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bwie.xiangmu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageActivity extends AppCompatActivity {

    @BindView(R.id.sdv_head_image)
    SimpleDraweeView sdvHeadImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        getSupportActionBar().hide();
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        sdvHeadImage.setImageURI(url);
    }
}
