package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/18
 */
public class UserOa {
    /**
     * 返回给前端的用户的实体
     */

    /**
     * 成员id
     */
    private int id;
    /**
     * 成员学号
     */
    private int stuid;
    /**
     * 成员名称
     */
    private String name;
    /**
     * 部门
     */
    private int depart;
    /**
     * 角色
     */
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

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

    public int getDepart() {
        return depart;
    }

    public void setDepart(int depart) {
        this.depart = depart;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserOa{" +
                "id=" + id +
//                ", avatar='" + avatar + '\'' +
                ", stuid=" + stuid +
                ", name='" + name + '\'' +
                ", depart=" + depart +
                ", role=" + role +
                '}';
    }
}
