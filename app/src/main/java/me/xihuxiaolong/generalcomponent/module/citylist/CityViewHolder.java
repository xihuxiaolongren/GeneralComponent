package me.xihuxiaolong.generalcomponent.module.citylist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/3/3.
 * 结算明细item
 */
public class CityViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.city_tv)
    TextView cityTv;

    public CityViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}