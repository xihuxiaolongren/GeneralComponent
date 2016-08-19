package me.xihuxiaolong.generalcomponent.module.shortnoteedit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.hannesdorfmann.mosby.mvp.MvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;
import me.xihuxiaolong.library.utils.DialogUtil;
import me.xihuxiaolong.library.utils.ToastUtil;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class ShortNoteEditFragment extends MvpFragment<ShortNoteEditContract.IView, ShortNoteEditFragmentPresenter> implements ShortNoteEditContract.IView {

    public static final String ARGUMENT_EDIT_SHORTNOTE_ID = "EDIT_SHORTNOTE_ID";

    @Inject
    ShortNoteEditFragmentPresenter presenter;

    @Inject
    ImageService imageService;

    @Inject
    ToastUtil toastUtil;

    ShortNoteEditFragmentComponent component;

    @BindView(R.id.edittext)
    EditText edittext;

    private Menu menu;

    protected void injectDependencies(Long shortNoteId) {
        component = DaggerShortNoteEditFragmentComponent.builder()
                .appComponent(ActivityUtils.getAppComponent(getActivity()))
                .shortNoteEditModule(new ShortNoteEditModule(shortNoteId))
                .build();
        component.inject(this);
    }

    @Override
    public ShortNoteEditFragmentPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Long shortNoteId = getArguments().getLong(ARGUMENT_EDIT_SHORTNOTE_ID);
        injectDependencies(shortNoteId);
        View view = inflater.inflate(R.layout.fragment_shortnote_edit, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                DialogUtil.showDialog(getActivity(), "删除便签", "你确定要删除这条便签吗？", new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        presenter.deleteShortNote();
                    }
                });
                return true;
            case R.id.menu_save:
                if(TextUtils.isEmpty(edittext.getText()))
                    toastUtil.showToast("不能保存一条空的便签", Toast.LENGTH_SHORT);
                else
                    presenter.saveShortNote(edittext.getText().toString());
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_shortnote_edit_menu, menu);
        this.menu = menu;
        presenter.loadShortNote();
    }

    @Override
    public void showDeleteMenu(boolean visible) {
        menu.findItem(R.id.menu_delete).setVisible(visible);
    }

    @Override
    public void setText(String text) {
        edittext.setText(text);
    }

    @Override
    public void saveSuccess() {
        getActivity().finish();
    }

    @Override
    public void deleteSuccess() {
        getActivity().finish();
    }

    @Override
    public void shareShortNote() {

    }

}
