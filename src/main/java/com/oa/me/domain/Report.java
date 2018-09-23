package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class Report {

    /**
     * 自增，PrimaryKey
     */
    private String id;
    /**
     * 汇报者ID
     */
    private String  uid;
    /**
     * 工作起始时间
     */
    private String  start_date;
    /**
     * 工作结束时间
     */
    private String  end_date;
    /**
     * 部长薪资意见
     */
    private String salary_sug;
    /**
     * 最终薪资
     */
    private String  salary;
    /**
     * 汇报时间戳
     */
    private String time_report;
    /**
     * 部长评分
     */
    private String rate;
    /**
     * 汇报内容
     */
    private String content;
    /**
     * 意见建议
     */
    private String suggestion;
    /**
     * 部长回评
     */
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getSalary_sug() {
        return salary_sug;
    }

    public void setSalary_sug(String salary_sug) {
        this.salary_sug = salary_sug;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getTime_report() {
        return time_report;
    }

    public void setTime_report(String time_report) {
        this.time_report = time_report;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
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
}
