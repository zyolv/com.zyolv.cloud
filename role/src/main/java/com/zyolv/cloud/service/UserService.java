package com.zyolv.cloud.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.UserEntity;

import java.util.Map;

public interface UserService {
    Page<UserEntity> selectUsers(String userName, Integer page, Integer size);
    void updateUser(UserEntity userEntity);
}
