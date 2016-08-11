package me.xihuxiaolong.generalcomponent.common.database.manager;

import java.util.ArrayList;
import java.util.List;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBCache;
import me.xihuxiaolong.generalcomponent.common.database.localentity.DBShortNote;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/8.
 */
public interface IMainDatabaseManager {

    void initDatabase(Long userId);

    void deleteCacheByKey(String key);

    DBCache getCacheByKey(String key);

    long insertOrReplaceCache(DBCache dbCache);

    void deleteShortNoteById(Long id);

    DBShortNote getShortNoteById(Long id);

    long insertOrReplaceShortNote(DBShortNote dbShortNote);

    List<DBShortNote> listDBShortNotes(Long id, int count);

}
