package me.xihuxiaolong.generalcomponent.module.doubanmovielist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
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
public class DoubanMovieViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.movie_title_tv)
    TextView movieTitleTv;
    @BindView(R.id.movie_genres_tv)
    TextView movieGenresTv;
    @BindView(R.id.movie_image_iv)
    ImageView movieImageIv;

    public DoubanMovieViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}