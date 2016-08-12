package me.xihuxiaolong.generalcomponent.shortnotelist;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.event.Event;
import me.xihuxiaolong.generalcomponent.common.mvp.IMvpLceListView;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/7/6.
 */
public class ShortNoteListFragmentPresenter extends MvpBasePresenter<ShortNoteListContract.IView>
        implements ShortNoteListContract.IPresenter {

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

    @Override
    public void loadShortNotes(int status) {
        List<DBShortNote> dbShortNotes;
        if(status == CREATE) {
            ltId = null;
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                if(dbShortNotes != null && !dbShortNotes.isEmpty()) {
                    ltId = dbShortNotes.get(dbShortNotes.size() - 1).getId();
                }
                getView().setData(dbShortNotes);
                getView().showContent();
            }
        } else if(status == REFRESH) {
            ltId = null;
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                if(dbShortNotes != null && !dbShortNotes.isEmpty()) {
                    ltId = dbShortNotes.get(0).getId();
                }
                getView().setData(dbShortNotes);
                getView().showContent();
            }
        } else if(status == LOADMORE) {
            dbShortNotes = mainDatabaseManager.listDBShortNotes(ltId, count);
            if (isViewAttached()) {
                getView().setMoreData(dbShortNotes);
                if(dbShortNotes != null && !dbShortNotes.isEmpty())
                    ltId = dbShortNotes.get(dbShortNotes.size() - 1).getId();
                getView().showContent();
            }
        }
    }

    @Override public void attachView(ShortNoteListContract.IView view) {
        super.attachView(view);
        EventBus.getDefault().register(this);
    }

    @Override public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(Event.DeleteShortNote event) {
        if(isViewAttached())
            getView().removeShortNoteItem(event.shortNoteId);
    }

    @Subscribe
    public void onEvent(Event.AddShortNote event) {
        if(isViewAttached())
            getView().addShortNoteItem(event.shortNote);
    }

    @Subscribe
    public void onEvent(Event.UpdateShortNote event) {
        if(isViewAttached())
            getView().updateShortNoteItem(event.shortNote);
    }

}
