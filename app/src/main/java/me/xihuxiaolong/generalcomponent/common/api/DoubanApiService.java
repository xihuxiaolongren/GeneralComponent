package me.xihuxiaolong.generalcomponent.common.api;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.model.Subject;
import rx.Subscriber;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/3.
 */
public interface DoubanApiService {

    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count);
}
