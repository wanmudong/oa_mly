package com.oa.me.Dao;

import com.oa.me.domain.RReport;
import com.oa.me.domain.Recruit;
import com.oa.me.domain.Report;
import com.oa.me.domain.Salary;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by chenjiehao on 2018/9/27
 */
@Mapper
public interface ReportDao {


    /**
     * 查询历史汇报记录
     * @param uid
     * @return
     */
    @Select("select oa_report.* from oa_report,oa_member where oa_report.uid=oa_member.id and oa_member.stuid=#{uid}")
    @Results({
            @Result(property = "start_date",  column = "start_date"),
            @Result(property = "end_date",  column = "end_date"),
            @Result(property = "salary_sug",  column = "salary_sug"),
            @Result(property = "time_report",  column = "time_report"),
            @Result(property = "id", column = "id")
    })
    List<Report> getHistory(@Param("uid") int uid);


    /**
     * 查询工作汇报状态
     * @return
     */
    @Select("select * from oa_setting ")
    List<Map<String,String>> getStatus();

    /**
     * 通过状态的名字查询
     * @param name
     * @return
     */
    @Select("select value from oa_setting where name=#{name}")
    String getStatusByName(@Param("name") String name);

    /**
     * 通过用户id获取该用户的汇报时间
     * @param uid
     * @return
     */
    @Select("select end_date,start_date,time_report from oa_report where uid=#{uid}")
    Report  getStatusByUid(int uid);

    /**
     * 查询出一个用户在当前汇报时间汇报的数量
     * @param s
     * @param s1
     * @param uid
     * @return
     */
    @Select("select count(*) from oa_report where uid=#{uid} and (time_report<#{s1} and time_report>#{s})")
    int getStatusByTime(@Param("s") long s,@Param("s1") long s1, @Param("uid")int uid);

    /**
     * 通过content,depart,content获取汇报者的id的集合
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    @Select("select id from oa_member where( " +
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
    List<Integer> getReportByContentAll(@Param("content") String content,@Param("depart") int depart, @Param("campus")int campus);

    /**
     * 通过汇报的开始日期与汇报者id获取汇报
     * @param l
     * @param uid
     * @return
     */
    @Select("select * from oa_report where uid=#{uid} and  start_date=#{l}")
    Report getReportByUid(@Param("l") long l,@Param("uid") int uid);

