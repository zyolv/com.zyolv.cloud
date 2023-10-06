package com.zyolv.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;

import java.util.List;

public interface ArticleService {
    Article getArticleById(Integer articleId);
    Article getArticleByUid(String uid);
    List<Article> getArticles(Integer userId);
    List<Article> getPubArticles(Integer userId);
    Integer getArticleId(Article article);
    void addArticle(Article article);
    void addCollArticle(Integer userId,String uid);
    List<UserEntity> getCollUsers(String uid);
    Page<Article> getAll(Integer userId, Integer page, Integer size);
}
