package me.xihuxiaolong.generalcomponent.common.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.xihuxiaolong.generalcomponent.common.image.ImageService;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class ImageServiceModule {

    @Provides
    @Singleton
    ImageService provideImageService() {
        return new ImageService();
    }

}
