package com.oa.me.domain;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Data
public class RecruitStatus<T> {
    /**、
     * 当前面试状态
     */
    private  int current;
    /**
     * 当前考核状态
     * 1，未通过  normal。2，通过
     */
    private int status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
