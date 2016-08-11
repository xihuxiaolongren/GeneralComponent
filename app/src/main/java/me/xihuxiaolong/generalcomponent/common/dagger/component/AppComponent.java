package me.xihuxiaolong.generalcomponent.common.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import me.xihuxiaolong.generalcomponent.common.api.IDoubanApiService;
import me.xihuxiaolong.generalcomponent.common.api.ITXApiService;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ApiServiceModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.AppModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.DatabaseManagerModule;
import me.xihuxiaolong.generalcomponent.common.dagger.module.ImageServiceModule;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;
import me.xihuxiaolong.library.utils.ToastUtil;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Singleton
@Component(modules={AppModule.class, ApiServiceModule.class, ImageServiceModule.class, DatabaseManagerModule.class})
public interface AppComponent {

    ToastUtil getToastUtil();

    IDoubanApiService getDoubanApiService();

    ITXApiService getTXApiService();

    ImageService getImageService();

    IMainDatabaseManager getMainDatabaseManager();

}
