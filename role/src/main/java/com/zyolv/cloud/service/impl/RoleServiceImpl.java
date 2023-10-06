package com.zyolv.cloud.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.zyolv.cloud.entity.RoleEntity;
import com.zyolv.cloud.mapper.RoleMapper;
import com.zyolv.cloud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<RoleEntity> selectAllRole() {

        return roleMapper.selectList();
    }

    @Override
    public List<RoleEntity> selectOwnRole(Integer userId) {
        return roleMapper.selectOwnList(userId);
    }

    @Override
    public void deleteOwnRole(Integer userId) {
        roleMapper.deleteOwnAllRole(userId);
    }

    @Override
    public void addOwnRole(Integer userId, Integer roleId) {
        roleMapper.addOwnRole(userId,roleId);
    }

    @Override
    public void addRole(RoleEntity roleEntity) {
        roleMapper.addRole(roleEntity.getRoleName());
    }

    @Override
    public void updateRole(RoleEntity roleEntity) {
        roleMapper.updateRole(roleEntity.getRoleName(),roleEntity.getId());
    }

    @Override
    public Page<RoleEntity> selectRoles(String roleName,Integer page, Integer size) {
        QueryWrapper<RoleEntity> wrapper = new QueryWrapper<>();
        wrapper.like(roleName != "" ,"role_name",roleName);
        Page<RoleEntity> pageO = new Page<>(page,size);
        Page<RoleEntity> res =  roleMapper.selectPage(pageO,wrapper);
        return res;
    }
}
