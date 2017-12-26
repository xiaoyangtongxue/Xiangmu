package com.example.bwie.xiangmu.Presenter;

import android.util.Log;

import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.Model.HttpModel;
import com.example.bwie.xiangmu.View.Iview;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by xsj on 2017/12/14.
 */

public class HttpPresenter  implements  Pview {

    private Iview iv;
    private DisposableSubscriber<AndroidBean> p1;

    public HttpPresenter(Iview iv) {
        this.iv = iv;
    }

    public void getMap(Map<String, String> map, String tag) {
        HttpModel httpModel = new HttpModel(this);
        httpModel.getApi(map, tag);

    }


    @Override
    public void getData(Flowable<AndroidBean> flowable, final String tag) {

        p1 = flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AndroidBean>() {
                    @Override
                    public void onNext(AndroidBean androidBean) {
                        Log.e("TAG","请求成功"+androidBean);
                        iv.OnSuccess(androidBean, tag);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.e("TAG","请求失败"+t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void disattach(){
        if(iv!=null){
            iv=null;
        }
        if(p1!=null){
            if(p1.isDisposed()){
                p1.dispose();
            }
        }
    }
}
