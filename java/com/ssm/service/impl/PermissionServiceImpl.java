package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.PermissionDao;
import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kneesh
 * @Description 权限服务接口的实现类
 * @date 2021/4/16-21:03
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    /**
     * 查询全部权限资源
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<Permission> queryAllPermission(int page,int size) {
        PageHelper.startPage(page,size);
        return permissionDao.queryAllPermission();
    }

    /**
     * 根据ID查询权限资源
     * @param id
     * @return
     */
    @Override
    public Permission queryPermissionById(int id) {
        return permissionDao.queryPermissionById(id);
    }

    /**
     * 添加权限资源
     * @param permission
     */
    @Override
    public void addPermission(Permission permission) {
        permissionDao.addPermission(permission);
    }

    /**
     * 根据ID查询权限资源
     * @param id
     * @return
     */
    @Override
    public void deletePermissionById(int id) {
        permissionDao.deletePermissionById(id);
    }

}
