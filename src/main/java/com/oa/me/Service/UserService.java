package com.oa.me.Service;

import com.oa.me.domain.User;
import com.oa.me.domain.UserModel;
import com.oa.me.domain.UserOa;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/17
 */
public interface UserService {
    public UserOa getUserLogin(UserModel userModel);

    List<User> getAllUser();

    User getUserByStuid(String stuid);

 //   List<User> getUserByContent(String anything, int depart, String circle, String campus);

    boolean updateMember(User user);


    public List<User> getUserByContent(String content, String depart,String campus);

    public List<User> getUserByContent(String content, String depart, String circle, String campus);

    boolean updateMemberByMe(String key, String stuid, String qq, String email, String phone, String debitcard);
}
