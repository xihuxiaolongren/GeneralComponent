package me.xihuxiaolong.generalcomponent.common.database.localentity;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBCACHE".
*/
public class DBCacheDao extends AbstractDao<DBCache, Void> {

    public static final String TABLENAME = "DBCACHE";

    /**
     * Properties of entity DBCache.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Key = new Property(0, String.class, "key", false, "KEY");
        public final static Property Value = new Property(1, String.class, "value", false, "VALUE");
        public final static Property CreateTime = new Property(2, long.class, "createTime", false, "CREATE_TIME");
        public final static Property Expire = new Property(3, int.class, "expire", false, "EXPIRE");
    };


    public DBCacheDao(DaoConfig config) {
        super(config);
    }
    
    public DBCacheDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBCACHE\" (" + //
                "\"KEY\" TEXT," + // 0: key
                "\"VALUE\" TEXT," + // 1: value
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 2: createTime
                "\"EXPIRE\" INTEGER NOT NULL );"); // 3: expire
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_DBCACHE_KEY ON DBCACHE" +
                " (\"KEY\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBCACHE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DBCache entity) {
        stmt.clearBindings();
 
        String key = entity.getKey();
        if (key != null) {
            stmt.bindString(1, key);
        }
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getExpire());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DBCache entity) {
        stmt.clearBindings();
 
        String key = entity.getKey();
        if (key != null) {
            stmt.bindString(1, key);
        }
 
        String value = entity.getValue();
        if (value != null) {
            stmt.bindString(2, value);
        }
        stmt.bindLong(3, entity.getCreateTime());
        stmt.bindLong(4, entity.getExpire());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public DBCache readEntity(Cursor cursor, int offset) {
        DBCache entity = new DBCache( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // key
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // value
            cursor.getLong(offset + 2), // createTime
            cursor.getInt(offset + 3) // expire
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DBCache entity, int offset) {
        entity.setKey(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setValue(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreateTime(cursor.getLong(offset + 2));
        entity.setExpire(cursor.getInt(offset + 3));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(DBCache entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(DBCache entity) {
        return null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}