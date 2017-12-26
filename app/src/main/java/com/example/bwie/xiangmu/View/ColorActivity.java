package com.example.bwie.xiangmu.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.bwie.xiangmu.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ColorActivity extends AppCompatActivity {

    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.green)
    RelativeLayout green;
    @BindView(R.id.blue)
    RelativeLayout blue;
    @BindView(R.id.yellow)
    RelativeLayout yellow;
    @BindView(R.id.fen)
    RelativeLayout fen;
    @BindView(R.id.zi)
    RelativeLayout zi;
    @BindView(R.id.red)
    RelativeLayout red;
    @BindView(R.id.rela)
    RelativeLayout rela;

    private int color = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        SharedPreferences user = getSharedPreferences("User", MODE_PRIVATE);
        int selectcolor = user.getInt("selectcolor", 0);
        if(selectcolor!=0){
            rela.setBackgroundColor(selectcolor);
        }
    }

    @OnClick({R.id.im_back, R.id.green, R.id.blue, R.id.yellow, R.id.fen, R.id.zi, R.id.red})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.im_back:
                finish();
                break;
            case R.id.green:
                color = getResources().getColor(R.color.colorgreen);
                rela.setBackgroundColor(color);

                break;
            case R.id.blue:
                color = getResources().getColor(R.color.colorBlue);
                rela.setBackgroundColor(color);

                break;
            case R.id.yellow:
                color = getResources().getColor(R.color.colorYellow);
                rela.setBackgroundColor(color);

                break;
            case R.id.fen:
                color = getResources().getColor(R.color.colorFen);
                rela.setBackgroundColor(color);

                break;
            case R.id.zi:
                color = getResources().getColor(R.color.colorZi);
                rela.setBackgroundColor(color);

                break;
            case R.id.red:
                color = getResources().getColor(R.color.colorRed);
                rela.setBackgroundColor(color);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().post(color);

        SharedPreferences user = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor edit = user.edit();
        edit.putInt("selectcolor",color);
        edit.commit();
    }
}
