package com.oa.me.Dao;

import com.oa.me.domain.Feedback;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/28
 */
@Mapper
public interface FeedbackDao {

    @Select("select * from oa_feedback where  status=1 order by id desc")
    List<Feedback> getAllFeedback();


    @Insert("insert into oa_feedback (type,content,uid,ftime,status,ip)values(#{feedback.type},#{feedback.content},#{feedback.uid},#{feedback.ftime},1,#{feedback.ip})")
    long setFeedback(@Param("feedback") Feedback feedback);
}
