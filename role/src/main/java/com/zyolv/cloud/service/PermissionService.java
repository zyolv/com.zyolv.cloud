package com.zyolv.cloud.service;

import com.zyolv.cloud.entity.PermissionEntity;

import java.util.List;

public interface PermissionService {
    List<PermissionEntity> getAllPer();
    List<PermissionEntity> getOneRolePer(Integer roleId);
    void addPer(PermissionEntity permissionEntity);
    void updatePer(PermissionEntity permissionEntity);
    void deletePer(Integer id);
    void deleleRolePer(Integer roleId);
    void addRolePer(Integer roleId,Integer permissionId);
}
