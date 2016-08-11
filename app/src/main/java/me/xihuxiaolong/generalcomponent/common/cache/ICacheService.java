package me.xihuxiaolong.generalcomponent.common.cache;

public interface ICacheService {

	void delete(String key) ;

	String get(String key);

	long put(String key, Object value);

	long put(String key, Object value, int expireTime);

}
