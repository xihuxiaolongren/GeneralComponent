package me.xihuxiaolong.generalcomponent.common.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.xihuxiaolong.generalcomponent.common.api.IDoubanApiService;
import me.xihuxiaolong.generalcomponent.common.api.DoubanApiService;
import me.xihuxiaolong.generalcomponent.common.api.ITXApiService;
import me.xihuxiaolong.generalcomponent.common.api.TXApiService;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class ApiServiceModule {

    @Provides
    @Singleton
    IDoubanApiService provideDoubanApiService() {
        return new DoubanApiService();
    }

    @Provides
    @Singleton
    ITXApiService provideTxApiService() {
        return new TXApiService();
    }
}
