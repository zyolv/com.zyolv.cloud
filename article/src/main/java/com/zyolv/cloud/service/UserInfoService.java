package com.zyolv.cloud.service;

import java.util.List;

public interface UserInfoService {
    List<String> getRoutes(Integer userId);
    List<String> getButtons(Integer userId);
    List<String> getRoles(Integer userId);
}
