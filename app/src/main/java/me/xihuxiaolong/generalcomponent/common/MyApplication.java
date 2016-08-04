package me.xihuxiaolong.generalcomponent.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import me.xihuxiaolong.generalcomponent.common.dagger.component.AppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.component.DaggerAppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ApiServiceModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.AppModule;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class MyApplication extends Application {

    private static MyApplication myApplication;

    AppComponent mAppComponent;

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static MyApplication get(Context context){
        return (MyApplication)context.getApplicationContext();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .build();
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }
}
