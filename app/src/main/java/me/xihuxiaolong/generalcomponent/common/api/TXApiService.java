package me.xihuxiaolong.generalcomponent.common.api;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.model.City;
import me.xihuxiaolong.generalcomponent.common.model.TXHttpResult;
import rx.Subscriber;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/3.
 */
public interface TXApiService {

    public void getCityList(Subscriber<List<List<City>>> subscriber);
}
