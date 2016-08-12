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
 * @param <M>  加载的列表数据结构
 */
public abstract class SimpleMvpLceListFragment<M, V extends IMvpLceListView<M>, P extends MvpPresenter<V> > extends MvpLceFragment<SwipeRefreshLayout, M, V, P>
        implements IMvpLceListView<M>, SwipeRefreshLayout.OnRefreshListener, LoadMoreRecyclerViewAdapter.LoadMoreAgainListener {

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
                loadmore();
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        adapter = createAdapter();
        if(adapter == null)
            throw new RuntimeException("adapter should not be null");
        adapter.setLoadMoreAgainListener(this);
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

}
