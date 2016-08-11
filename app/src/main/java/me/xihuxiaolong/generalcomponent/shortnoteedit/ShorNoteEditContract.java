package me.xihuxiaolong.generalcomponent.shortnoteedit;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/10.
 */
public class ShorNoteEditContract {

    interface IView extends MvpView {

        void setTitle(String title);

        void saveSuccess();

        void deleteSuccess();

        void shareShortNote();

    }

    interface IPresenter extends MvpPresenter<IView> {

        void saveShortNote(String text);

        void deleteShortNote();

        void generateShareShortNote();

    }
}
