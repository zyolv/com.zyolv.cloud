package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entities.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
    @Update("update t_user set username=#{username},name = #{name} where id = #{id}")
    void updateUser(@Param("id") Integer id,@Param("username") String username, @Param("name")String name);
}
