package me.xihuxiaolong.generalcomponent.common.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;

import java.util.List;

import me.xihuxiaolong.generalcomponent.R;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public abstract class LoadMoreRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, H, T> extends HeaderRecyclerViewAdapter<VH, H, T, LoadMoreRecyclerViewAdapter.FooterData> {

    protected final LayoutInflater mLayoutInflater;

    protected Context mContext;

    FooterData footerData;

    LoadMoreViewHolder loadMoreViewHolder;

    private List<T> mList;

    LoadMoreAgainListener loadMoreAgainListener;

    public LoadMoreRecyclerViewAdapter(Context context, LoadMoreAgainListener loadMoreAgainListener){
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        footerData = new FooterData(null, 0);
        setFooter(footerData);
        hideFooter();
        this.loadMoreAgainListener = loadMoreAgainListener;
        notifyDataSetChanged();
    }

    @Override
    protected void onBindFooterViewHolder(VH holder, int position) {
        FooterData footerData = getFooter();
        ((LoadMoreViewHolder)holder).infoView.setText(footerData.info);
        ((LoadMoreViewHolder)holder).infoView.setVisibility(View.VISIBLE);
        if(footerData.status == FooterData.ERROR_TYPE) {
            ((LoadMoreViewHolder)holder).imageView.setImageResource(R.drawable.icon_error);
            ((LoadMoreViewHolder)holder).progressBar.setVisibility(View.GONE);
            ((LoadMoreViewHolder)holder).imageView.setVisibility(View.VISIBLE);
            ((LoadMoreViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadMoreAgainListener.loadmore();
                }
            });
        } else if(footerData.status == FooterData.LOADING_TYPE){
            ((LoadMoreViewHolder)holder).progressBar.setVisibility(View.VISIBLE);
            ((LoadMoreViewHolder)holder).imageView.setVisibility(View.GONE);
            ((LoadMoreViewHolder)holder).itemView.setOnClickListener(null);
        } else if(footerData.status == FooterData.LOADING_COMPLETE){
            ((LoadMoreViewHolder)holder).progressBar.setVisibility(View.GONE);
            ((LoadMoreViewHolder)holder).imageView.setVisibility(View.GONE);
            ((LoadMoreViewHolder)holder).itemView.setOnClickListener(null);
        }
    }

    protected VH onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        loadMoreViewHolder = new LoadMoreViewHolder(mLayoutInflater.inflate(R.layout.footer_loadmore, parent, false));
        return (VH) loadMoreViewHolder;
    }

    public void showErrorFooter(String info){
        if(TextUtils.isEmpty(info))
            info = "加载失败，请重试";
        setFooter(new FooterData(info, FooterData.ERROR_TYPE));
        showFooter();
    }

    public void showLoadingFooter(String info){
        if(TextUtils.isEmpty(info))
            info = "加载中，请稍候。。。";
        setFooter(new FooterData(info, FooterData.LOADING_TYPE));
        showFooter();
    }

    public void showLoadingComplete(String info){
        if(TextUtils.isEmpty(info))
            info = "没有更多内容了。。。";
        setFooter(new FooterData(info, FooterData.LOADING_COMPLETE));
        showFooter();
    }

    public void setItems(List<T> items) {
        this.mList = items;
        super.setItems(mList);
        notifyDataSetChanged();
    }

    public void addItems(List<T> items) {
        this.mList.addAll(items);
        super.setItems(mList);
    }

    public interface LoadMoreAgainListener{
        void loadmore();
    }

    public static class FooterData{
        public static final int ERROR_TYPE = 1;
        public static final int LOADING_TYPE = 2;
        public static final int LOADING_COMPLETE = 3;

        public String info;
        public int status;

        public FooterData(String info, int status) {
            this.info = info;
            this.status = status;
        }
    }
}
