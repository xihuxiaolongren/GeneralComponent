package me.xihuxiaolong.generalcomponent.common;

import android.content.Context;

/**
 * 测试leakcanary是否生效
 */
public class TestLeakHelper {
 
    private Context mCtx;

    private static TestLeakHelper ourInstance = null;
 
    public static TestLeakHelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new TestLeakHelper(context);
        }
        return ourInstance;
    }
 
    private TestLeakHelper() {
    }
 
    private TestLeakHelper(Context context) {
        this.mCtx = context;
    }
 
}