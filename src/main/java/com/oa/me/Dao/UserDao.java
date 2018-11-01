package com.oa.me.Dao;

import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;
import com.oa.me.controller.UserController;
import com.oa.me.domain.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * 获取用户列表
     * @return
     */
    @Select("select id,stuid,name,sex,depart,debitcard,role,status,join_time,exit_time,phone,email,qq,campus,college,major from oa_member order by id")
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
    @Select("select id,stuid,pwd,salt,role,depart,campus from oa_member where stuid = #{username}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "username", column = "stuid"),
            @Result(property = "password", column = "pwd"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "role", column = "role"),
            @Result(property = "depart", column = "depart"),
            @Result(property = "campus", column = "campus")
    })
    SysUser findSysUserByUsername(String username);

    /**
     * 获取用户名对应的角色列表
     * @param username
     * @return
     */
    @Select("select * from sys_role where id in (select role_id from sys_member_role where stuid=#{username})")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "remark", column = "remark")
    })
    SysRole getSysUserRoleList(String username);

    /**
     * 通过通过content获取成员信息列表
     * @param content
     * @return
     */
    @Select("select * from oa_member where " +
            "(stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and status=1  "
                )
    List<User> getUserByContent(@Param("content") String content);

    /**
     * 通过通过content获取成员信息列表
     */
    @Select("select * from oa_member where " +
            "(stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%')  "
    )
    List<User> getUserByContentAdmin(@Param("content") String content);

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Update("update oa_member set name=#{name}," +
            "depart=#{depart}," +
            "phone=#{phone}," +
            "qq=#{qq}," +
            "campus=#{campus}," +
            "role=#{role}," +
            "status=#{status}," +
            "debitcard=#{debitcard}," +
            "pwd=#{pwd}," +
            "email=#{email} where stuid = #{stuid} ")
    long updateMember(User user);

    /**
     * 通过部门获取成员列表
     * @param depart
     * @return
     */
    @Select("select * from oa_member where depart =#{depart} and status=1")
    List<User> getUserListByDepart(@Param("depart") String depart);

//    @Select("select * from oa_member where( " +
////            "id  like '%${anything}%' or " +
//            "stuid  like '%${content}%' or " +
//            "name  like '%${content}%' or " +
////            "sex  like '%${anything}%' or " +
////            "depart like '%${anything}%' or " +
////            "debitcard  like '%${anything}%' or " +
////            "email  like '%${anything}%' or " +
////            "qq  like '%${anything}%' or " +
////            "college  like '%${anything}%' or " +
////            "campus  like '%${anything}%' or " +
////            "major  like '%${anything}%'
//            "phone  like '%${content}%') and depart=#{depart}  "
//    )
//    List<User> getUserByContent(@Param("content") String content,@Param("depart") String depart);

    /**
     * 通过campus，depart，content获取成员列表
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (depart=#{depart} and campus=#{campus} and status = 1)  "
    )
    List<User> getUserByContentAll(@Param("content") String content,@Param("depart") int depart,@Param("campus") int campus);


    /**
     * 通过campus，depart，content获取成员列表
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (depart=#{depart} and campus=#{campus} )  "
    )
    List<User> getUserByContentAllAdmin(@Param("content") String content,@Param("depart") int depart,@Param("campus") int campus);



    /**
     * 通过depart，content获取成员列表
     * @param content
     * @param depart
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (depart=#{depart} and status=1)  "
    )
    List<User> getUserByContentByDepart(@Param("content") String content,@Param("depart") int depart);

    /**
     * 通过depart，content获取成员列表
     * @param content
     * @param depart
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (depart=#{depart} )  "
    )
    List<User> getUserByContentByDepartAdmin(@Param("content") String content,@Param("depart") int depart);

    /**
     * 通过campus，content获取成员列表
     * @param content
     * @param campus
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (campus=#{campus} and status=1)  "
    )
    List<User> getUserByContentByCampus(@Param("content") String content,@Param("campus") String campus);

    /**
     * 通过campus，content获取成员列表
     * @param content
     * @param campus
     * @return
     */
    @Select("select * from oa_member where( " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
            "phone  like '%${content}%') and (campus=#{campus} )  "
    )
    List<User> getUserByContentByCampusAdmin(@Param("content") String content,@Param("campus") String campus);

    /**
     * 插入一条新成员数据
     * @param recruit
     * @param pwd
     * @param salt
     * @param time
     * @return
     */
    @Insert("insert into oa_member (stuid,name,sex,depart,role,pwd,salt,status,join_time,phone,qq,campus,college,major) values(#{recruit.stuid}," +
            "#{recruit.name}," +
            "#{recruit.sex}," +
            "#{recruit.depart}," +
            "0," +
            "#{pwd}," +
            "#{salt}," +
            "1," +
            "#{time}," +
            "#{recruit.phone}," +
            "#{recruit.qq}," +
            "#{recruit.campus}," +
            "#{recruit.college}," +
            "#{recruit.major})" )
    long InsertNewMember(@Param("recruit")Recruit recruit,@Param("pwd")String pwd,@Param("salt")String salt,@Param("time")int time);

    /**
     * 查询成员的部门
     * @param uid
     * @return
     */
    @Select("select dict_item_name from oa_member as om ,oa_dict_depart as od where om.id=#{uid} and om.depart=od.dict_id")
    String getDepartByUid(@Param("uid") int uid);

    /**
     * 通过uid查询成员的名称
     * @param uid
     * @return
     */
    @Select("select name from oa_member where id = #{uid}")
    String getNameByUid(@Param("uid") int uid);

    /**
     * 更新用户的密码
     * @param uid
     * @param newPwd
     * @return
     */
    @Update("update oa_member set pwd=#{newPwd} where id=#{uid}")
    long setPwd(@Param("uid") int uid,@Param("newPwd") String newPwd);

    /**
     * 更新用户的qq号
     * @param stuid
     * @param qq
     * @return
     */
    @Update("update oa_member set qq=#{qq} where stuid=#{stuid}")
    long setQqByStuid(@Param("stuid")String stuid,@Param("qq") String qq);

    /**
     * 更新用户的email
     * @param stuid
     * @param email
     * @return
     */
    @Update("update oa_member set email=#{email} where stuid=#{stuid}")
    long setEmailByStuid(@Param("stuid")String stuid, @Param("email")String email);

    /**
     * 更新用户的phone
     * @param stuid
     * @param phone
     * @return
     */
    @Update("update oa_member set phone=#{phone} where stuid=#{stuid}")
    long setPhoneByStuid(@Param("stuid")String stuid,@Param("phone") String phone);

    /**
     * 更新用户的debitcard（银行卡）
     * @param stuid
     * @param debitcard
     * @return
     */
    @Update("update oa_member set debitcard=#{debitcard} where stuid=#{stuid}")
    long setDebitcardByStuid(@Param("stuid")String stuid, @Param("debitcard")String debitcard);


}
