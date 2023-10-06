package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.RoleRoutes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleButtonsMapper extends BaseMapper {
    @Select("select buttons from t_role_buttons where role_id in (select role_id from t_user_role where user_id = #{userId})")
    List<String> selectButtons(@Param("userId") Integer userId);
}
