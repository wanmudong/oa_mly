package com.oa.me.Dao;

import com.oa.me.domain.Recruit;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Mapper
public interface DepartDao {

    @Select("select dict_id from oa_dict_depart where dict_item_name=#{depart}")
    String getIdByName(Recruit recruit);
    /**
     * 用部门代号获取部门名称
     * @param depart
     * @return
     */
    @Select("select dict_item_name from oa_dict_depart where dict_id=#{depart}")
    String getNameById(@Param("depart") int depart);


    /**
     * 用部门名称获取部门代号
     * @param depart
     * @return
     */
    @Select("select dict_id from oa_dict_depart where dict_item_name=#{depart}")
    Integer getIdByName(@Param("depart") String depart);
}
