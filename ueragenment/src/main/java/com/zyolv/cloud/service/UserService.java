package com.zyolv.cloud.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.mapper.UserMapper;
import org.springframework.stereotype.Service;



public interface UserService  {

    public UserEntity getUserByUsername(String username);

    public void saveUser(UserEntity userEntity);

}
