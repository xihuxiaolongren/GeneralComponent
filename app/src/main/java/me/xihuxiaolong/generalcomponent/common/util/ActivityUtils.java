package me.xihuxiaolong.generalcomponent.common.util;

import android.app.Activity;
import android.content.Context;

import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.dagger.component.AppComponent;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/10.
 */
public class ActivityUtils {

    public static AppComponent getAppComponent(Activity activity){
        return ((MyApplication) (activity.getApplication())).getAppComponent();
    }
}
