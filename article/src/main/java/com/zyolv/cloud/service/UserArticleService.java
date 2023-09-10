package com.zyolv.cloud.service;

import com.zyolv.cloud.entity.UserArticle;

import java.util.List;

public interface UserArticleService {
    List<Integer> getArticleIds(Integer userId);
    void addUserArticle(UserArticle userArticle);
}
