package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.CollArticle;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollArticleMapper extends BaseMapper<CollArticle> {
    @Select("select user_id from t_collection_article where coll_id = #{id}")
    List<Integer> selectCollArticleIds(@Param("id") Integer id);
    @Select("select id,username from t_user where id in (select user_id from t_collection_article where coll_id = #{id})")
    List<UserEntity> selectCollArticleUsers(@Param("id") Integer id);
    @Delete("DELETE FROM t_collection_article where coll_id = #{id} and user_id=#{userId}")
    void delCollArticle(@Param("id")Integer id,@Param("userId")Integer userId);
}
