package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {
}
