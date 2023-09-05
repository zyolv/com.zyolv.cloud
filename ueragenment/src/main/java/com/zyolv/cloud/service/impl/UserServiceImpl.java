package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.mapper.UserMapper;
import com.zyolv.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserEntity getUserByUsername(String username) {
        // 查询用户信息
        LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserEntity::getUsername, username);
        UserEntity user = userMapper.selectOne(queryWrapper);

        return user;
    }

    @Override
    public void saveUser(UserEntity userEntity) {
        userMapper.insert(userEntity);
    }
}
