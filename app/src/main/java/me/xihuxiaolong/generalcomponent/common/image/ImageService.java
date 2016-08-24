package me.xihuxiaolong.generalcomponent.common.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import javax.inject.Inject;

import me.xihuxiaolong.library.R;

/**
 * Created by yangxiaolong on 15/12/17.
 */
public class ImageService {

    public ImageService(){}

    static int defaultPlaceholderId = R.drawable.placeholder;

    public void loadImageFromUrl(Context context, String url, ImageView view) {
        loadImage(context, view, url, null, defaultPlaceholderId, DiskCacheStrategy.SOURCE);
    }

    public void loadImageFromUrl(Context context, String url, ImageView view, int placeholderId) {
        loadImage(context, view, url, null, placeholderId, DiskCacheStrategy.SOURCE);
    }

    public void loadImageFromUrl(Context context, String url, ImageView view, ImageView.ScaleType scaleType) {
        loadImage(context, view, url, scaleType, defaultPlaceholderId, DiskCacheStrategy.SOURCE);
    }

    public void loadImageFromUrl(Context context, String url, ImageView view, ImageView.ScaleType scaleType,
                                    DiskCacheStrategy diskCacheStrategy) {
        loadImage(context, view, url, scaleType, defaultPlaceholderId, diskCacheStrategy);
    }



    public void loadImageFromFile(Context context, ImageView view, String uri) {
        loadImage(context, view, "file://" + uri, null, defaultPlaceholderId, DiskCacheStrategy.NONE);
    }

    public void loadImageFromFile(Context context, ImageView view, String uri, int placeholderId) {
        loadImage(context, view, "file://" + uri, null, placeholderId, DiskCacheStrategy.NONE);
    }

    public void loadImageFromFile(Context context, ImageView view, String uri, ImageView.ScaleType scaleType) {
        loadImage(context, view, "file://" + uri, scaleType, defaultPlaceholderId, DiskCacheStrategy.NONE);
    }

    public void loadImage(Context context, ImageView view, String uri, ImageView.ScaleType scaleType,
                             int placeholderId, DiskCacheStrategy diskCacheStrategy) {
        DrawableTypeRequest<String> request = Glide.with(context).load(uri);
        DrawableRequestBuilder builder = request;
        if(ImageView.ScaleType.FIT_CENTER == scaleType){
            builder = request.fitCenter();
        }else if(ImageView.ScaleType.CENTER_CROP == scaleType){
            builder = request.centerCrop();
        }else{
            builder = request.centerCrop();
        }
        if(placeholderId != -1)
            request.placeholder(placeholderId);
        builder.dontAnimate()
                .diskCacheStrategy(diskCacheStrategy)
                .into(view);
    }
}
