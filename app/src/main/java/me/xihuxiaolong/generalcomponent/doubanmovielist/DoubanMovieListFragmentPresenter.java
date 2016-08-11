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
public class DoubanMovieListFragmentPresenter extends SimpleMvpLceListRxPresenter<IMvpLceListView<List<Subject>, List<Subject>>, List<Subject>, List<Subject>> {

    public static final int CREATE = 1;
    public static final int REFRESH = 2;
    public static final int LOADMORE = 3;

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

    public void loadMovie(int status){
        if(status == CREATE) {
            start = 0;
            doubanApiService.getTopMovie(context, subscribe(false), start, count);
        } else if(status == REFRESH) {
            start = 0;
            doubanApiService.getTopMovie(context, subscribe(true), start, count);
        } else if(status == LOADMORE) {
            doubanApiService.getTopMovie(context, subscribeLoadMore(), start, count);
        }
    }

    protected void onNext(List<Subject> data) {
        super.onNext(data);
        start += count;
    }

    protected void onLoadMoreNext(List<Subject> data) {
        super.onLoadMoreNext(data);
        start += count;
    }

}
