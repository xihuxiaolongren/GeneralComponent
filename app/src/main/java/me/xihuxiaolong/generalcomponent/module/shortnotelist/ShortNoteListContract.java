package me.xihuxiaolong.generalcomponent.module.shortnotelist;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/12.
 */
public class ShortNoteListContract {

    interface IView extends IMvpLceListView<List<DBShortNote>> {

        void removeShortNoteItem(long shortNoteId);

        void addShortNoteItem(DBShortNote shortNote);

        void updateShortNoteItem(DBShortNote shortNote);

    }

    interface IPresenter extends MvpPresenter<IView> {

        void loadShortNotes();
        void reloadShortNotes();
        void loadmoreShortNotes();

    }
}
