package me.xihuxiaolong.generalcomponent.common.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/2.
 */
public class DoubanHttpResult<T> {

    private int count;

    private int start;

    private int total;

    private T subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getSubjects() {
        return subjects;
    }

    public void setSubjects(T subjects) {
        this.subjects = subjects;
    }

}
