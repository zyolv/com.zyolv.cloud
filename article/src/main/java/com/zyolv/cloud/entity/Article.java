package com.zyolv.cloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_article")
public class Article {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField(value = "article_name")
    private String articleName;
    private String content;
    private String uid;//userId+articleName保证唯一
    @TableField(value = "is_del")
    private Integer isDel;//删除标志，0：未删除；1：已删除
    @TableField(value = "is_public")
    private Integer isPublic;//公开标志，0：不公开；1：公开
    @TableField(value = "collection_count")
    private Integer collectionCount;
    @TableField(exist = false)
    private List<Integer> collectionUsers;

}
