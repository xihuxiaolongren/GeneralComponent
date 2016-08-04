package me.xihuxiaolong.generalcomponent.common.mvp;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;

import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.base.listener.EndlessRecyclerOnScrollListener;

/**
 *
 *
 * @param <M>  首次加载完成所有的数据结构，可以和D相同
 * @param <D>  每次列表加载的数据结构
 */
//public abstract class SimpleMvpLceListFragment<M, D> extends MvpLceFragment<SwipeRefreshLayout, M, IMvpLceListView<M, D>, SimpleMvpLceListRxPresenter<IMvpLceListView<M, D>, M, D> > implements IMvpLceListView<M,D>, SwipeRefreshLayout.OnRefreshListener {
public abstract class SimpleMvpLceListFragment<M, D, P extends MvpPresenter<IMvpLceListView<M,D>>> extends MvpLceFragment<SwipeRefreshLayout, M, IMvpLceListView<M, D>, P> implements IMvpLceListView<M,D>, SwipeRefreshLayout.OnRefreshListener {

    protected RecyclerView recyclerView;
//    protected SwipeRefreshLayout swipeRefreshLayout;

    protected LinearLayoutManager linearLayoutManager;

    protected EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;

    protected LoadMoreRecyclerViewAdapter adapter;

    abstract protected LoadMoreRecyclerViewAdapter createAdapter();

    @CallSuper
    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadMoreData();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        adapter = createAdapter();
        if(adapter == null)
            throw new RuntimeException("adapter should not be null");
        recyclerView.setAdapter(adapter);
        contentView.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary, R.color.colorPrimary);
        contentView.setOnRefreshListener(this);
        contentView.setClickable(false);
        contentView.setEnabled(true);

    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override
    public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
        endlessRecyclerOnScrollListener.reset();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

//    @Override
//    public void loadData(boolean pullToRefresh) {
//    }

    @Override
    public void showLoadingMore() {
        adapter.showLoadingFooter(null);
    }

    @Override
    public void showLoadMoreError() {
        adapter.showErrorFooter(null);
    }

    @Override
    public void showMoreData() {
        adapter.hideFooter();
    }

    @Override
    public void showLoadMoreComplete() {
        adapter.showLoadingComplete(null);
    }

//    @Override
//    public void loadMoreData() {
//    }

}
