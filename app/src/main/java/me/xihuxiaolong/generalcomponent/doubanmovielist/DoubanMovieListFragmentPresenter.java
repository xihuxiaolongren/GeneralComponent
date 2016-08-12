package me.xihuxiaolong.generalcomponent.doubanmovielist;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.common.api.IDoubanApiService;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import me.xihuxiaolong.generalcomponent.common.mvp.SimpleMvpLceListRxPresenter;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class DoubanMovieListFragmentPresenter extends SimpleMvpLceListRxPresenter<DoubanMovieListContract.IView, List<Subject>> implements DoubanMovieListContract.IPresenter{

    int start, count;

    @Inject
    IDoubanApiService doubanApiService;

    @Inject
    Context context;

    @Inject
    public DoubanMovieListFragmentPresenter() {
        start = 0;
        count = 10;

    }

    protected void onNext(List<Subject> data) {
        super.onNext(data);
        start += count;
    }

    protected void onLoadMoreNext(List<Subject> data) {
        super.onLoadMoreNext(data);
        start += count;
    }

    @Override
    public void loadMovie() {
        start = 0;
        doubanApiService.getTopMovie(context, subscribe(false), start, count);
    }

    @Override
    public void reLoadMovie() {
        start = 0;
        doubanApiService.getTopMovie(context, subscribe(true), start, count);
    }

    @Override
    public void loadMoreMovie() {
        doubanApiService.getTopMovie(context, subscribeLoadMore(), start, count);
    }
}
