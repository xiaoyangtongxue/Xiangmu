package com.example.bwie.xiangmu.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu.R;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xsj on 2017/12/17.
 */

public class MeFg extends Fragment {

    @BindView(R.id.sdv_head_image)
    SimpleDraweeView sdvHeadImage;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.melayout, null);
        unbinder = ButterKnife.bind(this, view);

        sdvHeadImage.setImageURI(Uri.parse("res://com.example.xsj.shipei.Adapters/" + R.mipmap.save));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.sdv_head_image)
    public void onViewClicked() {
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

         // 设定结果返回
        startActivityForResult(i, 2);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImage = data.getData();
        if(selectedImage!=null){
            sdvHeadImage.setImageURI(selectedImage);
        }
    }


}
