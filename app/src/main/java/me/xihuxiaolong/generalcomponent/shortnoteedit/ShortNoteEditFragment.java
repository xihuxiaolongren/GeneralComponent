package me.xihuxiaolong.generalcomponent.shortnoteedit;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.rengwuxian.materialedittext.MaterialEditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;
import me.xihuxiaolong.library.utils.ToastUtil;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/5.
 */
public class ShortNoteEditFragment extends MvpFragment<ShorNoteEditContract.IView, ShortNoteEditFragmentPresenter> implements ShorNoteEditContract.IView {

    @Inject
    ShortNoteEditFragmentPresenter presenter;

    @Inject
    ImageService imageService;

    @Inject
    ToastUtil toastUtil;

    ShortNoteEditFragmentComponent component;

    @BindView(R.id.edittext)
    EditText edittext;

    protected void injectDependencies() {
        component = DaggerShortNoteEditFragmentComponent.builder()
                .appComponent(ActivityUtils.getAppComponent(getActivity()))
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
        injectDependencies();
        View view = inflater.inflate(R.layout.fragment_shortnote_edit, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete:
                toastUtil.showToast("delete", Toast.LENGTH_SHORT);
                presenter.deleteShortNote(null);
                return true;
            case R.id.menu_save:
                toastUtil.showToast("save", Toast.LENGTH_SHORT);
                presenter.saveShortNote(null);
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_shortnote_edit_menu, menu);
    }

    @Override
    public void setTitle(String title) {

    }

    @Override
    public void saveSuccess() {

    }

    @Override
    public void deleteSuccess() {

    }

    @Override
    public void shareShortNote() {

    }

}
