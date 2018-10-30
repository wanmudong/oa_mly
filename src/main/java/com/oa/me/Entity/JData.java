package com.oa.me.Entity;

import lombok.Data;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/24
 */
@Data
public class JData<T> {
    /**
     * 返回的数据的实体
     */


    /**
     * 请求的条件
     */
    private JCondition conditions;

    /**
     * 请求的成员列表
     */
    private List<T> members;

    /**
     * 请求的数据
     */
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
