package com.ssm.service;

import com.ssm.pojo.Permission;

import java.util.List;

/**
 * @author kneesh
 * @Description 权限服务接口
 * @date 2021/4/21-15:44
 */
public interface PermissionService {
    /**
     * 查询全部资源
     * @return
     */
    List<Permission> queryAllPermission(int page, int size);

    /**
     * 添加权限资源
     * @param permission
     */
    void addPermission(Permission permission);

    /**
     * 根据权限资源ID查询资源
     * @param id
     * @return
     */
    Permission queryPermissionById(int id);

    /**
     * 根据
     * @param id
     */
    void deletePermissionById(int id);
}
