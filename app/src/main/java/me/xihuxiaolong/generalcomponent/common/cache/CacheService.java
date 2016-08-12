package me.xihuxiaolong.generalcomponent.common.cache;

import com.google.gson.Gson;

import me.xihuxiaolong.generalcomponent.common.database.localentity.DBCache;
import me.xihuxiaolong.generalcomponent.common.database.manager.MainDatabaseManager;
import me.xihuxiaolong.generalcomponent.common.database.manager.IMainDatabaseManager;

public class CacheService implements ICacheService {

	public static final int MONTH_EXPIRE = 30 * 24 * 60 * 60;
	public static final int WEEK_EXPIRE = 7 * 24 * 60 * 60;
	public static final int DAY_EXPIRE = 24 * 60 * 60;
	public static final int HOUR_EXPIRE = 60 * 60;
	public static final int HALF_HOUR_EXPIRE = 30 * 60;
	public static final int MINUTES_EXPIRE = 60;

	public static class Keys{
		public static final String commonCityList = "commonCityList";
	}


	private static CacheService sInstance;

	private static Gson gson = new Gson();

	private IMainDatabaseManager databaseManager;

	public CacheService(){
//		databaseManager = MainDatabaseManager.getInstance();
	}

	@Override
	public void delete(String key) {
		databaseManager.deleteCacheByKey(key);
	}

	@Override
	public String get(String key) {
		DBCache dbCache = databaseManager.getCacheByKey(key);
		if(dbCache == null)
			return null;
		if(dbCache.getExpire() != -1 && ((Math.abs(System.currentTimeMillis() - dbCache.getCreateTime())) / 1000) - dbCache.getExpire() > 0){
			delete(key);
			return null;
		}
		return dbCache.getValue();
	}

	public long put(String key, Object value) {
		return databaseManager.insertOrReplaceCache(new DBCache(key, gson.toJson(value), System.currentTimeMillis(), -1));
	}

	public long put(String key, Object value, int expireTime) {
		return databaseManager.insertOrReplaceCache(new DBCache(key, gson.toJson(value), System.currentTimeMillis(), expireTime));
	}

}
