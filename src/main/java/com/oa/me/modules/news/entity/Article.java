package com.oa.me.modules.news.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章管理
 * 
 * @author chenliangliang
 * @date 2018-02-21 15:42:00
 */
@Data
@TableName("article")
public class Article implements Serializable {


	private static final long serialVersionUID = 8956305525538320811L;
	/**
	 * 新闻id
	 */
	@TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 标题
	 */
	@Size(max = 8,message = "sss")
	private String title;
	/**
	 * 摘要
	 */
	private String summary;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 作者
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String author;
	/**
	 * 状态：0->未审核；1->审核未通过未修改;2->审核未通过已修改;3->审核已通过
	 */
	private Integer state;
	/**
	 * 文章权重1-100
	 */
	private Integer weight;
	/**
	 * 文章来源
	 */
	private String copyFrom;
	/**
	 * 新闻封面
	 */
	private String cover;
	/**
	 * 阅读量
	 */
	private Integer read;
	/**
	 * 发布文章的管理员
	 */
	private String username;
	/**
	 * 审核文章
	 */
	private String comment;
	/**
	 * 文章上显示的时间
	 */
	private Date publishTime;
	/**
	 * 新闻提交到后台的时间
	 */
	@JsonFormat(pattern = "yyyy:MM:dd",timezone = "GMT+8")
	private Date createTime;
	/**
	 * 新闻最后被修改的时间
	 */
	private Date updateTime;
	/**
	 * 标签数组
	 */
	@TableField(exist = false)
	private Integer[] tagIdList;


}
