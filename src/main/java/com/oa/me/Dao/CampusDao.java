package com.oa.me.Dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Mapper
public interface    CampusDao {
    /**
     * 以校区的代码获取校区名称
     * @param campus
     * @return
     */
    @Select("select dict_item_name from oa_dict_campus where dict_id = #{campus}")
    String getCampusName(@Param("campus") int campus);
}
