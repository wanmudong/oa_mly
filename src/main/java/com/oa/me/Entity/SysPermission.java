package com.oa.me.Entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 菜单管理
 * </p>
 *
 * @author chenliangliang
 * @since 2018-02-18
 */
@Data
@Accessors(chain = true)
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 父菜单ID，一级菜单为0
     */
    private Integer pid;
    /**
     * 菜单名称
     */
    private String name;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * ztree属性
     * 如果0则是不存在子菜单
     */
    @TableField(exist=false)
    private int open;

    /**
     * 子菜单
     */
    @TableField(exist=false)
    private List<SysPermission> list;

    /**
     * 父菜单名称
     */
    @TableField(exist=false)
    private String parentName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public List<SysPermission> getList() {
        return list;
    }

    public void setList(List<SysPermission> list) {
        this.list = list;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", perms='" + perms + '\'' +
                ", orderNum=" + orderNum +
                ", open=" + open +
                ", list=" + list +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
