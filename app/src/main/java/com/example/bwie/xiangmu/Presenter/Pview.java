package com.example.bwie.xiangmu.Presenter;

import com.example.bwie.xiangmu.Beans.AndroidBean;

import io.reactivex.Flowable;

/**
 * Created by xsj on 2017/12/14.
 */

public interface Pview {
    void getData(Flowable<AndroidBean> flowable, String tag);
}
