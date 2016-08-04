package me.xihuxiaolong.generalcomponent.base.listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener {

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 5; // The minimum amount of items to have below your current scroll position before loading more.
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private int type = 0;

    private RecyclerView.LayoutManager mLayoutManager;

    public EndlessRecyclerOnScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
        if(layoutManager instanceof LinearLayoutManager || layoutManager instanceof GridLayoutManager)
            type = 0;
        else if(layoutManager instanceof StaggeredGridLayoutManager)
            type = 1;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState){
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//            if (scrollingUp) {
//                upScroll();
//            }else{
//                downScroll();
//            }

        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLayoutManager.getItemCount();
        if(type == 0) {
            if(mLayoutManager instanceof LinearLayoutManager)
                firstVisibleItem = ((LinearLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
            else if(mLayoutManager instanceof GridLayoutManager)
                firstVisibleItem = ((GridLayoutManager) mLayoutManager).findFirstVisibleItemPosition();
        }else if(type == 1){
            int[] firstVisibleItems = null;
            firstVisibleItems = ((StaggeredGridLayoutManager) mLayoutManager).findFirstVisibleItemPositions(firstVisibleItems);
            if(firstVisibleItems != null && firstVisibleItems.length > 0) {
                firstVisibleItem = firstVisibleItems[0];
            }
        }
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {
            current_page++;
            onLoadMore(current_page);
            loading = true;
        }
    }

    public void reset(){
        previousTotal = 0;
        loading = true;
        current_page = 1;
    }

    public abstract void onLoadMore(int current_page);

}
