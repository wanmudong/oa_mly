package com.oa.me.Service.impl;

import com.oa.me.Dao.UserDao;
import com.oa.me.Service.UserService;
import com.oa.me.domain.User;
import com.oa.me.domain.UserModel;
import com.oa.me.domain.UserOa;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.oa.me.utils.oa_md5.md5_salt;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public UserOa getUserLogin(UserModel userModel) {
        User user = new User();
        UserOa userOa = new UserOa();

//        user.setSalt(userDao.getUserSalt(userModel));

        user.setPwd(md5_salt(userModel.getPassword(),userDao.getUserSalt(userModel)));
        user.setStuid(Integer.valueOf(userModel.getUsername()));
        userOa = userDao.getUserLogin(user);

        return userOa;

    }

    @Override
    public List<User> getAllUser() {
        List<User> list = userDao.getAllUser();
        return list;
    }

    @Override
    public User getUserByStuid(String stuid) {
        User user = userDao.getUserByStuid(stuid);
        if (user==null){
            return null;
        }
        user.setPwd("");
        user.setSalt("");
        return user;
    }

    @Override
    public List<User> getUserByAll(String anything) {

        List<User> list  = userDao.getUserByAny(anything);
        for (User user:list){
            user.setPwd("");
            user.setSalt("");
        }
        return list;
    }

    @Override
    public boolean updateMember(User user) {

        long success = userDao.updateMember(user);

        return success==1?true:false;
    }
}
