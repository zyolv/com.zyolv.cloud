package com.zyolv.cloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import com.zyolv.cloud.service.ArticleService;
import com.zyolv.cloud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
//@RequestMapping("/article")

public class ArticleController {

    @Autowired
    private ArticleService articleService;



    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 老师权限
     */
    @GetMapping("/getArticle")
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult getArticle(@RequestParam("articleName") String articleName, @RequestParam("page") Integer page, @RequestParam("limit") Integer size) {



        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user = UserUtil.getUser(authentication);




        Map<String,Object> res = new HashMap<>();
        Page<Article> all = articleService.getAll(articleName, page, size);
        List<Article> records = all.getRecords();
        for (Article article:records){
            List<UserEntity> collUsers = articleService.getCollUsers(article.getId());
            List<Integer> list = new ArrayList<>();
            for (UserEntity userEntity:collUsers){
                 list.add(userEntity.getId());
            }
            article.setCollectionUsers(list);
        }
        all.setRecords(records);
        res.put("record",all);
        res.put("userId",user.getId());
        return CommonResult.success(res);
    }

    @PostMapping("/addAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult addAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);

        article.setUid(user.getId());

        article.setIsDel(0);
        if (null==article.getIsPublic()){
            article.setIsPublic(1);
        }
        article.setCollectionCount(0);
        articleService.addArticle(article);


//        UserArticle userArticle = new UserArticle();
//        userArticle.setArticleId(articleService.getArticleByUid(user.getId()+article.getArticleName()).getId());
//        userArticle.setUserId(user.getId());
//        userArticleService.addUserArticle(userArticle);

        return CommonResult.success(articleService.getArticleById(user.getId()));
    }
    @PutMapping("/updateAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult updateAritcle(@RequestBody Article article) {

//        //获取身份验证
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserEntity user = UserUtil.getUser(authentication);
//
//        Integer uid = article.getUid();
//        String newUid = user.getId()+article.getArticleName();
//        if (!uid.equals(user.getId())){
//            if (articleService.getArticleById(newUid) != null){
//                return CommonResult.fail(400,"article已存在",articleService.getArticleByUid(user.getId()+article.getArticleName()));
//            }
//        }
        String articleName = article.getArticleName();
        String content = article.getContent();

        articleService.updateArticle(article.getId(),articleName,content);



        return CommonResult.success("success");
    }


    @PostMapping("/addCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult addCollAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
//        if (redisTemplate.hasKey(article.getId())){
//            redisTemplate.delete(article.getId());
//        }
        articleService.addCollArticle(user.getId(),article.getId());
        Map<String,Object> collArticle  = new HashMap<>();
        collArticle.put("collectionCount",articleService.getArticleById(article.getId()).getCollectionCount());
        collArticle.put("collectionUser", articleService.getCollUsers(article.getId()));
//        redisTemplate.delete(article.getId());
//        redisTemplate.opsForValue().set(article.getId(),collArticle);
        return CommonResult.success(collArticle);
    }
    @PostMapping("/delCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult delCollAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
//        if (redisTemplate.hasKey(article.getId())){
//            redisTemplate.delete(article.getId());
//        }
        articleService.delCollArticle(user.getId(),article.getId());
        Map<String,Object> collArticle  = new HashMap<>();
        collArticle.put("collectionCount",articleService.getArticleById(article.getId()).getCollectionCount());
        collArticle.put("collectionUser", articleService.getCollUsers(article.getId()));
//        redisTemplate.delete(article.getId());
//        redisTemplate.opsForValue().set(article.getId(),collArticle);
        return CommonResult.success(collArticle);
    }

    @GetMapping("/getCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult getCollAritcle( Integer id) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
        Map<String,Object> res  = new HashMap<>();
//        if (redisTemplate.hasKey(id)){
//            res = (Map<String, Object>) redisTemplate.opsForValue().get(id);
//        }else {
//            res.put("collectionCount",articleService.getArticleById(id).getCollectionCount());
//            res.put("collectionUser", articleService.getCollUsers(id));
//            redisTemplate.opsForValue().set(id,res);
//        }

        res.put("collectionCount",articleService.getArticleById(id).getCollectionCount());
        res.put("collectionUser", articleService.getCollUsers(id));
        return CommonResult.success(res);
    }
}
