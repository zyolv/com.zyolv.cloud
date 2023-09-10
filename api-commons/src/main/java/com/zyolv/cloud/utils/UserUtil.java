package com.zyolv.cloud.utils;


import com.zyolv.cloud.entities.UserEntity;
import org.springframework.security.core.Authentication;

public class UserUtil {
    public static UserEntity getUser(Authentication authentication) {
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal == null) {
                return null;
            } else {
                try {
                    //获取用户详细信息
                    UserEntity userInfo = (UserEntity) principal;
                    return userInfo;

                } catch (Exception var5) {
                    throw new ClassCastException("类型转换异常");
                }
            }
        }
        return null;

    }
}
