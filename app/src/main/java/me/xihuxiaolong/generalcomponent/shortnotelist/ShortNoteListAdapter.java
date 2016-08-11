package me.xihuxiaolong.generalcomponent.shortnotelist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.mvp.LoadMoreRecyclerViewAdapter;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/8.
 */
public class ShortNoteListAdapter extends LoadMoreRecyclerViewAdapter<RecyclerView.ViewHolder, Object, DBShortNote> {

    ShortNoteItemListener shortNoteItemListener;

    ImageService imageService;

    public ShortNoteListAdapter(Context context, ShortNoteItemListener shortNoteItemListener, ImageService imageService) {
        super(context, shortNoteItemListener);
        this.shortNoteItemListener = shortNoteItemListener;
        this.imageService = imageService;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View rootview = mLayoutInflater.inflate(R.layout.item_douban_movie, parent, false);
        ShortNoteViewHolder shortNoteViewHolder = new ShortNoteViewHolder(rootview);

        return shortNoteViewHolder;
    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        final DBShortNote dbShortNote = getItem(position);
        ((ShortNoteViewHolder) holder).movieTitleTv.setText(dbShortNote.getId() + " " + dbShortNote.getText());
        ((ShortNoteViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shortNoteItemListener.onShortNoteClick(dbShortNote);
            }
        });

    }

    public interface ShortNoteItemListener extends LoadMoreAgainListener{

        void onShortNoteClick(DBShortNote dbShortNote);

    }

}
