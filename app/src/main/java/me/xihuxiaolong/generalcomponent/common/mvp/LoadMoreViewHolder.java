package me.xihuxiaolong.generalcomponent.common.mvp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.xihuxiaolong.generalcomponent.R;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class LoadMoreViewHolder extends RecyclerView.ViewHolder {

    TextView infoView;

    ProgressBar progressBar;

    ImageView imageView;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);
        infoView = (TextView) itemView.findViewById(R.id.info_view);
        progressBar = (ProgressBar) itemView.findViewById(R.id.progressbar);
        imageView = (ImageView) itemView.findViewById(R.id.errorView);
    }

}
