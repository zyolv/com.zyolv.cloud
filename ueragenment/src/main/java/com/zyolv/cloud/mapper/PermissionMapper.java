package com.zyolv.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.PermissionEntry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface PermissionMapper extends BaseMapper<PermissionEntry> {


    @Select("SELECT" +
            " id,code,description,url " +
            " FROM" +
            " t_permission " +
            " WHERE" +
            " id IN (" +
            " SELECT permission_id FROM t_role_permission WHERE role_id IN (" +
            " SELECT role_id FROM t_user_role WHERE user_id = #{userId} ) )")
    List<PermissionEntry> selectPermissionsByUserId(@Param("userId") Integer userId);


}
