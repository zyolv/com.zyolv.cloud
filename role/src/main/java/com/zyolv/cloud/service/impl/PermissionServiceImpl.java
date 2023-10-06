package com.zyolv.cloud.service.impl;

import com.zyolv.cloud.entity.PermissionEntity;
import com.zyolv.cloud.mapper.PerMapper;
import com.zyolv.cloud.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PerMapper perMapper;
    @Override
    public List<PermissionEntity> getAllPer() {
        return perMapper.selectAll();
    }

    @Override
    public List<PermissionEntity> getOneRolePer(Integer roleId) {
        return perMapper.selectOneRole(roleId);
    }

    @Override
    public void addPer(PermissionEntity permissionEntity) {
        perMapper.addPermisson(permissionEntity.getCode(),permissionEntity.getPid(),permissionEntity.getName(),permissionEntity.getLevel(),permissionEntity.getType());
    }

    @Override
    public void updatePer(PermissionEntity permissionEntity) {
        perMapper.updatePermisson(permissionEntity.getCode(),permissionEntity.getId(),permissionEntity.getName());
    }

    @Override
    public void deletePer(Integer id) {
        perMapper.deletePermisson(id);
    }

    @Override
    public void deleleRolePer(Integer roleId) {
        perMapper.deleteRolePer(roleId);
    }

    @Override
    public void addRolePer(Integer roleId, Integer permissionId) {
        perMapper.addRolePer(roleId,permissionId);
    }
}
