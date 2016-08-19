package me.xihuxiaolong.generalcomponent.module.doubanmovielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import me.xihuxiaolong.generalcomponent.common.mvp.LoadMoreRecyclerViewAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/8.
 */
public class DoubanMovieListAdapter extends LoadMoreRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Subject> {

    SubjectItemListener subjectItemListener;

    ImageService imageService;

    public DoubanMovieListAdapter(Context context, SubjectItemListener subjectItemListener, ImageService imageService) {
        super(context);
        this.subjectItemListener = subjectItemListener;
        this.imageService = imageService;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View rootview = mLayoutInflater.inflate(R.layout.item_douban_movie, parent, false);
        DoubanMovieViewHolder doubanMovieViewHolder = new DoubanMovieViewHolder(rootview);

        return doubanMovieViewHolder;
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Subject subject = getItem(position);
        ((DoubanMovieViewHolder) holder).movieTitleTv.setText(position + " " + subject.getTitle());
        ((DoubanMovieViewHolder) holder).movieGenresTv.setText(TextUtils.join(",", subject.getGenres()));
        imageService.loadImageFromUrl(mContext, subject.getImages().getSmall(), ((DoubanMovieViewHolder) holder).movieImageIv, ImageView.ScaleType.FIT_CENTER);
        ((DoubanMovieViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectItemListener.onSubjectClick(subject);
            }
        });

    }

    public interface SubjectItemListener{

        void onSubjectClick(Subject subject);

    }

}
