package me.xihuxiaolong.generalcomponent.doubanmovielist;

import dagger.Component;
import me.xihuxiaolong.generalcomponent.common.dagger.component.AppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.scope.ActivityScope;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface DoubanMovieListFragmentComponent{

    public void inject(DoubanMovieListFragment doubanMovieListFragment);

}
