package com.oa.me.Entity;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/24
 */
public class JData<T> {
    private JCondition conditions;
    private List<T> members;
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public JCondition getConditions() {
        return conditions;
    }

    public void setConditions(JCondition conditions) {
        this.conditions = conditions;
    }

    public List<T> getMembers() {
        return members;
    }

    public void setMembers(List<T> members) {
        this.members = members;
    }
}
