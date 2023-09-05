package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyolv.cloud.Mapper.ArticleMapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import com.zyolv.cloud.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public Article getArticleById(Integer articleId) {

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, articleId);
        return articleMapper.selectOne(queryWrapper);
    }
}
