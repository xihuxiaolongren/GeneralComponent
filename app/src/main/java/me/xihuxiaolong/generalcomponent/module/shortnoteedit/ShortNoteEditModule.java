package me.xihuxiaolong.generalcomponent.module.shortnoteedit;

import dagger.Module;
import dagger.Provides;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
@Module
public class ShortNoteEditModule {

    private Long shortNoteId;

    public ShortNoteEditModule(Long shortNoteId){
        this.shortNoteId = shortNoteId;
    }

    @Provides
    Long provideShortNoteId() {
        return shortNoteId;
    }

}