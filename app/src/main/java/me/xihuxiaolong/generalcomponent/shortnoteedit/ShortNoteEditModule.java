package me.xihuxiaolong.generalcomponent.shortnoteedit;

import android.content.Context;
import android.support.annotation.Nullable;

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