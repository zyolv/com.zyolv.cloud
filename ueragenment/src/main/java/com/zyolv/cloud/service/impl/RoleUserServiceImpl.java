package com.zyolv.cloud.service.impl;

import com.zyolv.cloud.entity.RoleUser;
import com.zyolv.cloud.mapper.RoleUserMapper;
import com.zyolv.cloud.service.RoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    RoleUserMapper roleUserMapper;

    @Override
    public void saveRoleUser(Integer userId,Integer roleId) {
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        roleUserMapper.insert(roleUser);

    }
}
