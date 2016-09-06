package me.xihuxiaolong.generalcomponent.common.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.generalcomponent.common.image.ImageUploadTask;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/3/10.
 */
public class AddPhotoView extends FrameLayout{

//    private ImageView download;

    private TextView textView;

    private TextView clickReloadTV;

    private ImageView icon;

    private ImageView photo;

    private SmoothProgressBar smoothProgressBar;

    private String uri;

    private String url;

    private String uploadTextOK;

    private UploadListener uploadListener;

    ImageService imageService;

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    private boolean isUpdate = false;

    public void startUp(String uri){
        startUp(uri, null, null);
    }

    public void startUp(String uri, String uploadTextOK){
        startUp(uri, uploadTextOK, null);
    }

    public void startUp(final String uri, final String uploadTextOK, final UploadListener uploadListener){
        this.uri = uri;
        this.uploadTextOK = uploadTextOK;
        this.uploadListener = uploadListener;
        imageService = MyApplication.getInstance().getAppComponent().getImageService();
        imageService.loadImageFromFile(getContext(), photo, uri, ImageView.ScaleType.CENTER_CROP);
        clickReloadTV.setVisibility(View.VISIBLE);
        new ImageUploadTask().asyncPutObjectFromLocalFile(Uri.decode(uri), new ImageUploadTask.IMageUploadListener() {
            @Override
            public void onProgress(long currentSize, long totalSize) {

            }

            @Override
            public void onSuccess(final String resultUrl) {
                post(new Runnable() {
                    @Override
                    public void run() {
                        setImageKey(resultUrl);
                        isUpdate = true;
                        smoothProgressBar.setVisibility(View.GONE);
                        if(!TextUtils.isEmpty(uploadTextOK))
                            Toast.makeText(getContext(), uploadTextOK, Toast.LENGTH_SHORT).show();
                        if(uploadListener != null)
                            uploadListener.uploadSuccess(resultUrl);
                    }
                });

            }

            @Override
            public void onFailure(ClientException clientExcepion, ServiceException serviceException) {

            }
        });
        smoothProgressBar.setVisibility(View.VISIBLE);
    }

    public void setClickReloadVisible() {
        clickReloadTV.setVisibility(View.VISIBLE);
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setImageUrl(String url){
        this.url = url;
        imageService.loadImageFromUrl(this.getContext(), url, photo, ImageView.ScaleType.CENTER_CROP);
    }

    public void setImageKey(String url){
        this.url = url;
//        ImageManager.displayImageFromUrl(this.getContext(), url, photo, ImageView.ScaleType.CENTER_CROP);
    }

    public String getImageUrl(){
        return url;
    }

    public AddPhotoView(Context context) {
        super(context);
        init(context);
    }

    public AddPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddPhotoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AddPhotoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private Handler uiHandler = new Handler();

    private void init(final Context context){
        LayoutInflater.from(context).inflate(R.layout.add_photo_view, this);
        icon = (ImageView) findViewById(R.id.cameraIcon);
        photo = (ImageView) findViewById(R.id.photo);
        textView = (TextView) findViewById(R.id.cameraText);
        clickReloadTV = (TextView) findViewById(R.id.clickReload);
        smoothProgressBar = (SmoothProgressBar) findViewById(R.id.progress_bar);
//        download = (ImageView) findViewById(R.id.download);
//        download.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        Bitmap bitmap = imageService.getBitmap(context, url);
//                        if(bitmap == null)
//                            return;
//                        imageService.saveImageToGallery(context, bitmap);
//                        uiHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                if(context != null)
//                                    Toast.makeText(context, "图片已保存", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }).start();
//            }
//        });
    }

//    public void setDownLoadVisibility(int visibility){
//        download.setVisibility(visibility);
//    }


    public void setText(String text){
        textView.setText(text);
    }

    public void setIcon(int resId) {
        icon.setImageResource(resId);
    }

    public interface UploadListener{
        void uploadSuccess(String picUrl);
    }
}
