package com.oa.me.domain;

import lombok.Data;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Data
public class User {
    /**
     *     用于和数据库交互的成员的实体
     */

    //成员id
    private int id;
    //学号
    private int stuid;
    //姓名
    private String  name;
    //性别
    private String  sex;
    //密码
    private String  pwd;
    //盐值
    private String  salt;
    //部门
    private int depart;
    //银行卡
    private String debitcard;
    //角色
    private int role;
   // private String avatar_url;
    //状态
    private int status;
    //加入时间
    private  int join_time;
    //退出时间
    private int exit_time;
    //电话
    private  String phone;
    //邮箱
    private String email;
    //qq号
    private String qq;
    //校区
    private int campus;
    //学院
    private String college;
    //专业
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
