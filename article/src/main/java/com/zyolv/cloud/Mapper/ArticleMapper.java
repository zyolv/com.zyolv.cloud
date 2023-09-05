package com.zyolv.cloud.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zyolv.cloud.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
