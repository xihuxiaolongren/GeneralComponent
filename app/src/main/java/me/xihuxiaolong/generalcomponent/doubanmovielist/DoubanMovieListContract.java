package me.xihuxiaolong.generalcomponent.doubanmovielist;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.model.City;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/4.
 */
public interface DoubanMovieListContract {

    interface IView extends IMvpLceListView<List<Subject>> {

    }

    interface IPresenter extends MvpPresenter<IView> {

        void loadMovie();

        void reLoadMovie();

        void loadMoreMovie();
    }
}
