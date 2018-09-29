package com.oa.me.Service.impl;

import com.oa.me.Dao.DictDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Entity.SysUser;
import com.oa.me.Service.UserService;
import com.oa.me.domain.*;
import com.oa.me.utils.oa_md5;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.oa.me.utils.oa_md5.md5_salt;

/**
 * Created by chenjiehao on 2018/9/17
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private DictDao dictDao;

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
    public List<User> getUserByContent(String content, String depart,String campus) {
        /**
         * 部长权限
         */
        List<User> userList = new  ArrayList<User>();


        userList  = userDao.getUserByContentAll(content,Integer.valueOf(depart),Integer.valueOf(campus));
        for (User user:userList){
           user.setPwd("");
           user.setSalt("");
//           JUser jUser =  mapperUser.mapperJUser(user,dictDao);
//           jUserList.add(jUser);
        }
        return userList;
    }

    @Override
    public boolean updateMember(User user) {
        SysUser sysUser =  userDao.findSysUserByUsername(String.valueOf(user.getStuid()));
        String password = sysUser.getPassword();
        String salt = sysUser.getSalt();

        if (user.getPwd()==null||user.getPwd().equals("")){

           user.setPwd(password);
        }else {
             password = user.getPwd();
//            String salt = user.getSalt();
            password = oa_md5.md5_salt(password,salt);
            user.setPwd(password);
        }

        long success = userDao.updateMember(user);

        return success==1?true:false;
    }


    @Override
    public List<User> getUserByContent(String content, String depart, String circle, String campus) {
        /**
         * 主管或行政人员权限
         * circle 先不去管
         *
         */
        List<User> list = new ArrayList<User>();
        if (campus==null|| campus.equals("")){

            if (depart==null|| depart.equals("")){
                list  = userDao.getUserByContent(content);
            }else {
                list  = userDao.getUserByContentByDepart(content, Integer.parseInt(depart));
            }
        }else {
            if (depart==null|| depart.equals("")){
                list  = userDao.getUserByContentByCampus(content,campus);
            }else {

            list  = userDao.getUserByContentAll(content,Integer.valueOf(depart),Integer.valueOf(campus));
            }
        }
//        for (User user:list){
//            user.setPwd("");
//            user.setSalt("");
//        }
        return list;
    }

    @Override
    public boolean updateMemberByMe(String key, String stuid, String qq, String email, String phone, String debitcard) {
        long success=1;
        long success1=1;
        long success2=1;
        long success3=1;
        if (qq!=null)
        {
            success = userDao.setQqByStuid(stuid,qq);
        }
        if (email!=null)
        {
            success1 = userDao.setEmailByStuid(stuid,email);
        }
        if (phone!=null)
        {
            success2 = userDao.setPhoneByStuid(stuid,phone);
        }
        if (debitcard!=null)
        {
            success3 = userDao.setDebitcardByStuid(stuid,debitcard);
        }
        if (success>0&&success1>0&&success2>0&&success3>0)
        {
            return true;
        }



        return false;
    }
}
