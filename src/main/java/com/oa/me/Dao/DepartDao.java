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

    @Select("select dict_item_name from oa_dict_depart where dict_id=#{depart}")
    String getNameById(@Param("depart") int depart);


    @Select("select dict_id from oa_dict_depart where dict_item_name=#{depart}")
    String getIdByName(Recruit recruit);

    @Select("select dict_id from oa_dict_depart where dict_item_name=#{depart}")
    String getIdByName(@Param("depart") String depart);
}
