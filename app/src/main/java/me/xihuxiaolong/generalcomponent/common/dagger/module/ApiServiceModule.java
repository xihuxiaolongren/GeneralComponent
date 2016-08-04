package me.xihuxiaolong.generalcomponent.common.dagger.module;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.xihuxiaolong.generalcomponent.common.api.DoubanApiService;
import me.xihuxiaolong.generalcomponent.common.api.DoubanApiServiceImpl;
import me.xihuxiaolong.generalcomponent.common.api.TXApiService;
import me.xihuxiaolong.generalcomponent.common.api.TXApiServiceImpl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    DoubanApiService provideDoubanApiService() {
        return new DoubanApiServiceImpl();
    }

    @Provides
    @Singleton
    TXApiService provideTxApiService() {
        return new TXApiServiceImpl();
    }
}
