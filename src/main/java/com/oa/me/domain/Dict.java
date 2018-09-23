package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/20
 */
public class Dict {
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
