package me.xihuxiaolong.generalcomponent.common.dagger.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.xihuxiaolong.generalcomponent.common.api.DoubanApiService;
import me.xihuxiaolong.generalcomponent.common.api.TXApiService;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ApiServiceModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.AppModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ImageServiceModule;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.library.utils.ToastUtil;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Singleton
@Component(modules={AppModule.class, ApiServiceModule.class, ImageServiceModule.class})
public interface AppComponent {

    ToastUtil getToastUtil();

    DoubanApiService getDoubanApiService();

    TXApiService getTXApiService();

    ImageService getImageService();

}