    /**
     *  通过campus,depart,start_date，content获取汇报信息
     * @param l
     * @param content
     * @param depart
     * @param campus
     * @return
     */
    @Select("select  r.start_date,r.end_date,r.salary,r.salary_sug,r.rate,r.content,r.suggestion,r.comment,m.status,m.name,r.id from oa_report r left  join oa_member m on r.uid=m.id where  (m.stuid  like '%${content}%' or " +
            "m.name  like '%${content}%' or " +
            "m.phone  like '%${content}%')" +
            " and (r.start_date=#{l} and m.depart=#{depart} and m.campus=#{campus} and m.status = 1) order by r.id desc")
    @Results({
            @Result(property = "start_date",  column = "start_date"),
            @Result(property = "end_date",  column = "end_date"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "salary1",  column = "salary_sug"),
            @Result(property = "rate",  column = "rate"),
            @Result(property = "content",  column = "content"),
            @Result(property = "suggestion",  column = "suggestion"),
            @Result(property = "comment",  column = "comment"),
            @Result(property = "status",  column = "status"),
            @Result(property = "status",  column = "status"),
            @Result(property = "username",  column = "name"),
//            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getReportById(@Param("l") long l, @Param("content")String content, @Param("depart") int depart, @Param("campus") int campus);

    /**
     * 通过campus,start_date，content获取汇报信息
     * @param l
     * @param contact
     * @param campus
     * @return
     */
    @Select("select  r.start_date,r.end_date,r.salary,r.salary_sug,r.rate,r.content,r.suggestion,r.comment,m.status,m.name,r.id from oa_report r left  join oa_member m on r.uid=m.id where  (" +
            "m.stuid  like '%${contact}%' or " +
            "m.name  like '%${contact}%' or " +
            "m.phone  like '%${contact}%')" +
            " and (r.start_date=#{l} and m.campus=#{campus} and m.status = 1) order by r.id desc")
    @Results({
            @Result(property = "start_date",  column = "start_date"),
            @Result(property = "end_date",  column = "end_date"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "salary1",  column = "salary_sug"),
            @Result(property = "rate",  column = "rate"),
            @Result(property = "content",  column = "content"),
            @Result(property = "suggestion",  column = "suggestion"),
            @Result(property = "comment",  column = "comment"),
            @Result(property = "status",  column = "status"),
            @Result(property = "status",  column = "status"),
            @Result(property = "username",  column = "name"),
//            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getReportByIdAndCampus(@Param("l")long l,@Param("contact") String contact,@Param("campus") Integer campus);


    /**
     * depart,start_date，content获取汇报信息
     * @param l
     * @param contact
     * @param depart
     * @return
     */
    @Select("select  r.start_date,r.end_date,r.salary,r.salary_sug,r.rate,r.content,r.suggestion,r.comment,m.status,m.name,r.id from oa_report r left  join oa_member m on r.uid=m.id where  (" +
            "m.stuid  like '%${contact}%' or " +
            "m.name  like '%${contact}%' or " +
            "m.phone  like '%${contact}%')" +
            " and (r.start_date=#{l} and m.depart=#{depart} and m.status = 1) order by r.id desc")
    @Results({
            @Result(property = "start_date",  column = "start_date"),
            @Result(property = "end_date",  column = "end_date"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "salary1",  column = "salary_sug"),
            @Result(property = "rate",  column = "rate"),
            @Result(property = "content",  column = "content"),
            @Result(property = "suggestion",  column = "suggestion"),
            @Result(property = "comment",  column = "comment"),
            @Result(property = "status",  column = "status"),
            @Result(property = "status",  column = "status"),
            @Result(property = "username",  column = "name"),
//            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getReportByIdAndDepart(@Param("l")long l, @Param("contact")String contact, @Param("depart")Integer depart);

    /**
     * 通过start_date，content获取汇报信息
     * @param l
     * @param contact
     * @return
     */
    @Select("select  r.start_date,r.end_date,r.salary,r.salary_sug,r.rate,r.content,r.suggestion,r.comment,m.status,m.name,r.id from oa_report r left  join oa_member m on r.uid=m.id where  (m.stuid  like '%${contact}%' or " +
            "m.name  like '%${contact}%' or " +
            "m.phone  like '%${contact}%')" +
            " and (r.start_date=#{l} and m.status = 1) order by r.id desc")
    @Results({
            @Result(property = "start_date",  column = "start_date"),
            @Result(property = "end_date",  column = "end_date"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "salary1",  column = "salary_sug"),
            @Result(property = "rate",  column = "rate"),
            @Result(property = "content",  column = "content"),
            @Result(property = "suggestion",  column = "suggestion"),
            @Result(property = "comment",  column = "comment"),
            @Result(property = "status",  column = "status"),
            @Result(property = "status",  column = "status"),
            @Result(property = "username",  column = "name"),
//            @Result(property = "name", column = "name"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getReportByContact(@Param("l")long l,@Param("contact") String contact);


    /**
     * 更新汇报时间表的字段数据
     * @param name
     * @param value
     * @return
     */
    @Update("update oa_setting set value=#{value} where name=#{name}")
    Long setStatusByName(@Param("name") String name,@Param("value") String value);

    /**
     * 插入一条汇报信息
     * @param uid1
     * @param work_start_date
     * @param work_end_date
     * @param secondTimeNow
     * @param content
     * @param suggestion
     * @return
     */
    @Insert("insert into oa_report (uid,start_date,end_date,time_report,content,suggestion)values(" +
            "#{uid1},#{work_start_date},#{work_end_date},#{secondTimeNow},#{content},#{suggestion})")
    long setReport(@Param("uid1") int uid1, @Param("work_start_date")String work_start_date, @Param("work_end_date")String work_end_date, @Param("secondTimeNow")int secondTimeNow,@Param("content") String content,@Param("suggestion") String suggestion);


    /**
     * 部长权限对一条汇报进行审核
     * @param id
     * @param comment
     * @param rate
     * @param salary_sug
     * @return
     */
    @Update("update oa_report set comment=#{comment},rate=#{rate},salary_sug=#{salary_sug} where id=#{id}")
    long setReportByAdmin(@Param("id") int id,@Param("comment") String comment,@Param("rate") String rate,@Param("salary_sug") String salary_sug);

    /**
     * 主管权限对一条汇报进行审核
     * @param id
     * @param salary
     * @return
     */
    @Update("update oa_report set salary=#{salary} where id=#{id}")
    long setReportByAdmin(int id, String salary);

    /**
     * 主管权限对一条汇报进行审核
     * @param id
     * @param salary
     * @return
     */
    @Update("update oa_report set salary=#{salary} where id=#{id}")
    long setReportSalaryByAdmin(@Param("id") int id,@Param("salary")  String salary);

    /**
     * 对一条汇报进行评价
     * @param id
     * @param comment
     * @return
     */
    @Update("update oa_report set comment=#{comment} where id=#{id}")
    long setReporCommentByAdmin(@Param("id") int id,@Param("comment")  String comment);

    /**
     * 设置一条汇报的评分
     * @param id
     * @param rate
     * @return
     */
    @Update("update oa_report set rate=#{rate} where id=#{id}")
    long setReportRateByAdmin(@Param("id") int id,@Param("rate")  String rate);

    /**
     * 设置一条汇报的建议薪酬
     * @param id
     * @param salary_sug
     * @return
     */
    @Update("update oa_report set salary_sug=#{salary_sug} where id=#{id}")
    long setReportSalary_sugByAdmin(@Param("id") int id, @Param("salary_sug") String salary_sug);

    /**
     * 根据start_date,depart,campus获取汇报信息以及汇报者的信息
     * @param depart
     * @param campus
     * @param start_date_long
     * @return
     */
    @Select("select m.depart,m.name,m.stuid,m.sex,m.college,m.phone,m.debitcard,r.salary,r.content,r.time_report,r.id  " +
            "from oa_report r left  join oa_member m on r.uid=m.id where  " +
            "(r.start_date=#{start_date_long} and m.status = 1 and m.depart = #{depart} and m.campus = #{campus}) order by r.id desc")
    @Results({
            @Result(property = "depart",  column = "depart"),
            @Result(property = "username",  column = "name"),
            @Result(property = "stuid",  column = "stuid"),
            @Result(property = "sex",  column = "sex"),
            @Result(property = "college",  column = "college"),
            @Result(property = "phone",  column = "phone"),
            @Result(property = "debitcard",  column = "debitcard"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "content",  column = "content"),
            @Result(property = "time_report",  column = "time_report"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getReport(@Param("depart") String depart,@Param("campus") String campus,@Param("start_date_long") long start_date_long);

    /**
     * 根据开始时间获取汇报信息列表
     * @param start_date_long
     * @return
     */
    @Select("select m.depart,m.name,m.stuid,m.sex,m.college,m.phone,m.debitcard,r.salary,r.content,r.time_report,r.id  " +
            "from oa_report r left  join oa_member m on r.uid=m.id where  " +
            "(r.start_date=#{start_date_long} and m.status = 1) order by r.id desc")
    @Results({
            @Result(property = "depart",  column = "depart"),
            @Result(property = "username",  column = "name"),
            @Result(property = "stuid",  column = "stuid"),
            @Result(property = "sex",  column = "sex"),
            @Result(property = "college",  column = "college"),
            @Result(property = "phone",  column = "phone"),
            @Result(property = "debitcard",  column = "debitcard"),
            @Result(property = "salary0",  column = "salary"),
            @Result(property = "content",  column = "content"),
            @Result(property = "time_report",  column = "time_report"),
            @Result(property = "id", column = "id")
    })
    List<RReport> getAllReport(@Param("start_date_long")long start_date_long);
}
