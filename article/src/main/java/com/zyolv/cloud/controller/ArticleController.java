package com.zyolv.cloud.controller;

import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.Article;
import com.zyolv.cloud.service.ArticleService;
import com.zyolv.cloud.service.UserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
//@RequestMapping("/article")

public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserArticleService userArticleService;

    /**
     * 老师权限
     */
    @GetMapping("/getArticle")
    @PreAuthorize("hasAuthority('teacher')")
    public CommonResult getArticle() {

        String userId = "";
        CommonResult commonResult = new CommonResult();
        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal == null) {
                return null;
            } else {
                try {
                    //获取用户详细信息
                    UserEntity userInfo = (UserEntity) principal;
                    userId= userInfo == null ? null : String.valueOf(userInfo.getId());

                } catch (Exception var5) {
                    throw new ClassCastException("类型转换异常");
                }
                List<Integer> articleIds = userArticleService.getArticleIds(Integer.parseInt(userId));
                List<Article> res= new ArrayList<>();
                for (Integer articleId:articleIds){
                    res.add(articleService.getArticleById(articleId));
                }
                commonResult.setCode(200);
                commonResult.setMessage("success");
                commonResult.setData(res);
            }
        } 

        return commonResult;
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('teacher')")
    public Article test() {

        return articleService.getArticleById(3);
    }
}
