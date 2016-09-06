package me.xihuxiaolong.generalcomponent.module.uishow;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.Switch;
import com.soundcloud.android.crop.Crop;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.util.ActivityUtils;
import me.xihuxiaolong.generalcomponent.common.view.AddPhotoView;
import me.xihuxiaolong.library.utils.CollectionUtil;
import me.xihuxiaolong.library.utils.DialogUtil;
import me.xihuxiaolong.library.utils.GridSpacingItemDecoration;
import me.xihuxiaolongren.photoga.MediaChoseActivity;
import mehdi.sakout.fancybuttons.FancyButton;

public class UIImageProcessFragment extends Fragment {

    ImageService imageService;

    @BindView(R.id.crop_image)
    FancyButton cropImage;
    @BindView(R.id.switches_cb)
    CheckBox switchesCb;
    @BindView(R.id.resultView)
    ImageView resultView;
    @BindView(R.id.select_image)
    FancyButton selectImage;
    @BindView(R.id.switches_crop)
    Switch switchesCrop;
    @BindView(R.id.et_select_num)
    MaterialEditText etSelectNum;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.addphoto)
    AddPhotoView addphoto;
    @BindView(R.id.addphotoResultShow)
    ImageView addphotoResultShow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageService = ActivityUtils.getAppComponent(getActivity()).getImageService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ui_image_process, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

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
    public void onResume() {
        super.onResume();
        etSelectNum.clearFocus();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_button_menu, menu);
    }

    @OnClick(R.id.crop_image)
    void cropImageClick(View v) {
        Crop.pickImage(getActivity(), this);
    }

    int request;

    @OnClick(R.id.select_image)
    void selectImageClick(View v) {
        request = 0;
        Intent intent = new Intent(getActivity(), MediaChoseActivity.class);
        //chose_mode选择模式 0单选 1多选
        intent.putExtra("chose_mode", switchesCrop.isChecked() ? 0 : 1);
        //最多支持选择多少张
        if (TextUtils.isEmpty(etSelectNum.getText()))
            return;
        intent.putExtra("max_chose_count", Integer.parseInt(etSelectNum.getText().toString()));
        //是否显示需要第一个是图片相机按钮
        intent.putExtra("isNeedfcamera", true);
        //是否剪裁图片(只有单选模式才有剪裁)
        intent.putExtra("crop", switchesCrop.isChecked());
        startActivityForResult(intent, MediaChoseActivity.REQUEST_CODE_CAMERA);
    }

    @OnClick(R.id.addphoto)
    void addphotoClick(View v) {
        request = 1;
        Intent intent = new Intent(getActivity(), MediaChoseActivity.class);
        intent.putExtra("chose_mode", 0);
        //是否显示需要第一个是图片相机按钮
        intent.putExtra("isNeedfcamera", true);
        //是否剪裁图片(只有单选模式才有剪裁)
        intent.putExtra("crop", switchesCrop.isChecked());
        startActivityForResult(intent, MediaChoseActivity.REQUEST_CODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == Activity.RESULT_OK) {
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP) {
            handleCrop(resultCode, result);
        } else if (requestCode == MediaChoseActivity.REQUEST_CODE_CAMERA) {
            if (result != null && !CollectionUtil.isEmpty(result.getStringArrayListExtra("data"))) {
                ArrayList<String> uris = result.getStringArrayListExtra("data");
                if(request == 0) {
                    int spanCount = uris.size() < 3 ? uris.size() : 3;
                    int spacing = 20; // 50px
                    boolean includeEdge = false;
                    recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));
                    recyclerView.setAdapter(new CommonAdapter<String>(getContext(), R.layout.item_image, uris) {
                        @Override
                        protected void convert(ViewHolder holder, String s, int position) {
                            ImageView imageView = holder.getView(R.id.image_iv);
                            imageView.setImageURI(Uri.parse(s));
                        }
                    });
                }else if(request == 1){
                    addphoto.startUp(uris.get(0), "上传图片成功", new AddPhotoView.UploadListener() {
                        @Override
                        public void uploadSuccess(String picUrl) {
                            imageService.loadImageFromUrl(getContext(), picUrl, addphotoResultShow);
                        }
                    });
                }
            }
        }
    }

    private void beginCrop(Uri source) {
        Uri destination = Uri.fromFile(new File(getContext().getCacheDir(), "cropped"));
        Crop crop = Crop.of(source, destination);
        if (switchesCb.isChecked())
            crop.asSquare();
        crop.start(getActivity(), this);
    }

    private void handleCrop(int resultCode, Intent result) {
        if (resultCode == Activity.RESULT_OK) {
            resultView.setImageDrawable(null);
            resultView.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR) {
            Toast.makeText(getActivity(), Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
