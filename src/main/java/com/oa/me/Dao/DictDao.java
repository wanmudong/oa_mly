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
public interface DictDao {

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

    @Select("select * from oa_recruit_log where rid=#{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "step0", column = "step_0"),
            @Result(property = "step1", column = "step_1"),
            @Result(property = "step2", column = "step_2"),
            @Result(property = "step3", column = "step_3"),
            @Result(property = "step4", column = "step_4")
    })
    Steps getSteps(@Param("id") int id);

    /**
     *
     * @return
     */
    @Select("select dict_id from oa_dict_campus where dict_item_name=#{campus}")
    String getIdByCampusName(Recruit recruit);
}
