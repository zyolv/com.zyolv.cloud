package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zyolv.cloud.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
}
