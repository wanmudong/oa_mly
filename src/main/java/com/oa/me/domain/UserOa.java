package com.oa.me.domain;

/**
 * Created by chenjiehao on 2018/9/18
 */
public class UserOa {
//id,avatar_url,stuid,name,depart,role
    /**
     * 返回给前端的用户实体
     */
    private int id;
//    private String avatar;
    private int stuid;
    private String name;
    private int depart;
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
