package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class Setting {
    private String  id;
    /**
     * 对应工作开始时间，格式：2016-09-15
     */
    private String start_date;
    /**
     * 对应工作结束时间，格式同上
     */
    private String end_date;
    /**
     * 汇报内容
     */
    private String content;
    /**
     * 意见建议
     */
    private String suggestion;
    /**
     * 部长评价
     */
    private String comment;
    /**
     * 部长评分
     */
    private String rate;
    /**
     * 最终薪资
     */
    private String  salary;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
