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
    @Select("select id,article_name,content,collection_count from t_article where uid = #{userId} and is_del = 0")
    List<Article> selectArticles(@Param("userId") Integer userId);
    @Select("select id,article_name,content,collection_count from t_article where uid = #{userId} and is_public = 1 and is_del = 0")
    List<Article> selectPubArticles(@Param("userId") Integer userId);
    @Select("select id from t_article where article_name = #{articleName} and content = #{content} and is_del = 0")
    Integer selectArticleId(@Param("articleName") String articleName,@Param("content") String content);
    @Select("select id,article_name,content,collection_count from t_article where uid = #{uid} and is_del = 0")
    List<Article> selectArticleByUid(@Param("uid") Integer uid);
    @Update("update t_article set collection_count=collection_count+1 WHERE id=#{id} ")
    void addCollArticle(@Param("id") Integer id);
    @Update("update t_article set collection_count=collection_count-1 WHERE id=#{id} ")
    void delCollArticle(@Param("id") Integer id);
    @Update("update t_article set article_name=#{articleName},content=#{content} WHERE id=#{id} ")
    void updateArticle(@Param("id") Integer id,@Param("articleName") String articleName,@Param("content") String content);
    @Select("select article_id from t_article where uid = #{uid}")
    List<Integer> selectArticleIds(@Param("userId") Integer userId);
}
