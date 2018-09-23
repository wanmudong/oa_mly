package com.oa.me.Dao;

import com.oa.me.Entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/21
 */
@Mapper
public interface RoleDao {
    /**
     * 通过role的id获取对应的权限列表
     */
    @Select("select * from sys_role_permission rp left join sys_permission p on rp.permission_id = p.id where rp.role_id=#{role_id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "perms", column = "permission"),
            @Result(property = "open", column = "available"),
            @Result(property = "pid", column = "parent_id"),
            @Result(property = "parentName", column = "parent_ids")
    })
    List<SysPermission> getPermissions(int role_id);
}
