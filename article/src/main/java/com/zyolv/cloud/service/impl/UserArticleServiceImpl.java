package com.zyolv.cloud.service.impl;

import com.zyolv.cloud.Mapper.UserArticleMapper;
import com.zyolv.cloud.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserArticleServiceImpl implements UserArticleService {
    @Autowired
    private UserArticleMapper userArticleMapper;

    @Override
    public List<Integer> getArticleIds(Integer userId) {
        return  userArticleMapper.selectArticleIds(userId);
    }
}
