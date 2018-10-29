package com.oa.me.modules.news.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenliangliang
 * @date 2018-02-21 15:42:00
 */
@Data
@TableName("article_tag")
public class ArticleTag implements Serializable {

	private static final long serialVersionUID = 8239655810196911860L;
	/**
	 * id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 新闻ID
	 */
	private Long articleId;
	/**
	 * 标签ID
	 */
	private Integer tagId;


}
