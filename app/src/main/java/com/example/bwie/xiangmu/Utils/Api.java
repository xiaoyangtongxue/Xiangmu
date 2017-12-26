package com.example.bwie.xiangmu.Utils;

import com.example.bwie.xiangmu.Beans.AndroidBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Administrator on 2017/12/19.
 */

public interface Api {
    @GET("data/Android/10/1")
    Flowable<AndroidBean> getAndroidData(@QueryMap Map<String, String> map);

    @GET("data/iOS/10/1")
    Flowable<AndroidBean> getIOSData(@QueryMap Map<String, String> map);

    @GET("data/福利/10/1")
    Flowable<AndroidBean> getFuLiData(@QueryMap Map<String, String> map);

    @GET("data/瞎推荐/10/1")
    Flowable<AndroidBean> gettuijianData(@QueryMap Map<String, String> map);
}
