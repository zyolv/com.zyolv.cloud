package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.mapper.UserMapper;
import com.zyolv.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Page<UserEntity> selectUsers(String userName, Integer page, Integer size) {
        QueryWrapper<UserEntity> wrapper = new QueryWrapper<>();
        if (null != userName && userName != ""){
            wrapper.like("username" ,userName);
        }

        Page<UserEntity> pageO = new Page<>(page,size);
        Page<UserEntity> res =  userMapper.selectPage(pageO, wrapper);
        return res;
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        userMapper.updateUser(userEntity.getId(),userEntity.getUsername(),userEntity.getName());
    }
}
