package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/17
 */
public class User {
    //用于和数据库交互的用户实体
    private int id;
    private int stuid;
    private String  name;
    private String  sex;
    private String  pwd;
    private String  salt;
    private int depart;
    private String debitcard;
    private int role;
   // private String avatar_url;
    private int status;
    private  int join_time;
    private int exit_time;
    private  String phone;
    private String email;
    private String qq;
    private int campus;
    private String college;
    private String major;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStuid() {
        return stuid;
    }

    public void setStuid(int stuid) {
        this.stuid = stuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public String getDebitcard() {
        return debitcard;
    }

    public void setDebitcard(String debitcard) {
        this.debitcard = debitcard;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

//    public String getAvatar_url() {
//        return avatar_url;
//    }
//
//    public void setAvatar_url(String avatar_url) {
//        this.avatar_url = avatar_url;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getJoin_time() {
        return join_time;
    }

    public void setJoin_time(int join_time) {
        this.join_time = join_time;
    }

    public int getExit_time() {
        return exit_time;
    }

    public void setExit_time(int exit_time) {
        this.exit_time = exit_time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", stuid=" + stuid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", pwd='" + pwd + '\'' +
                ", salt='" + salt + '\'' +
                ", depart=" + depart +
                ", debitcard='" + debitcard + '\'' +
                ", role=" + role +
//                ", avatar_url='" + avatar_url + '\'' +
                ", status=" + status +
                ", join_time=" + join_time +
                ", exit_time=" + exit_time +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", campus=" + campus +
                ", college='" + college + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
