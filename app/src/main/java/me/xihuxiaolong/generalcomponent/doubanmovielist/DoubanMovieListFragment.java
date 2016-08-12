package me.xihuxiaolong.generalcomponent.doubanmovielist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import me.xihuxiaolong.generalcomponent.common.mvp.LoadMoreRecyclerViewAdapter;
import me.xihuxiaolong.generalcomponent.common.mvp.SimpleMvpLceListFragment;
import me.xihuxiaolong.generalcomponent.common.mvp.SimpleMvpLceListRxPresenter;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;

/**
 *
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class DoubanMovieListFragment extends SimpleMvpLceListFragment<List<Subject>, DoubanMovieListContract.IView, SimpleMvpLceListRxPresenter<DoubanMovieListContract.IView, List<Subject>> > {

    @Inject
    DoubanMovieListFragmentPresenter presenter;

    @Inject
    ImageService imageService;

    DoubanMovieListFragmentComponent component;

    @Override
    public SimpleMvpLceListRxPresenter<DoubanMovieListContract.IView, List<Subject>> createPresenter() {
        return presenter;
    }

    @Override
    protected DoubanMovieListAdapter createAdapter() {
        return new DoubanMovieListAdapter(getContext(), subjectItemListener, imageService);
    }

    DoubanMovieListAdapter.SubjectItemListener subjectItemListener = new DoubanMovieListAdapter.SubjectItemListener() {

        @Override
        public void onSubjectClick(Subject subject) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(subject.getAlt()));
            startActivity(intent);
        }
    };

    protected void injectDependencies() {
        component = DaggerDoubanMovieListFragmentComponent.builder()
                .appComponent(ActivityUtils.getAppComponent(getActivity()))
                .doubanMovieListModule(new DoubanMovieListModule(getContext()))
                .build();
        component.inject(this);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        injectDependencies();
        return inflater.inflate(R.layout.fragment_douban_movie_list, container, false);
    }

    @Override
    public void setData(List<Subject> data) {
        adapter.setItems(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if(pullToRefresh)
            presenter.reLoadMovie();
        else
            presenter.loadMovie();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setMoreData(List<Subject> data) {
        adapter.addItems(data);
    }

    @Override
    public void loadMoreData() {
        presenter.loadMoreMovie();
    }

    @Override
    public void loadmore() {
        loadMoreData();
    }
}
