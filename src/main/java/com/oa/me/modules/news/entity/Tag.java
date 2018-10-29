package com.oa.me.modules.news.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by chenjiehao on 2018/10/27
 */
@Data
@TableName("tags")
public class Tag implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer tagId;

    private String tagName;

}
