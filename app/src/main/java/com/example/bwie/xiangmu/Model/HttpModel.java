package com.example.bwie.xiangmu.Model;


import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.Presenter.Pview;
import com.example.bwie.xiangmu.Utils.HttpUtils;

import java.util.Map;

import io.reactivex.Flowable;


/**
 * Created by xsj on 2017/12/14.
 */

public class HttpModel {
    private Pview pv;

    public HttpModel(Pview pv) {
        this.pv = pv;
    }

    public void getApi(Map<String,String> map, String tag){

        if(tag.equals("Android")){
            Flowable<AndroidBean> androidData = new HttpUtils().getApiNet().getAndroidData(map);
            pv.getData(androidData,tag);
        }

        if(tag.equals("IOS")){
            Flowable<AndroidBean> androidData = new HttpUtils().getApiNet().getIOSData(map);
            pv.getData(androidData,tag);
        }

        if(tag.equals("福利")){
            Flowable<AndroidBean> androidData = new HttpUtils().getApiNet().getFuLiData(map);
            pv.getData(androidData,tag);
        }

        if(tag.equals("瞎推荐")){
            Flowable<AndroidBean> androidBeanFlowable = new HttpUtils().getApiNet().gettuijianData(map);
            pv.getData(androidBeanFlowable,tag);
        }
    }
}
