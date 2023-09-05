package com.zyolv.cloud.Controller;

import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.service.RoleUserService;
import com.zyolv.cloud.service.impl.SecurityUserDetailServiceImpl;
import com.zyolv.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    @ResponseBody
    public Object login(String userName,String password,Integer roleId){
        if (userService.getUserByUsername(userName) != null) return "用户已存在";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        userEntity.setPassword(passwordEncoder.encode(password));
        userService.saveUser(userEntity);
//        System.out.println(userService.getUserByUsername(userName));
        roleUserService.saveRoleUser(userService.getUserByUsername(userName).getId(),roleId);
        return passwordEncoder.encode("123");
    }
//    @DeleteMapping("/logout")
//    public CommonResult logout() {
//        JSONObject jsonObject = WebUtils.getJwtPayload();
//        String jti = jsonObject.getStr("jti"); // JWT唯一标识
//        long exp = jsonObject.getLong("exp"); // JWT过期时间戳
//        long currentTimeSeconds = System.currentTimeMillis() / 1000;
//        CommonResult commonResult = new CommonResult();
//        commonResult.setCode(200);
//        commonResult.setMessage("success");
//        if (exp < currentTimeSeconds) { // token已过期，无需加入黑名单
//            return commonResult;
//        }
//        redisTemplate.opsForValue().set(AuthConstants.TOKEN_BLACKLIST_PREFIX + jti, null, (exp - currentTimeSeconds), TimeUnit.SECONDS);
//        return commonResult;
//    }

}
