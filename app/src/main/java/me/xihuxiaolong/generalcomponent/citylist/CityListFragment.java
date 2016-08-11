package me.xihuxiaolong.generalcomponent.citylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceFragment;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.model.City;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class CityListFragment extends MvpLceFragment<RelativeLayout, List<List<City>>, CityListContract.IView<List<List<City>>>, CityListFragmentPresenter> implements CityListContract.IView<List<List<City>>>, OnQuickSideBarTouchListener {

    @Inject
    CityListFragmentPresenter presenter;

    CityListAdapter adapter;

    CityListFragmentComponent component;

    protected LinearLayoutManager linearLayoutManager;

    @BindView(R.id.quickSideBarView)
    QuickSideBarView quickSideBarView;
    @BindView(R.id.quickSideBarTipsView)
    QuickSideBarTipsView quickSideBarTipsView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    protected void injectDependencies() {
        component = DaggerCityListFragmentComponent.builder()
                .appComponent(((MyApplication) getActivity().getApplication()).getAppComponent())
                .build();
        component.inject(this);
    }

    @Override
    public CityListFragmentPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CityListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new StickyRecyclerHeadersDecoration(adapter));
        quickSideBarView.setOnQuickSideBarTouchListener(this);
        loadData(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        injectDependencies();
        View view = inflater.inflate(R.layout.fragment_city_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setData(List<List<City>> data) {
        adapter.addAll(data.get(1));
    }


    @Override
    public void loadData(boolean pullToRefresh) {
        presenter.loadMovie();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void onLetterChanged(String letter, int position, float y) {
        quickSideBarTipsView.setText(letter, position, y);
        presenter.scrollToLetter(letter);
    }

    @Override
    public void onLetterTouching(boolean touching) {
        quickSideBarTipsView.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void setQuickSideBarTipsData(List<String> letters) {
        quickSideBarView.setLetters(letters);
    }

    @Override
    public void scrollToPosition(int position) {
        ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(position, 0);
    }

    @Override public void showLoading(boolean pullToRefresh) {
        if (!pullToRefresh) {
            //contentView设置为none会导致quickSidebar渲染错误，bug原因未知
            contentView.setVisibility(View.INVISIBLE);
            errorView.setVisibility(View.GONE);
            loadingView.setVisibility(View.VISIBLE);
        }
    }
}
