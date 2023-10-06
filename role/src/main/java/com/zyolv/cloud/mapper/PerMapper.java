package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.PermissionEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PerMapper extends BaseMapper {

    @Select("select id,pid,name,type,code,level from t_permission")
    public List<PermissionEntity> selectAll();
    @Select("select id,pid,name,type,code,level from t_permission where id in (select permission_id from t_role_permission where role_id=#{roleId})")
    public List<PermissionEntity> selectOneRole(@Param("roleId") Integer roleId);
    @Insert("insert into t_permission(code,pid,name,level,type) values (#{code},#{pid},#{name},#{level},#{type})")
    public void addPermisson(@Param("code") String code, @Param("pid") Integer pid, @Param("name") String name, @Param("level") Integer level, @Param("type") Integer type);
    @Update("update t_permission set code=#{code},name=#{name} where id = #{id}")
    public void updatePermisson(@Param("code") String code, @Param("id") Integer id, @Param("name") String name);
    @Delete("delete from t_permission where id = #{id}")
    public void deletePermisson(@Param("id") Integer id);
    @Delete("delete from t_role_permission where role_id = #{roleId}")
    public void deleteRolePer(@Param("roleId") Integer roleId);
    @Insert("insert into  t_role_permission(role_id,permission_id) values (#{roleId},#{permissionId})")
    public void addRolePer(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);
}
