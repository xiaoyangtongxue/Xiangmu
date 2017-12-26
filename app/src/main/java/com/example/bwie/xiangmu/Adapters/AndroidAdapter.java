package com.example.bwie.xiangmu.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bwie.xiangmu.Beans.AndroidBean;
import com.example.bwie.xiangmu.R;
import com.example.bwie.xiangmu.View.WebViewActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * Created by xsj on 2017/12/17.
 */

public class AndroidAdapter extends XRecyclerView.Adapter<AndroidAdapter.ViewHolder>{

    private List<AndroidBean.ResultsBean> list ;
    private Context context;

    public AndroidAdapter(List<AndroidBean.ResultsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.androiditem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final List<String> images = list.get(position).getImages();

        if(images!=null&&images.size()!=0){
            for (int i=0;i<images.size();i++) {
                DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        //设置uri,加载本地的gif资源
                        .setUri(images.get(i))//设置uri
                        .build();
                holder.img.setController(mDraweeController);
            }
        }else{
            holder.img.setImageURI(Uri.parse("res://com.example.xsj.shipei.Adapters/" + R.mipmap.ic_launcher_round));
        }

        holder.title.setText(list.get(position).getDesc());
        holder.name.setText(list.get(position).getWho());
        holder.time.setText(list.get(position).getPublishedAt());

        holder.linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebViewActivity.class);
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

        private final SimpleDraweeView img;
        private final TextView title,name,time;
        private final LinearLayout linear1;

        public ViewHolder(View itemView) {
            super(itemView);

            img = (SimpleDraweeView) itemView.findViewById(R.id.sdv_head_image);
            title = (TextView) itemView.findViewById(R.id.title);
            name = (TextView) itemView.findViewById(R.id.name);
            time = (TextView) itemView.findViewById(R.id.time);
            linear1 = (LinearLayout) itemView.findViewById(R.id.linear1);

        }
    }
}
