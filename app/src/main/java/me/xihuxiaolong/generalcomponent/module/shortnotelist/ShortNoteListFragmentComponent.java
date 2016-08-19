package me.xihuxiaolong.generalcomponent.module.shortnotelist;

import dagger.Component;
import me.xihuxiaolong.generalcomponent.common.dagger.component.AppComponent;
import me.xihuxiaolong.generalcomponent.common.dagger.scope.ActivityScope;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = {ShortNoteListModule.class})
public interface ShortNoteListFragmentComponent {

    void inject(ShortNoteListFragment shortNoteListFragment);

    ShortNoteListFragmentPresenter presenter();
}
