package com.zyolv.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    Article getArticleById(Integer articleId);
    List<Article> getArticleByUid(Integer uid);
    List<Article> getArticles(Integer userId);
    List<Article> getPubArticles(Integer userId);
    Integer getArticleId(Article article);
    void addArticle(Article article);
    void addCollArticle(Integer userId,Integer uid);
    void delCollArticle(Integer userId,Integer uid);
    List<UserEntity> getCollUsers(Integer id);
    Page<Article> getAll(String articleName, Integer page, Integer size);
    void updateArticle(Integer id, String articleName, String content);
    List<Integer> getArticleIds(Integer userId);
}
