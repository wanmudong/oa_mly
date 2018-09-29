package com.oa.me.domain;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class RRecruitStatus<T> {
    /**、
     * 当前面试状态
     */
    private  int current;
    /**
     * 当前考核状态
     * 1，未通过  normal。2，通过
     */
    private String status;
    /**
     * 面试各轮情况
     */
    private List<T> steps;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public List<T> getSteplist() {
//        return steplist;
//    }
//
//    public void setSteplist(List<T> steplist) {
//        this.steplist = steplist;
//    }

    public List<T> getSteps() {
        return steps;
    }

    public void setSteps(List<T> steps) {
        this.steps = steps;
    }
}
