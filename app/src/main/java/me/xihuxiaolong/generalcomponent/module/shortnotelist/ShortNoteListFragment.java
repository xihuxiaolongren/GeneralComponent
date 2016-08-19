package me.xihuxiaolong.generalcomponent.module.shortnotelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.mvp.SimpleMvpLceListFragment;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;
import me.xihuxiaolong.generalcomponent.module.shortnoteedit.ShortNoteEditActivity;
import me.xihuxiaolong.generalcomponent.module.shortnoteedit.ShortNoteEditFragment;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class ShortNoteListFragment extends SimpleMvpLceListFragment<List<DBShortNote>, ShortNoteListContract.IView, ShortNoteListFragmentPresenter> implements ShortNoteListContract.IView{

    @Inject
    ShortNoteListFragmentPresenter presenter;

    @Inject
    ImageService imageService;

    ShortNoteListFragmentComponent component;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    public ShortNoteListFragmentPresenter createPresenter() {
        return presenter;
    }

    @Override
    protected ShortNoteListAdapter createAdapter() {
        return new ShortNoteListAdapter(getContext(), shortNoteItemListener, imageService);
    }

    ShortNoteListAdapter.ShortNoteItemListener shortNoteItemListener = new ShortNoteListAdapter.ShortNoteItemListener() {

        @Override
        public void onShortNoteClick(DBShortNote dbShortNote) {
            startActivity(new Intent(getActivity(), ShortNoteEditActivity.class).putExtra(ShortNoteEditFragment.ARGUMENT_EDIT_SHORTNOTE_ID, dbShortNote.getId()));
        }
    };

    protected void injectDependencies() {
        component = DaggerShortNoteListFragmentComponent.builder()
                .appComponent(ActivityUtils.getAppComponent(getActivity()))
                .build();
        component.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        injectDependencies();
        View view = inflater.inflate(R.layout.fragment_shortnote_list, container, false);
        ButterKnife.bind(this, view);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(getActivity(), ShortNoteEditActivity.class));
            }
        });
        return view;
    }

    @Override
    public void setData(List<DBShortNote> data) {
        adapter.setItems(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        if (pullToRefresh == true)
            presenter.reloadShortNotes();
        else
            presenter.loadShortNotes();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return e.getMessage();
    }

    @Override
    public void setMoreData(List<DBShortNote> data) {
        adapter.addItems(data);
    }

    @Override
    public void loadMoreData() {
        presenter.loadmoreShortNotes();
    }

    @Override
    public void removeShortNoteItem(long shortNoteId) {
        ((ShortNoteListAdapter) adapter).removeShortNote(shortNoteId);
    }

    @Override
    public void addShortNoteItem(DBShortNote shortNote) {
        ((ShortNoteListAdapter) adapter).addShortNote(shortNote);
    }

    @Override
    public void updateShortNoteItem(DBShortNote shortNote) {
        ((ShortNoteListAdapter) adapter).updateShortNote(shortNote);
    }

    @Override
    public void loadmore() {
        loadMoreData();
    }
}
