package com.zyolv.cloud.Controller;

import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.service.RoleUserService;
import com.zyolv.cloud.service.impl.SecurityUserDetailServiceImpl;
import com.zyolv.cloud.service.UserService;
import com.zyolv.cloud.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private SecurityUserDetailServiceImpl securityUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    RoleUserService roleUserService;

    @PostMapping("/add")
    @ResponseBody
    public CommonResult addUser(@RequestBody UserEntity userEntity){
        if (userService.getUserByUsername(userEntity.getUsername()) != null) return CommonResult.fail(400,"fail","用户已存在");

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userService.saveUser(userEntity);
//        System.out.println(userService.getUserByUsername(userName));
        //roleUserService.saveRoleUser(userService.getUserByUsername(userName).getId(),roleId);
        return CommonResult.success("success");
    }
    @PostMapping("/test")
    @ResponseBody
    public CommonResult test(){

        return CommonResult.success("test");
    }


}
