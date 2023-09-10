package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserArticleMapper extends BaseMapper<UserArticle> {
    @Select("select article_id from t_user_article where user_id = #{userId}")
    List<Integer> selectArticleIds(@Param("userId") Integer userId);
}
