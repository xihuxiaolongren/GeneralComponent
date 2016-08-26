package me.xihuxiaolong.generalcomponent.module.uishow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;
import me.xihuxiaolong.library.utils.DialogUtil;
import uk.co.senab.photoview.PhotoView;

public class UIImageShowFragment extends Fragment {

    public ImageService imageService;

    LayoutInflater mLayoutInflater;

    @BindView(R.id.photoView)
    PhotoView photoView;
    @BindView(R.id.circleIV1)
    CircleImageView circleIV1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_image_show, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        imageService = ActivityUtils.getAppComponent(getActivity()).getImageService();

        mLayoutInflater = LayoutInflater.from(getContext());

        imageService.loadImageFromUrl(getContext(), "http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg", circleIV1);
        imageService.loadImageFromUrl(getContext(), "http://pbs.twimg.com/media/Bist9mvIYAAeAyQ.jpg", photoView);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_explain:
                DialogUtil.showDialog(getContext(), "image UI 说明", Html.fromHtml(getString(R.string.about_button)));
                return true;
        }
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_button_menu, menu);
    }

}
