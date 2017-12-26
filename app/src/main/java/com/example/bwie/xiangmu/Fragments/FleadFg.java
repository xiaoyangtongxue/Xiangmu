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

/**
 * Created by xsj on 2017/12/17.
 */

public class FleadFg extends Fragment  implements Iview {

    private HttpPresenter presenter;
    private XRecyclerView xrecy;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fleadlayout, null);

        presenter = new HttpPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        presenter.getMap(map,"瞎推荐");

        xrecy = view.findViewById(R.id.xrecy);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xrecy.setLayoutManager(manager);
        return view;
    }

    @Override
    public void OnSuccess(Object o, String tag) {
        if(tag.equals("瞎推荐")) {

            AndroidBean androidBean = (AndroidBean) o;
            final List<AndroidBean.ResultsBean> results = androidBean.getResults();

            xrecy.setAdapter(new AndroidAdapter(results, getActivity()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.disattach();
    }

}
