package com.oa.me.domain;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/20
 */
@Data
public class Dict {
    /**
     * 用于存储系统汇报信息的实体
     */
    private int key;
    private String value;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Dict{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
