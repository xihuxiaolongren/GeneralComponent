package me.xihuxiaolong.generalcomponent.shortnoteedit;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.event.Event;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/10.
 */
public class ShortNoteEditFragmentPresenter extends MvpBasePresenter<ShortNoteEditContract.IView> implements ShortNoteEditContract.IPresenter {

    @Inject
    Long shortNoteId;

    @Inject
    IMainDatabaseManager mainDatabaseManager;

    @Inject
    public ShortNoteEditFragmentPresenter() {
    }

    @Override
    public void loadShortNote() {
        if(shortNoteId != -1L) {
            DBShortNote dbShortNote = mainDatabaseManager.getShortNoteById(shortNoteId);
            if (isViewAttached()) {
                getView().setText(dbShortNote.getText());
                getView().showDeleteMenu(true);
            }
        } else{
            if (isViewAttached()) {
                getView().showDeleteMenu(false);
            }
        }
    }

    @Override
    public void saveShortNote(String text) {
        DBShortNote shortNote = new DBShortNote();
        shortNote.setText(text);
        if(shortNoteId != -1L) {
            shortNote.setId(shortNoteId);
            EventBus.getDefault().post(new Event.UpdateShortNote(shortNote));
        }else {
            long shortNoteId = mainDatabaseManager.insertOrReplaceShortNote(shortNote);
            shortNote.setId(shortNoteId);
            EventBus.getDefault().post(new Event.AddShortNote(shortNote));
        }
        if (isViewAttached())
            getView().saveSuccess();
    }

    @Override
    public void deleteShortNote() {
        if(shortNoteId != -1L) {
            mainDatabaseManager.deleteShortNoteById(shortNoteId);
            EventBus.getDefault().post(new Event.DeleteShortNote(shortNoteId));
            if (isViewAttached())
                getView().deleteSuccess();
        }
    }

    @Override
    public void generateShareShortNote() {

    }

}
