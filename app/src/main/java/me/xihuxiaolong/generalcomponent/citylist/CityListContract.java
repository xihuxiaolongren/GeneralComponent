package me.xihuxiaolong.generalcomponent.citylist;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/4.
 */
public interface CityListContract {

    interface View<M> extends MvpLceView<M> {

        void setQuickSideBarTipsData(List<String> letters);

        void scrollToPosition(int position);
    }
}
