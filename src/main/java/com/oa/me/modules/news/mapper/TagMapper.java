package com.oa.me.modules.news.mapper;

/**
 * Created by chenjiehao on 2018/10/27
 */

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.oa.me.modules.news.entity.Article;
import com.oa.me.modules.news.entity.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagMapper extends BaseMapper<Tag> {


    Integer insertTags(Tag tag);

    List<Tag> queryTags();

}
