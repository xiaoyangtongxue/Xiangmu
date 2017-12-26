package com.example.bwie.xiangmu.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bwie.xiangmu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xsj on 2017/12/17.
 */

public class HomeFg extends Fragment {

    @BindView(R.id.tas)
    TabLayout tas;
    @BindView(R.id.vp)
    ViewPager vp;
    Unbinder unbinder;

    private List<String> datas = new ArrayList<String>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homelayout, null);
        unbinder = ButterKnife.bind(this, view);

        datas.add("Android");
        datas.add("IOS");
        datas.add("福利");


        //适配器
        vp.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment=null;
                //当选中的位置是对应的索引值的话 就加载那个Fragment
                switch (position) {
                    case 0:
                        fragment=new AndroidFg();
                        break;



                    case 1:
                        fragment=new IosFg();
                        break;
                    case 2:
                        fragment=new FuliFg();
                        break;

                    default:
                        break;
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 3;
            }

            public CharSequence getPageTitle(int position) {
                return datas.get(position);
            }
        });

        //进行关联
        tas.setupWithViewPager(vp);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
