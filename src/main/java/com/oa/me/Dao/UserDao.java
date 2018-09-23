package com.oa.me.Dao;

import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;
import com.oa.me.controller.UserController;
import com.oa.me.domain.User;
import com.oa.me.domain.UserModel;
import com.oa.me.domain.UserOa;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by chenjiehao on 2018/9/17
 */
@Mapper
public interface UserDao {
    /**
     * 获取登录的用户属性
     * @param user
     * @return
     */
    @Select("select id,avatar_url,stuid,name,depart,role from oa_member where stuid =#{user.stuid} and pwd = #{user.pwd}")
    public UserOa getUserLogin(@Param("user")User user);

    /**
     * 获取登录用户的盐
     * @param userModel
     * @return
     */
    @Select("select salt from oa_member where stuid =#{userModel.username}")
    public String getUserSalt(@Param("userModel")UserModel userModel);


    /**
     * 获取所有用户
     * @return
     */
    @Select("select id,stuid,name,sex,depart,debitcard,role,status,join_time,exit_time,phone,email,qq,campus,college,major from oa_member")
    List<User> getAllUser();

    /**
     * 通过Stuid获取用户详细信息
     * @param stuid
     * @return
     */
    @Select("select id,stuid,name,sex,depart,debitcard,role,status,join_time,exit_time,phone,email,qq,campus,college,major from oa_member where stuid=#{stuid}")
    User getUserByStuid(String stuid);

    /**
     *
     * 获取用户对应的角色
     */
    @Select("SELECT r.role  FROM sys_user_role ur LEFT JOIN sys_role r ON ur.role_id=r.id WHERE ur.stuid=#{stuid}")
    Integer queryAllRoles(int stuid);
    /**
     * 通过用户名（学号）获取用户的相应验证信息
     *
     */
    @Select("select id,stuid,pwd,salt from oa_member where stuid = #{username}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username", column = "stuid"),
            @Result(property = "password", column = "pwd"),
            @Result(property = "salt", column = "salt")
    })
    SysUser findSysUserByUsername(String username);


    @Select("select * from sys_role where id in (select role_id from sys_member_role where stuid=#{username})")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark")
    })
    SysRole getSysUserRoleList(String username);


    @Select("select * from oa_member where " +
            "id  like '%${anything}%' or " +
            "stuid  like '%${anything}%' or " +
            "name  like '%${anything}%' or " +
            "sex  like '%${anything}%' or " +
            "depart like '%${anything}%' or " +
            "debitcard  like '%${anything}%' or " +
            "phone  like '%${anything}%' or " +
            "email  like '%${anything}%' or " +
            "qq  like '%${anything}%' or " +
            "college  like '%${anything}%' or " +
            "campus  like '%${anything}%' or " +
            "major  like '%${anything}%' " )
    List<User> getUserByAny(@Param("anything") String anything);


    @Update("update oa_member set name=#{name}," +
            "depart=#{depart}," +
            "phone=#{phone}," +
            "qq=#{qq}," +
            "campus=#{campus}," +
            "role=#{role}," +
            "status=#{status}," +
            "debitcard=#{debitcard}," +
            "email=#{email} where stuid = #{stuid}")
    long updateMember(User user);


    @Select("select * from oa_member where depart =#{depart}")
    List<User> getUserListByDepart(@Param("depart") String depart);
}
