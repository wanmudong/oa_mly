package com.oa.me.utils.shiro.tool;

import com.oa.me.Entity.SysPermission;
import com.oa.me.Entity.SysRole;
import com.oa.me.Entity.SysUser;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
public interface IShiro {

    public SysUser getSysuser(String username);

    public SysRole getSysUserRoleList(String username);
    public List<SysPermission> getPermissions(SysRole role);

    SysUser findSysUserByUsername(String i);
}
