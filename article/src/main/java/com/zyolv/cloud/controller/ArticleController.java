package com.zyolv.cloud.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import com.zyolv.cloud.entity.UserArticle;
import com.zyolv.cloud.service.ArticleService;
import com.zyolv.cloud.service.UserArticleService;
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
    private UserArticleService userArticleService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 老师权限
     */
    @GetMapping("/getArticle")
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult getArticle(@RequestParam("userId") String userId, @RequestParam("page") Integer page, @RequestParam("limit") Integer size) {



        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserEntity user = UserUtil.getUser(authentication);

//        if (user == null) {
//            return CommonResult.fail(401, "用户信息不存在", null);
//        }
//        if (userId == null || userId.equals(user.getId())) {
//            res = articleService.getArticles(user.getId());
//        } else {
//            res = articleService.getPubArticles(userId);
//        }
        Integer i = null;
        if (!userId.equals("")){
             i = Integer.valueOf(userId);
        }

        Map<String,Object> res = new HashMap<>();
        Page<Article> all = articleService.getAll(i, page, size);
        List<Article> records = all.getRecords();
        for (Article article:records){
            List<UserEntity> collUsers = articleService.getCollUsers(article.getUid());
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
        if (articleService.getArticleByUid(user.getId()+article.getArticleName()) != null){
            return CommonResult.fail(400,"article已存在",articleService.getArticleByUid(user.getId()+article.getArticleName()));
        }
        article.setUid(user.getId()+article.getArticleName());

        article.setIsDel(0);
        if (null==article.getIsPublic()){
            article.setIsPublic(1);
        }
        article.setCollectionCount(0);
        articleService.addArticle(article);


        UserArticle userArticle = new UserArticle();
        userArticle.setArticleId(articleService.getArticleByUid(user.getId()+article.getArticleName()).getId());
        userArticle.setUserId(user.getId());
        userArticleService.addUserArticle(userArticle);

        return CommonResult.success(articleService.getArticleByUid(user.getId()+article.getArticleName()));
    }
    @PutMapping("/updateAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult updateAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);

        String uid = article.getUid();
        String newUid = user.getId()+article.getArticleName();
        if (!uid.equals(newUid)){
            if (articleService.getArticleByUid(newUid) != null){
                return CommonResult.fail(400,"article已存在",articleService.getArticleByUid(user.getId()+article.getArticleName()));
            }
        }
        String articleName = article.getArticleName();
        String content = article.getContent();

        articleService.updateArticle(uid,articleName,content,newUid);



        return CommonResult.success("success");
    }


    @PostMapping("/addCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult addCollAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
        if (redisTemplate.hasKey(article.getUid())){
            redisTemplate.delete(article.getUid());
        }
        articleService.addCollArticle(user.getId(),article.getUid());
        Map<String,Object> collArticle  = new HashMap<>();
        collArticle.put("collectionCount",articleService.getArticleByUid(article.getUid()).getCollectionCount());
        collArticle.put("collectionUser", articleService.getCollUsers(article.getUid()));
        redisTemplate.delete(article.getUid());
        redisTemplate.opsForValue().set(article.getUid(),collArticle);
        return CommonResult.success(collArticle);
    }
    @PostMapping("/delCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult delCollAritcle(@RequestBody Article article) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
        if (redisTemplate.hasKey(article.getUid())){
            redisTemplate.delete(article.getUid());
        }
        articleService.delCollArticle(user.getId(),article.getUid());
        Map<String,Object> collArticle  = new HashMap<>();
        collArticle.put("collectionCount",articleService.getArticleByUid(article.getUid()).getCollectionCount());
        collArticle.put("collectionUser", articleService.getCollUsers(article.getUid()));
        redisTemplate.delete(article.getUid());
        redisTemplate.opsForValue().set(article.getUid(),collArticle);
        return CommonResult.success(collArticle);
    }

    @GetMapping("/getCollAritcle")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult getCollAritcle( String uid) {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity user = UserUtil.getUser(authentication);
        Map<String,Object> res  = new HashMap<>();
        if (redisTemplate.hasKey(uid)){
            res = (Map<String, Object>) redisTemplate.opsForValue().get(uid);
        }else {
            res.put("collectionCount",articleService.getArticleByUid(uid).getCollectionCount());
            res.put("collectionUser", articleService.getCollUsers(uid));
            redisTemplate.opsForValue().set(uid,res);
        }

        return CommonResult.success(res);
    }
}
