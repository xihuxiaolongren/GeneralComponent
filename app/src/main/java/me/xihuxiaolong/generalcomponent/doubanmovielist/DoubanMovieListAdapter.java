package me.xihuxiaolong.generalcomponent.doubanmovielist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import me.xihuxiaolong.generalcomponent.common.mvp.LoadMoreRecyclerViewAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/8.
 */
public class DoubanMovieListAdapter extends LoadMoreRecyclerViewAdapter<RecyclerView.ViewHolder, Object, Subject> {

    SubjectItemListener subjectItemListener;

    @Inject
    public DoubanMovieListAdapter(Context context, SubjectItemListener subjectItemListener) {
        super(context, subjectItemListener);
        this.subjectItemListener = subjectItemListener;
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
        ((DoubanMovieViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjectItemListener.onSubjectClick(subject);
            }
        });

    }

    public interface SubjectItemListener extends LoadMoreAgainListener{

        void onSubjectClick(Subject subject);

    }

}
