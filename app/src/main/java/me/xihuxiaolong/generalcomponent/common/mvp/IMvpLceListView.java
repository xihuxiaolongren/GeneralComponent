package me.xihuxiaolong.generalcomponent.common.mvp;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public interface IMvpLceListView<M> extends MvpLceView<M>{

    void setMoreData(M data);

    void showLoadingMore();

    void showLoadMoreError();

    void showLoadMoreComplete();

    void showMoreData();

    void loadMoreData();


}
