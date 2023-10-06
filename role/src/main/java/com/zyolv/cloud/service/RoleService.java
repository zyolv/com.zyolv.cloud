package com.zyolv.cloud.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zyolv.cloud.entities.UserEntity;
import com.zyolv.cloud.entity.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> selectAllRole();
    List<RoleEntity> selectOwnRole(Integer userId);
    void deleteOwnRole(Integer userId);
    void addOwnRole(Integer userId,Integer roleId);
    void addRole(RoleEntity roleEntity);
    void updateRole(RoleEntity roleEntity);
    Page<RoleEntity> selectRoles( String roleName,Integer page, Integer size);
}
