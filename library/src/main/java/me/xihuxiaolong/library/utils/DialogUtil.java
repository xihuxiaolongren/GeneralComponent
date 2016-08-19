package me.xihuxiaolong.library.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.afollestad.materialdialogs.MaterialDialog;

import me.xihuxiaolong.library.R;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class DialogUtil {

    public static MaterialDialog showDialog(Context context, String title, String content, MaterialDialog.SingleButtonCallback positiveCallback){
        MaterialDialog.Builder builder = new MaterialDialog.Builder(context);
        if(title != null)
            builder.title(title);
        if(content != null)
            builder.content(content);
        if(positiveCallback != null)
            builder.onPositive(positiveCallback);
        return builder
                .positiveText(R.string.agree)
                .negativeText(R.string.disagree)
                .limitIconToDefaultSize()
                .show();
    }

}
