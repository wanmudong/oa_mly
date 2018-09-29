package com.oa.me.Dao;

import com.oa.me.Entity.SysPermission;
import org.apache.ibatis.annotations.*;

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


    /**
     * 用成员身份代号获取成员身份名称
     * @param role
     * @return
     */
    @Select("select dict_item_name from oa_dict_member_role where dict_id=#{role}")
    String getNameById(@Param("role") int role);


    /**
     * 用成员身份名称获取员身份代号
     * @param role
     * @return
     */
    @Select("select dict_id from oa_dict_member_role where dict_item_name=#{role}")
    String getIdByName(@Param("role") String role);
}
