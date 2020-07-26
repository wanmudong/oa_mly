package com.oa.me.Dao;

import com.oa.me.domain.Announce;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by chenjiehao on 2018/9/18
 */
@Mapper
public interface AnnounceDao {
    /**
     * 查找所有通知
     * @return
     */
    @Select("select * from oa_announce order by id desc")
    List<Announce> getAnnounce();

    /**
     * 添加一条通知
     * @param announce
     * @return
     */
    @Insert("insert into oa_announce(title,content,publish_time,status,uid) values (" +
            "#{announce.title},#{announce.content},#{announce.publish_time},#{announce.status},#{announce.uid})"
    )
    long setAnnounce(@Param("announce") Announce announce);

    /**
     * 删除一条通知
     */
    @Delete("delete from oa_announce where id = #{id}")
    long delAnnounce(int id);
}
