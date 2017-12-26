package com.example.bwie.xiangmu.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu.Adapters.FuliAdapter;
import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.Presenter.HttpPresenter;
import com.example.bwie.xiangmu.R;
import com.example.bwie.xiangmu.View.Iview;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xsj on 2017/12/17.
 */

public class FuliFg extends Fragment implements Iview {

    @BindView(R.id.xrecy)
    XRecyclerView xrecy;
    Unbinder unbinder;
    @BindView(R.id.xianxing_fuli)
    FloatingActionButton xianxingFuli;
    @BindView(R.id.liushi_fuli)
    FloatingActionButton liushiFuli;
    @BindView(R.id.pubu_fuli)
    FloatingActionButton pubuFuli;
    @BindView(R.id.changeMaptypeBtn)
    FloatingActionsMenu changeMaptypeBtn;
    private HttpPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fulilayout, null);
        unbinder = ButterKnife.bind(this, view);

        presenter = new HttpPresenter(this);
        HashMap<String, String> map = new HashMap<>();
        presenter.getMap(map, "福利");

//        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        xrecy.setLayoutManager(manager);
        //线性
        xianxingFuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
        });
        //瀑布流
        pubuFuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                LinearLayoutManager manager = new LinearLayoutManager(1, LinearLayoutManager.VERTICAL);
//                xrecy.setLayoutManager(manager);
                xrecy.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            }
        });
        //流式
        liushiFuli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xrecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
            }
        });


        return view;
    }

    //请求成功
    @Override
    public void OnSuccess(Object o, String tag) {
        if (tag.equals("福利")) {
            AndroidBean androidBean = (AndroidBean) o;
            final List<AndroidBean.ResultsBean> results = androidBean.getResults();

            xrecy.setAdapter(new FuliAdapter(results, getActivity()));


        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.disattach();
    }
}
