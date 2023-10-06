package com.zyolv.cloud.controller;

import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entity.PermissionEntity;
import com.zyolv.cloud.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @GetMapping("/getPermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('Permission')")
    public CommonResult getPermission() {

        List<PermissionEntity> list = permissionService.getAllPer();
        List<PermissionEntity> res = new ArrayList<>();
        for (PermissionEntity p:list){
            if (p.getLevel() == 3){
                List<PermissionEntity> list1 = new ArrayList<>();
                for (PermissionEntity p1:list){
                    if(p1.getPid()==p.getId()){
                        list1.add(p1);
                    }
                }
                p.setChildren(list1);
            }
        }
        for (PermissionEntity p:list){
            if (p.getLevel() == 2){
                List<PermissionEntity> list2 = new ArrayList<>();
                for (PermissionEntity p1:list){

                    if(p1.getPid()==p.getId()){
                        list2.add(p1);
                    }
                }
                p.setChildren(list2);
            }
        }
        for (PermissionEntity p:list){
            if (p.getLevel() == 1){
                List<PermissionEntity> list3 = new ArrayList<>();
                for (PermissionEntity p1:list){
                    if(p1.getPid()==p.getId()){
                        list3.add(p1);
                    }
                }
                p.setChildren(list3);
                res.add(p);
            }
        }
        return CommonResult.success(res);
    }

    @GetMapping("/getOnrRolePermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('Permission')")
    public CommonResult getOnrRolePermission(Integer roleId) {

        List<PermissionEntity> listAll = permissionService.getAllPer();
        List<PermissionEntity> list = permissionService.getOneRolePer(roleId);
        List<PermissionEntity> res = new ArrayList<>();

        for (PermissionEntity p:listAll){
            for (PermissionEntity p1:list){
                if(p.equals(p1)){
                    p.setSelect(true);
                }
            }
            if (p.getLevel() == 3){
                List<PermissionEntity> list1 = new ArrayList<>();
                for (PermissionEntity p1:listAll){
                    if(p1.getPid()==p.getId()){
                        list1.add(p1);
                    }
                }
                p.setChildren(list1);
            }
        }
        for (PermissionEntity p:listAll){
            if (p.getLevel() == 2){
                List<PermissionEntity> list2 = new ArrayList<>();
                for (PermissionEntity p1:listAll){

                    if(p1.getPid()==p.getId()){
                        list2.add(p1);
                    }
                }
                p.setChildren(list2);
            }
        }
        for (PermissionEntity p:listAll){
            if (p.getLevel() == 1){
                List<PermissionEntity> list3 = new ArrayList<>();
                for (PermissionEntity p1:listAll){
                    if(p1.getPid()==p.getId()){
                        list3.add(p1);
                    }
                }
                p.setChildren(list3);
                res.add(p);
            }
        }
        return CommonResult.success(res);
    }

    @PostMapping("/addPermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Permission.add')")
    public CommonResult addPermission(@RequestBody PermissionEntity permissionEntity) {
        if (permissionEntity.getLevel() !=4){
            permissionEntity.setType(1);
        }else {
            permissionEntity.setType(2);
        }
        permissionService.addPer(permissionEntity);
        return CommonResult.success("success");
    }
    @PutMapping("/updatePermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Permission.update')")
    public CommonResult updatePermission(@RequestBody PermissionEntity permissionEntity) {

        permissionService.updatePer(permissionEntity);
        return CommonResult.success("success");
    }
    @DeleteMapping("/deletePermission")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Permission.remove')")
    public CommonResult deletePermission (Integer id) {

        permissionService.deletePer(id);
        return CommonResult.success("success");
    }


}
