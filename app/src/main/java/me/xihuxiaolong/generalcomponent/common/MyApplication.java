package me.xihuxiaolong.generalcomponent.common;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.multidex.MultiDex;
import android.util.Log;
import android.widget.ImageView;

import com.baidu.mapapi.SDKInitializer;
import com.bumptech.glide.Glide;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.util.AbstractDrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerImageLoader;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

import me.xihuxiaolong.generalcomponent.BuildConfig;
import me.xihuxiaolong.generalcomponent.R;
import me.xihuxiaolong.generalcomponent.common.dagger.component.AppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.component.DaggerAppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ApiServiceModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.AppModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.DatabaseManagerModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ImageServiceModule;
import timber.log.Timber;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@ReportsCrashes(
        httpMethod = HttpSender.Method.PUT,
        reportType = HttpSender.Type.JSON,
        formUri = "http://10.0.50.73:5984/acra-generalcomponent/_design/acra-storage/_update/report",
        formUriBasicAuthLogin = "yxl",
        formUriBasicAuthPassword = "123456"
)
public class MyApplication extends Application {

    private static MyApplication myApplication;

    AppComponent mAppComponent;

    public static MyApplication getInstance() {
        return myApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //内存泄露
        LeakCanary.install(this);

        //acra初始化
        ACRA.init(this);

        // 百度地图初始化
        SDKInitializer.initialize(this);

        //initialize and create the image loader logic
        DrawerImageLoader.init(new AbstractDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Glide.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Glide.clear(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx, String tag) {
                //define different placeholders for different imageView targets
                //default tags are accessible via the DrawerImageLoader.Tags
                //custom ones can be checked via string. see the CustomUrlBasePrimaryDrawerItem LINE 111
                if (DrawerImageLoader.Tags.PROFILE.name().equals(tag)) {
                    return DrawerUIUtils.getPlaceHolder(ctx);
                } else if (DrawerImageLoader.Tags.ACCOUNT_HEADER.name().equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(com.mikepenz.materialdrawer.R.color.primary).sizeDp(56);
                } else if ("customUrlItem".equals(tag)) {
                    return new IconicsDrawable(ctx).iconText(" ").backgroundColorRes(R.color.md_red_500).sizeDp(56);
                }

                //we use the default one for
                //DrawerImageLoader.Tags.PROFILE_DRAWER_ITEM.name()

                return super.placeholder(ctx, tag);
            }
        });

        myApplication = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .imageServiceModule(new ImageServiceModule())
                .databaseManagerModule(new DatabaseManagerModule())
                .build();

        //打开数据库连接
        mAppComponent.getMainDatabaseManager().initDatabase(-1L);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree(){
                @Override protected void log(int priority, String tag, String message, Throwable t) {
                    Logger.log(priority, tag, message, t);
                }
            });
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }

    public AppComponent getAppComponent(){
        return mAppComponent;
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            Logger.log(priority, tag, message, t);
//            FakeCrashLibrary.log(priority, tag, message);
//
//            if (t != null) {
//                if (priority == Log.ERROR) {
//                    FakeCrashLibrary.logError(t);
//                } else if (priority == Log.WARN) {
//                    FakeCrashLibrary.logWarning(t);
//                }
//            }
        }
    }
}
