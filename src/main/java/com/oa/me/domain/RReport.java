package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class RReport {

    /**
     * 自增，PrimaryKey
     */
    private int id;

    /**
     * 工作起始时间
     */
    private String  start_date;
    /**
     * 工作结束时间
     */
    private String  end_date;
    /**
     * 薪资
     */
    private  Salary salary;
    /**
     * 最终薪资
     */
    private  int salary0;
    /**
     * 建议薪资
     */
    private  int salary1;

    /**
     * 部长评分
     */
    private float rate;
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
    /**
     * 用户名
     */
    private String username;
    /**
     * 部门
     */
    private String depart;
    /**
     * 学号
     */
    private String stuid;
    /**
     * 性别
     */
    private String sex;
    /**
     * 学院
     */
    private String college;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 银行卡号
     */
    private String debitcard;
    /**
     * 汇报时间
     */
    private String time_report;

    public String getTime_report() {
        return time_report;
    }

    public void setTime_report(String time_report) {
        this.time_report = time_report;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDebitcard() {
        return debitcard;
    }

    public void setDebitcard(String debitcard) {
        this.debitcard = debitcard;
    }

    private String status;

    public int getSalary0() {
        return salary0;
    }

    public void setSalary0(int salary0) {
        this.salary0 = salary0;
    }

    public int getSalary1() {
        return salary1;
    }

    public void setSalary1(int salary1) {
        this.salary1 = salary1;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
