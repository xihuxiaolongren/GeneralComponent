package me.xihuxiaolong.generalcomponent.common.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.database.manager.MainDatabaseManager;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class DatabaseManagerModule {

    @Provides
    @Singleton
    IMainDatabaseManager provideDataBaseManager() {
        return new MainDatabaseManager();
    }

}
