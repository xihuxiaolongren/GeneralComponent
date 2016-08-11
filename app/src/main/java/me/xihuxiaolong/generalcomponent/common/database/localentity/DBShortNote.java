package me.xihuxiaolong.generalcomponent.common.database.localentity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/8.
 */
@Entity
public class DBShortNote {

    @Id(autoincrement = true)
    private Long id;

    private String text;

    private long createdTime;

    private long modifiedTime;

    public long getModifiedTime() {
        return this.modifiedTime;
    }

    public void setModifiedTime(long modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public long getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Generated(hash = 412185915)
    public DBShortNote(Long id, String text, long createdTime, long modifiedTime) {
        this.id = id;
        this.text = text;
        this.createdTime = createdTime;
        this.modifiedTime = modifiedTime;
    }

    @Generated(hash = 1440106287)
    public DBShortNote() {
    }
}
