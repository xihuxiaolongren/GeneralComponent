package me.xihuxiaolong.generalcomponent.common.database.localentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/6.
 */
@Entity
public class DBCache {

    @Index(unique = true)
    private String key;

    private String value;

    private long createTime;

    private int expire;

    public int getExpire() {
        return this.expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Generated(hash = 1065873984)
    public DBCache(String key, String value, long createTime, int expire) {
        this.key = key;
        this.value = value;
        this.createTime = createTime;
        this.expire = expire;
    }

    @Generated(hash = 2006200633)
    public DBCache() {
    }

}
