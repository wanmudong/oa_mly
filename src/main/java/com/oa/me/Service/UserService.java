package com.oa.me.Service;

import com.oa.me.domain.User;
import com.oa.me.domain.UserModel;
import com.oa.me.domain.UserOa;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/17
 */
public interface UserService {
    /**
     * 获取登陆者信息
     * @param userModel
     * @return
     */
    public UserOa getUserLogin(UserModel userModel);

    /**
     * 获取用户列表
     * @return
     */
    List<User> getAllUser();

    /**
     * 通过用户的stuid来获取其详细信息
     * @param stuid
     * @return
     */
    User getUserByStuid(String stuid);

 //   List<User> getUserByContent(String anything, int depart, String circle, String campus);

    /**
     * 利用权限更新用户信息
     * @param user
     * @return
     */
    boolean updateMember(User user);


    /**
     * 部长权限：根据部门，校区，内容获取用户列表
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    public List<User> getUserByContent(String content, String depart,String campus);

    /**
     * 行政（主管）权限：根据部门，校区，内容获取用户列表
     * @param content
     * @param depart
     * @param circle
     * @param campus
     * @return
     */
    public List<User> getUserByContent(String content, String depart, String circle, String campus);

    /**
     * 个人修改自身信息
     * @param key
     * @param stuid
     * @param qq
     * @param email
     * @param phone
     * @param debitcard
     * @return
     */
    boolean updateMemberByMe(String key, String stuid, String qq, String email, String phone, String debitcard);
}
