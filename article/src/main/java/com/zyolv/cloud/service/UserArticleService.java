package com.zyolv.cloud.service;

import java.util.List;

public interface UserArticleService {
    List<Integer> getArticleIds(Integer userId);
}
