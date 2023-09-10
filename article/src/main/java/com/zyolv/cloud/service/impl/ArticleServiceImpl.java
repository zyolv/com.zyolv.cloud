package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyolv.cloud.Mapper.ArticleMapper;
import com.zyolv.cloud.Mapper.CollArticleMapper;

import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import com.zyolv.cloud.entity.CollArticle;
import com.zyolv.cloud.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CollArticleMapper collArticleMapper;

    @Override
    public Article getArticleById(Integer articleId) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, articleId);
        return articleMapper.selectOne(queryWrapper);
    }

    @Override
    public Article getArticleByUid(String uid) {
        return articleMapper.selectArticleByUid(uid);
    }

    @Override
    public List<Article> getArticles(Integer userId) {
        return articleMapper.selectArticles(userId);
    }

    @Override
    public List<Article> getPubArticles(Integer userId) {
        return articleMapper.selectPubArticles(userId);
    }

    @Override
    public Integer getArticleId(Article article) {
        return articleMapper.selectArticleId(article.getArticleName(),article.getContent());
    }

    @Override
    public void addArticle(Article article) {
        articleMapper.insert(article);
    }

    @Override
    public void addCollArticle(Integer userId, String uid) {
        CollArticle article = new CollArticle();
        article.setCollId(uid);
        article.setUserId(userId);
        articleMapper.addCollArticle(uid);
        collArticleMapper.insert(article);
    }

    @Override
    public List<UserEntity> getCollUsers(String uid) {
        return collArticleMapper.selectCollArticleUsers(uid);
    }

}
