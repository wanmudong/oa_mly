package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/27
 */
public class Salary {
    /**
     * 薪酬的实体
     */

    /**
     * 最终薪资
     */
    private int fina;

    /**
     * 建议薪资
     */
    private int review;

    public int getFina() {
        return fina;
    }

    public void setFina(int fina) {
        this.fina = fina;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }
}
