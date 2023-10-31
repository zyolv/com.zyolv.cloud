package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public List<Article> getArticleByUid(Integer uid) {
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
    public void addCollArticle(Integer userId, Integer id) {
        CollArticle article = new CollArticle();
        article.setCollId(id);
        article.setUserId(userId);
        articleMapper.addCollArticle(id);
        collArticleMapper.insert(article);
    }

    @Override
    public void delCollArticle(Integer userId, Integer uid) {
        collArticleMapper.delCollArticle(uid,userId);
        articleMapper.delCollArticle(uid);
    }

    @Override
    public List<UserEntity> getCollUsers(Integer id) {
        return collArticleMapper.selectCollArticleUsers(id);
    }

    @Override
    public Page<Article> getAll(String articleName, Integer page, Integer size) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
//        wrapper.select("a.id","a.article_name","a.content","a.uid","a.collection_count","b.user_id as collectionUsers")
//                .from("t_article a")
//                .join("t_collection_article b on a.uid=b.coll_id")
//                .eq("a.is_del",0)
//                .
        wrapper.eq("is_del",0);
        if (null != articleName){
            wrapper.like("article_name",articleName);
        }
        Page<Article> pageO = new Page<>(page,size);
        return articleMapper.selectPage(pageO,wrapper);
    }

    @Override
    public void updateArticle(Integer id, String articleName, String content) {
        articleMapper.updateArticle(id,articleName,content);
    }

    @Override
    public List<Integer> getArticleIds(Integer userId) {
        return  articleMapper.selectArticleIds(userId);
    }

}
