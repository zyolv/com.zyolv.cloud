package com.zyolv.cloud.controller;


import com.zyolv.cloud.entities.CommonResult;
import com.zyolv.cloud.entity.PermissionEntity;
import com.zyolv.cloud.entity.RoleEntity;
import com.zyolv.cloud.service.PermissionService;
import com.zyolv.cloud.service.RoleService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @GetMapping("/getAssign")
    @ResponseBody
    @PreAuthorize("hasAuthority('Role')")
    public CommonResult getAssign(@RequestParam("adminId") Integer id){
        Map<String, List<RoleEntity>> map = new HashMap<>();
        map.put("assignRoles",roleService.selectOwnRole(id));
        map.put("allRolesList",roleService.selectAllRole());
        System.out.println(id);
        return CommonResult.success(map);
    }
    @PostMapping("/doAssignRole")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.User.assgin')")
    public CommonResult getAssign(@RequestBody Map<String,Object> map){
        if (null != map.get("userId") ){
            roleService.deleteOwnRole((Integer) map.get("userId"));
        }else {
            return CommonResult.fail(400,"no user","fail");
        }
        if( null != map.get("roleIdList")){
            List<Integer> list = (List<Integer>) map.get(("roleIdList"));
            for (int i : list){
                roleService.addOwnRole((Integer) map.get("userId"),i);
            }
        }
        return CommonResult.success("success");
    }

    @GetMapping("/getRoles")
    @ResponseBody
    @PreAuthorize("hasAuthority('Role')")
    public CommonResult getRoles(@RequestParam("rolename") String roleName,@RequestParam("page") Integer page, @RequestParam("limit") Integer limit) {

        return CommonResult.success(roleService.selectRoles(roleName,page,limit));
    }

    @PostMapping("/doAssign")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Role.assgin')")
    public CommonResult doAssign(@RequestBody Map<String,Object> map) {
        if (map.get("roleId") != null){
            permissionService.deleleRolePer((Integer) map.get("roleId"));
        }
        if( null != map.get("permissionId")){
            List<Integer> list = (List<Integer>) map.get(("permissionId"));
            for (int i : list){
                permissionService.addRolePer((Integer) map.get("roleId"),i);
            }
        }

        return CommonResult.success("success");
    }
    @PostMapping("/addRole")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Role.add')")
    public CommonResult addRole(@RequestBody RoleEntity roleEntity) {
        roleService.addRole(roleEntity);

        return CommonResult.success("success");
    }
    @PutMapping("/updateRole")
    @ResponseBody
    @PreAuthorize("hasAuthority('btn.Role.update')")
    public CommonResult updateRole(@RequestBody RoleEntity roleEntity) {
        roleService.updateRole(roleEntity);

        return CommonResult.success("success");
    }
}
