package com.oa.me.Dao;

import com.oa.me.domain.Recruit;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/22
 */
@Mapper
public interface RecruitDao {
    /**
     * 通过id获取招新成员
     * @param id
     * @return
     */
    @Select("select * from oa_recruit where id = #{id}")
    Recruit getRecruitById(@Param("id") int id);

    /**
     * 通过条件获取符合条件recruit集合
     * @param recruit
     * @return
     */
    @Select("select * from oa_recruit where depart=#{recruit.depart} and campus=#{recruit.campus}and ( time between #{recruit.start_date} and #{recruit.end_date})")
    List<Recruit> getRecruitAll(@Param("recruit") Recruit recruit);


    @Update("update oa_recruit set status = #{status},depart=#{depart},current=#{current} where id = #{id}")
    Long update(@Param("id") String id, @Param("status") String status, @Param("depart") String depart,@Param("current") int current);


    @Select("select current from oa_recruit where id=#{id}")
    Integer getCurrent(@Param("id")String id);

    @Update("update oa_recruit_log set step_${current} = ${desc} where rid= ${id}")
    Long updateLog(@Param("id")String id, @Param("current")String current,@Param("desc") String desc);


    @Insert("insert into oa_recruit(name,stuid,sex,phone,qq,depart,time,status,current,campus,college,major,say) values(" +
            "#{name}," +
            "#{stuid}," +
            "#{sex}," +
            "#{phone}," +
            "#{qq}," +
            "#{depart}," +
            "#{time}," +
            "#{status}," +
            "#{current}," +
            "#{campus}," +
            "#{college}," +
            "#{major}," +
            "#{say})" )
    long apply(Recruit recruit);
}
