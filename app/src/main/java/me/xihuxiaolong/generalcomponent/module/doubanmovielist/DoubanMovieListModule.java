package me.xihuxiaolong.generalcomponent.module.doubanmovielist;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class DoubanMovieListModule {

    private final Context context;

    public DoubanMovieListModule(Context context){
        this.context = context;
    }

    @Provides
    public Context provideContext(){
        return context;
    }

}