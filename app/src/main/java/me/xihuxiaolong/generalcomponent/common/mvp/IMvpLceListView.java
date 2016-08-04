package me.xihuxiaolong.generalcomponent.common.mvp;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public interface IMvpLceListView<M, D> extends MvpLceView<M> {

    public void setMoreData(D data);

    public void showLoadingMore();

    public void showLoadMoreError();

    public void showLoadMoreComplete();

    public void showMoreData();

    public void loadMoreData();


}
