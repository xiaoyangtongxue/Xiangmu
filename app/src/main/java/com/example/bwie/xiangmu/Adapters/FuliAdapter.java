package com.example.bwie.xiangmu.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.R;
import com.example.bwie.xiangmu.View.ImageActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by xsj on 2017/12/17.
 */

public class FuliAdapter extends XRecyclerView.Adapter<FuliAdapter.ViewHolder>{

    private List<AndroidBean.ResultsBean> list ;
    private Context context;

    public FuliAdapter(List<AndroidBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.fuliitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(list.get(position).getUrl()).into(holder.img);


        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageActivity.class);
                intent.putExtra("url",list.get(position).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends XRecyclerView.ViewHolder{

        private  ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (ImageView) itemView.findViewById(R.id.sdv_head_image);

        }
    }
}
