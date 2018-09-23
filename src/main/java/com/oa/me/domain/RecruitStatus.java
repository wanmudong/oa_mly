package com.oa.me.domain;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class RecruitStatus<T> {
    /**、
     * 当前面试状态
     */
    private  int current;
    /**
     * 当前考核状态
     * 1，通过。2，未通过
     */
    private int status;
    /**
     * 面试各轮情况
     */
    private List<T> steplist;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<T> getSteplist() {
        return steplist;
    }

    public void setSteplist(List<T> steplist) {
        this.steplist = steplist;
    }
}
