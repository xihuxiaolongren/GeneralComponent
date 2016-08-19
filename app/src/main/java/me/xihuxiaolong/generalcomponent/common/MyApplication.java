package me.xihuxiaolong.generalcomponent.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.orhanobut.logger.Logger;

import org.acra.ACRA;
import org.acra.annotation.ReportsCrashes;
import org.acra.sender.HttpSender;

import me.xihuxiaolong.generalcomponent.BuildConfig;
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

        //acra初始化
        ACRA.init(this);

        myApplication = this;
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiServiceModule(new ApiServiceModule())
                .imageServiceModule(new ImageServiceModule())
                .databaseManagerModule(new DatabaseManagerModule())
                .build();
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
