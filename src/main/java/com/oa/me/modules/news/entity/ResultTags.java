package com.oa.me.modules.news.entity;

import lombok.Data;

import java.util.List;

/**
 * Created by chenjiehao on 2018/10/27
 */
@Data
public class ResultTags {
    private String msg;
    private Integer code;
    private List<Tag> tags;

}
