package me.xihuxiaolong.generalcomponent.module.citylist;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.model.City;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/4.
 */
public interface CityListContract {

    interface IView extends MvpLceView<List<List<City>>> {

        void setQuickSideBarTipsData(List<String> letters);

        void scrollToPosition(int position);
    }

    interface IPresenter extends MvpPresenter<IView> {

        void loadCityData();
    }
}
