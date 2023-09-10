package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.CollArticle;
import com.zyolv.cloud.entity.UserArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollArticleMapper extends BaseMapper<CollArticle> {
    @Select("select user_id from t_collection_article where coll_id = #{uid}")
    List<Integer> selectCollArticleIds(@Param("uid") String uid);
    @Select("select id,username from t_user where id in (select user_id from t_collection_article where coll_id = #{uid})")
    List<UserEntity> selectCollArticleUsers(@Param("uid") String uid);
}
