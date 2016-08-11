package me.xihuxiaolong.generalcomponent.shortnotelist;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class ShortNoteListFragmentPresenter extends MvpBasePresenter<IMvpLceListView<List<DBShortNote>, List<DBShortNote>>>
        implements MvpPresenter<IMvpLceListView<List<DBShortNote>, List<DBShortNote>>> {

    public static final int CREATE = 1;
    public static final int REFRESH = 2;
    public static final int LOADMORE = 3;

    Long ltId;
    int count;

    @Inject
    IMainDatabaseManager mainDatabaseManager;

    @Inject
    public ShortNoteListFragmentPresenter() {
        count = 10;

    }

    public void loadShortNotes(int status){
        List<DBShortNote> dbShortNotes;
        if(status == CREATE) {
            ltId = null;
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                getView().setData(dbShortNotes);
                if(dbShortNotes != null && !dbShortNotes.isEmpty()) {
                    ltId = dbShortNotes.get(0).getId();
                }
                getView().showContent();
            }
        } else if(status == REFRESH) {
            ltId = null;
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                getView().setData(dbShortNotes);
            }
        } else if(status == LOADMORE) {
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                getView().setMoreData(dbShortNotes);
                if(dbShortNotes != null && !dbShortNotes.isEmpty())
                    ltId = dbShortNotes.get(0).getId();
                getView().showContent();
            }
        }
    }

}
