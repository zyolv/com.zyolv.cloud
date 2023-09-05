package com.zyolv.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zyolv.cloud.entity.PermissionEntry;
import com.zyolv.cloud.mapper.PermissionMapper;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntry> {


    public List<PermissionEntry> getPermissionsByUserId(Integer userId) {

        return baseMapper.selectPermissionsByUserId(userId);
    }

}