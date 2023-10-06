package com.zyolv.cloud.controller;


import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.UserInfo;
import com.zyolv.cloud.service.UserInfoService;
import com.zyolv.cloud.utils.UserUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/getCollUser")
    @ResponseBody
    @PreAuthorize("hasAuthority('admin')")
    public CommonResult getCollUser() {

        //获取身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object[] authorities = authentication.getAuthorities().toArray();
//        List<String> authorityList = new ArrayList<>();
//        for (Object o: authorities){
//            authorityList.add(o.toString());
//        };
        UserEntity user = UserUtil.getUser(authentication);
        UserInfo userInfo= new UserInfo();
        userInfo.setName(user.getUsername());
        userInfo.setAvatar("");
        userInfo.setRoles(userInfoService.getRoles(user.getId()));
        userInfo.setRoutes(userInfoService.getRoutes(user.getId()));
        userInfo.setButtons(userInfoService.getButtons(user.getId()));
        return CommonResult.success(userInfo);
    }

}
