package me.xihuxiaolong.generalcomponent.module.shortnoteedit;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/10.
 */
public class ShortNoteEditContract {

    interface IView extends MvpView {

        void showDeleteMenu(boolean visible);

        void setText(String text);

        void saveSuccess();

        void deleteSuccess();

        void shareShortNote();

    }

    interface IPresenter extends MvpPresenter<IView>{

        void loadShortNote();

        void saveShortNote(String text);

        void deleteShortNote();

        void generateShareShortNote();

    }
}
