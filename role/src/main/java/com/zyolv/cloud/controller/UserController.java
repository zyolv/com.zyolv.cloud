package com.zyolv.cloud.controller;


import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUsers")
    @ResponseBody
    @PreAuthorize("hasAuthority('User')")
    public CommonResult getCollUser(@RequestParam("username") String userName,@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {
        System.out.println(userName);
        return CommonResult.success(userService.selectUsers(userName,page,limit));
    }


    @PutMapping("/user/update")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.User.update')")
    public CommonResult userUpdate(@RequestBody UserEntity userEntity) {
        userService.updateUser(userEntity);
        return CommonResult.success("success");
    }
}

