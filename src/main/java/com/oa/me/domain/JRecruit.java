package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/22
 */
public class JRecruit {
   private int id;
    private String name;
    private int stuid;
    private String  sex;
    private String phone;
    private String qq;
    private int depart;
    private String  time;
    private int  status;
    private int  current;
    private int  campus;
    private String college;
    private String  major;
    private String  say;
    private RecruitStatus recruitStatus;
    private String start_date;
    private String end_date;

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

    public RecruitStatus getRecruitStatus() {
        return recruitStatus;
    }

    public void setRecruitStatus(RecruitStatus recruitStatus) {
        this.recruitStatus = recruitStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getStuid() {
//        return stuid;
//    }
//
//    public void setStuid(String stuid) {
//        this.stuid = stuid;
//    }


    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

//    public String getDepart() {
//        return depart;
//    }
//
//    public void setDepart(String depart) {
//        this.depart = depart;
//    }


    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

//    public String getCampus() {
//        return campus;
//    }
//
//    public void setCampus(String campus) {
//        this.campus = campus;
//    }

    public int getCampus() {
        return campus;
    }

    public void setCampus(int campus) {
        this.campus = campus;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say;
    }
}
