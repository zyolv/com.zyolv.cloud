package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select id,article_name,content,collection_count from t_article where id in (select article_id from t_user_article where user_id = #{userId}) and is_del = 0")
    List<Article> selectArticles(@Param("userId") Integer userId);
    @Select("select id,article_name,content,collection_count from t_article where id in (select article_id from t_user_article where user_id = #{userId}) and is_public = 1 and is_del = 0")
    List<Article> selectPubArticles(@Param("userId") Integer userId);
    @Select("select id from t_article where article_name = #{articleName} and content = #{content} and is_del = 0")
    Integer selectArticleId(@Param("articleName") String articleName,@Param("content") String content);
    @Select("select id,article_name,content,collection_count from t_article where uid = #{uid} and is_del = 0")
    Article selectArticleByUid(@Param("uid") String uid);
    @Update("update t_article set collection_count=collection_count+1 WHERE uid=#{uid} ")
    void addCollArticle(@Param("uid") String uid);
}
