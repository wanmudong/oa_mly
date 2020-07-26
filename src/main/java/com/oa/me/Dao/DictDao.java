package com.oa.me.Dao;

import com.oa.me.domain.Dict;
import com.oa.me.domain.Recruit;
import com.oa.me.domain.Steps;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/20
 */
@Mapper
public interface  DictDao {

    /**
     *以表名获取各字典表数据
     * @param tableName
     * @return
     */
    @Select("select dict_id,dict_item_name from ${tableName}")
    @Results({
            @Result(property = "key",  column = "dict_id"),
            @Result(property = "value", column = "dict_item_name")
    })
    List<Dict> getDictByTableName(@Param(value = "tableName") String tableName);

    /**
     * 获取所有字典的表名
     * @return
     */
    @Select("select dict_table_name from oa_dict")
    List<String> getDictTableNames();


    /**
     * 获取各个表的代表的信息类型
     * @return
     */
    @Select("select dict_memo from oa_dict where dict_table_name=#{dict_table_name}")
    String getDictMemo(String dict_table_name);

    /**
     * 以个人id获取到招新的审核信息
     * @param id
     * @return
     */
    @Select("select rid,step_0,step_1,step_2,step_3,step_4 from oa_recruit_log where rid=#{id}")
    @Results({
            @Result(property = "id",  column = "rid"),
            @Result(property = "step0", column = "step_0"),
            @Result(property = "step1", column = "step_1"),
            @Result(property = "step2", column = "step_2"),
            @Result(property = "step3", column = "step_3"),
            @Result(property = "step4", column = "step_4")
    })
    Steps getSteps(@Param("id") int id);

    /**
     * 通过校区的名字获取校区的代号
     * @return
     */
    @Select("select dict_id from oa_dict_campus where dict_item_name=#{campus}")
    String getIdByCampusName(Recruit recruit);

    /**
     * 用成员身份代号获取成员身份名称
     * @param role
     * @return
     */
    @Select("select dict_item_name from oa_dict_member_role where dict_id=#{role}")
    String getRoleNameById(@Param("role") int role);


    /**
     * 用成员身份名称获取员身份代号
     * @param role
     * @return
     */
    @Select("select dict_id from oa_dict_member_role where dict_item_name=#{role}")
    Integer getRoleIdByName(@Param("role") String role);

    /**
     * 用部门代号获取部门名称
     * @param depart
     * @return
     */
    @Select("select dict_item_name from oa_dict_depart where dict_id=#{depart}")
    String getDepartNameById(@Param("depart") int depart);


    /**
     * 用部门名称获取部门代号
     * @param depart
     * @return
     */
    @Select("select dict_id from oa_dict_depart where dict_item_name=#{depart}")
    String  getDepartIdByName(@Param("depart") String depart);

    /**
     * 用状态代号获取状态名称
     * @param status
     * @return
     */
    @Select("select dict_item_name from oa_dict_member_status where dict_id=#{status}")
    String getStatusNameById(@Param("status") int status);


    /**
     * 用状态名称获取状态代号
     * @param status
     * @return
     */
    @Select("select dict_id from oa_dict_member_status where dict_item_name=#{status}")
    Integer getStatusIdByName(@Param("status") String status);

    /**
     * 用校区代号获取校区名称
     * @param campus
     * @return
     */
    @Select("select dict_item_name from oa_dict_campus where dict_id=#{campus}")
    String getCampusNameById(@Param("campus") int campus);


    /**
     * 用校区名称获取校区代号
     * @param campus
     * @return
     */
    @Select("select dict_id from oa_dict_campus where dict_item_name=#{campus}")
    String getCampusIdByName(@Param("campus") String campus);

    /**
     * 通过反馈的类型获取该反馈类型的代号
     * @param type
     * @return
     */
    @Select("select dict_item_name from oa_dict_feedback where dict_id=#{type}")
    String getFeedbackTypeNameById(String type);
}
