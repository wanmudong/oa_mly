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

    List<User> getUserByAll(String anything);

    boolean updateMember(User user);
}
