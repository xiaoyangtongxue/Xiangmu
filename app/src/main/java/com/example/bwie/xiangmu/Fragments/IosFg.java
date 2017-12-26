package com.example.bwie.xiangmu.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu.Adapters.AndroidAdapter;
import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.Presenter.HttpPresenter;
import com.example.bwie.xiangmu.R;
import com.example.bwie.xiangmu.View.Iview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xsj on 2017/12/17.
 */

public class IosFg extends Fragment implements Iview {

    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    Unbinder unbinder;
    private HttpPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ioslayout, null);
        unbinder = ButterKnife.bind(this, view);

        presenter = new HttpPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        presenter.getMap(map,"IOS");

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xrecy.setLayoutManager(manager);
        return view;
    }

    @Override
    public void OnSuccess(Object o, String tag) {

        if(tag.equals("IOS")) {
            AndroidBean androidBean = (AndroidBean) o;
            final List<AndroidBean.ResultsBean> results = androidBean.getResults();

            xrecy.setAdapter(new AndroidAdapter(results, getActivity()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.disattach();
    }
}
