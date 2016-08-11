package me.xihuxiaolong.generalcomponent.common.database.manager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import me.xihuxiaolong.generalcomponent.BuildConfig;
import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBCache;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBCacheDao;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNoteDao;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DaoMaster;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DaoSession;

/**
 * Created by yangxiaolong on 15/11/2.
 */
public class MainDatabaseManager implements IMainDatabaseManager {

    private static DaoSession daoSession;

    private static DaoMaster.OpenHelper helper;

    private static SQLiteDatabase database;
    private static DaoMaster daoMaster;

    private static final String MAIN_DB_NAME_SUFFIX = "_main_db";

    public static MainDatabaseManager getInstance(){
        return SingletonHolder.instance;
    }

    /**
     * 打开数据库连接
     */
    public void initDatabase(Long userId) {
        closeDatabase();
        if(BuildConfig.DEBUG)
            helper = new OnlineOpenHelper(MyApplication.getInstance(), userId + MAIN_DB_NAME_SUFFIX, null);
        else
            helper = new OnlineOpenHelper(MyApplication.getInstance(), userId + MAIN_DB_NAME_SUFFIX, null);

        database = helper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    @Override
    public void deleteCacheByKey(String key) {
        daoSession.getDBCacheDao().queryBuilder()
                .where(DBCacheDao.Properties.Key.eq(key))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
    }

    @Override
    public DBCache getCacheByKey(String key) {
        return daoSession.getDBCacheDao().queryBuilder()
                .where(DBCacheDao.Properties.Key.eq(key)).unique();
    }

    @Override
    public long insertOrReplaceCache(DBCache dbCache) {
        return daoSession.getDBCacheDao().insertOrReplace(dbCache);
    }

    @Override
    public void deleteShortNoteById(Long id) {
        daoSession.getDBShortNoteDao().deleteByKey(id);
    }

    @Override
    public DBShortNote getShortNoteById(Long id) {
        return daoSession.getDBShortNoteDao().queryBuilder()
                .where(DBShortNoteDao.Properties.Id.eq(id)).unique();
    }

    @Override
    public long insertOrReplaceShortNote(DBShortNote dbShortNote) {
        return daoSession.getDBShortNoteDao().insertOrReplace(dbShortNote);
    }

    @Override
    public List<DBShortNote> listDBShortNotes(Long id, int count) {
        if(id == null)
            return daoSession.getDBShortNoteDao().queryBuilder().orderDesc().limit(count).list();
        else
            return daoSession.getDBShortNoteDao().queryBuilder()
                    .where(DBShortNoteDao.Properties.Id.lt(id)).orderDesc().limit(count).list();
    }

    private MainDatabaseManager(){}

    private static class SingletonHolder{
        /** 单例变量  */
        private static MainDatabaseManager instance = new MainDatabaseManager();
    }

    /**
     * 关闭当前数据库连接
     */
    private void closeDatabase(){
        if(daoSession != null){
            daoSession.clear();
            daoSession = null;
        }
        if(database != null && database.isOpen())
            database.close();
        if(helper != null){
            helper.close();
            helper = null;
        }
    }

    private class OnlineOpenHelper  extends DaoMaster.OpenHelper {

        public OnlineOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            switch (oldVersion){
                default:
            }
        }

    }
}
