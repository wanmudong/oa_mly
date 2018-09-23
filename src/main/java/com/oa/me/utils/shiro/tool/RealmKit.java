package com.oa.me.utils.shiro.tool;

import com.oa.me.Dao.RoleDao;
import com.oa.me.Dao.UserDao;
import com.oa.me.Entity.SysPermission;
import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;
import com.oa.me.utils.SpringContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Service
@DependsOn("springContextHolder")
public class RealmKit implements IShiro {


    private UserDao userDao;

    private RoleDao roleDao;

    @Autowired
    protected RealmKit(UserDao userDao,RoleDao roleDao){
        this.userDao=userDao;
        this.roleDao=roleDao;
    }

    public static IShiro me() {
        return SpringContextHolder.getBean(IShiro.class);
    }

    public  SysUser getSysuser(String username){

     return userDao.findSysUserByUsername(username);
    }

    public SysRole getSysUserRoleList(String username){

       return userDao.getSysUserRoleList(username);
    }


    public List<SysPermission> getPermissions(SysRole role) {
       return roleDao.getPermissions(role.getId());
    }

    @Override
    public SysUser findSysUserByUsername(String i) {
        return userDao.findSysUserByUsername(i);
    }


}
