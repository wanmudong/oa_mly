package com.oa.me.Dao;

import com.oa.me.domain.Recruit;
import com.oa.me.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
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
    @Select("select * from oa_recruit where depart=#{recruit.depart} and campus=#{recruit.campus}and ( time between #{recruit.start_date} and #{recruit.end_date})and status=1")
    List<Recruit> getRecruitAll(@Param("recruit") Recruit recruit);


    @Update("update oa_recruit set depart=#{depart},current=#{current} where id = #{id} ")
    Long updateDepart(@Param("id") int id, @Param("depart") Integer depart,@Param("current") int current);


    /**
     * 修改当前面试状态
      * @param id
     * @param current
     * @return
     */
    @Update("update oa_recruit set current=#{current} where id = #{id}")
    Long update(@Param("id") int id, @Param("current") int current);

    /**
     *修改面试者状态
     * @param id
     * @param status
     * @return
     */
    @Update("update oa_recruit set status = #{status} where id = #{id} ")
    Long updateStatus(@Param("id") int id, @Param("status") int status);


    @Select("select current from oa_recruit where id=#{id} and status=1")
    Integer getCurrent(@Param("id")String id);

    @Update("update oa_recruit_log set step_${status}='${desc}' where rid= ${id}")
    Long updateLog(@Param("id")int id, @Param("status")int status,@Param("desc") String desc);


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

    @Select("select * from oa_recruit where( " +
//            "id  like '%${anything}%' or " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
//            "sex  like '%${anything}%' or " +
//            "depart like '%${anything}%' or " +
//            "debitcard  like '%${anything}%' or " +
//            "email  like '%${anything}%' or " +
//            "qq  like '%${anything}%' or " +
//            "college  like '%${anything}%' or " +
//            "campus  like '%${anything}%' or " +
//            "major  like '%${anything}%'
            "phone  like '%${content}%') and (depart=#{depart} and campus=#{campus} and status=1) "
    )
    List<Recruit> getRecruitByContentAll(@Param("content") String content,@Param("depart") int depart,@Param("campus") int campus);

    @Select("select * from oa_recruit where ( " +
//            "id  like '%${anything}%' or " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
//            "sex  like '%${anything}%' or " +
//            "depart like '%${anything}%' or " +
//            "debitcard  like '%${anything}%' or " +
//            "email  like '%${anything}%' or " +
//            "qq  like '%${anything}%' or " +
//            "college  like '%${anything}%' or " +
//            "campus  like '%${anything}%' or " +
//            "major  like '%${anything}%'
            "phone  like '%${content}% ') and status=1 "
    )
    List<Recruit> getRecruitByContent(@Param("content") String content);
//    List<Recruit> getUserByContent(String content);

    @Select("select * from oa_recruit where( " +
//            "id  like '%${anything}%' or " +
                   "stuid  like '%${content}%' or " +
                   "name  like '%${content}%' or " +
//            "sex  like '%${anything}%' or " +
//            "depart like '%${anything}%' or " +
//            "debitcard  like '%${anything}%' or " +
//            "email  like '%${anything}%' or " +
//            "qq  like '%${anything}%' or " +
//            "college  like '%${anything}%' or " +
//            "campus  like '%${anything}%' or " +
//            "major  like '%${anything}%'
                   "phone  like '%${content}%') and (depart=#{depart} and status=1)  "
    )
    List<Recruit> getRecruitByContentByDepart(@Param("content") String content,@Param("depart") int depart);
//    List<Recruit> getUserByContentByDepart(String content, String department);

    @Select("select * from oa_recruit where( " +
//            "id  like '%${anything}%' or " +
            "stuid  like '%${content}%' or " +
            "name  like '%${content}%' or " +
//            "sex  like '%${anything}%' or " +
//            "depart like '%${anything}%' or " +
//            "debitcard  like '%${anything}%' or " +
//            "email  like '%${anything}%' or " +
//            "qq  like '%${anything}%' or " +
//            "college  like '%${anything}%' or " +
//            "campus  like '%${anything}%' or " +
//            "major  like '%${anything}%'
            "phone  like '%${content}%') and ( campus=#{campus} and status=1 )"
    )
    List<Recruit> getRecruitByContentByCampus(@Param("content") String content,@Param("campus") int campus);

    /**
     * 将id对应的一组数据设为空
     * @param id
     * @param desc
     * @return
     */
    @Update("update oa_recruit_log  set step_0 =#{desc},step_1='' ,step_2='',step_3='',step_4='' where rid=#{id} ")
    long clearRecruitLog(@Param("id") int id,@Param("desc") String desc);
//    List<Recruit> getUserByContentByCampus(String content, String campus);
//    List<Recruit> getRecruitByContentAll(String content, String depart, String campus_0);
}
