package me.xihuxiaolong.generalcomponent.common.api;

import android.content.Context;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.model.Subject;
import rx.Subscriber;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/3.
 */
public interface IDoubanApiService {

    public void getTopMovie(Context context, Subscriber<List<Subject>> subscriber, int start, int count);
}
