package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.RoleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<RoleEntity> {
    @Select("select id,create_time, update_time, role_name,remark from t_role")
    List<RoleEntity> selectList();

    @Select("select id,create_time, update_time, role_name,remark from t_role where id in (select role_id from t_user_role where user_id= #{userId})")
    List<RoleEntity> selectOwnList(@Param("userId") Integer userId);

    @Delete("DELETE FROM t_user_role where user_id= #{userId}")
    void deleteOwnAllRole(@Param("userId") Integer userId);

    @Insert("insert into t_user_role (user_id,role_id) values (#{userId},#{roleId})")
    void addOwnRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);

    @Insert("insert into t_role (role_name,status) values (#{roleName},1)")
    void addRole(@Param("roleName") String roleName);

    @Insert("update  t_role set role_name =#{roleName} where id = #{id}")
    void updateRole(@Param("roleName") String roleName,@Param("id") Integer id);
}
