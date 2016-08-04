package me.xihuxiaolong.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class ToastUtil {

    private Context mContext;

    public ToastUtil(Context context){
        this.mContext = context;
    }

    public void showToast(String message){
        Toast.makeText(mContext,message, Toast.LENGTH_LONG).show();
    }
}
