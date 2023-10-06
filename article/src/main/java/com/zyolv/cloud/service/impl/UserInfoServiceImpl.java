package com.zyolv.cloud.service.impl;

import com.zyolv.cloud.Mapper.RoleButtonsMapper;
import com.zyolv.cloud.Mapper.RoleRoutesMapper;
import com.zyolv.cloud.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserInfoServiceImpl implements UserInfoService {


    @Autowired
    private RoleRoutesMapper roleRoutesMapper;

    @Override
    public List<String> getRoutes(Integer userId) {
        return roleRoutesMapper.selectRoutes(userId);
    }

    @Override
    public List<String> getButtons(Integer userId) {
        return roleRoutesMapper.selectButtons(userId);
    }

    @Override
    public List<String> getRoles(Integer userId) {
        return roleRoutesMapper.selectRoles(userId);
    }
}
