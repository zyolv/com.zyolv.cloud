package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.RoleRoutes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleRoutesMapper extends BaseMapper<RoleRoutes> {
    @Select("select code from t_permission where id in (select permission_id from t_role_permission where role_id in" +
            "(select role_id from t_user_role where user_id = #{userId}) )" +
            "and level in (2,3)")
    List<String> selectRoutes(@Param("userId") Integer userId);
    @Select("select code from t_permission where id in (select permission_id from t_role_permission where role_id in" +
            "(select role_id from t_user_role where user_id = #{userId}) )" +
            "and level=4")
    List<String> selectButtons(@Param("userId") Integer userId);
    @Select("select role_name from t_role where id in (select role_id from t_user_role where user_id = #{userId})")
    List<String> selectRoles(@Param("userId") Integer userId);
}
