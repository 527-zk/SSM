package com.ssm.dao;

import com.ssm.pojo.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kneesh
 * @Description 权限服务DAO层
 * @date 2021/4/21-15:46
 */
public interface PermissionDao {
    /**
     * 查询全部资源
     * @return
     */
    List<Permission> queryAllPermission();

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
    Permission queryPermissionById(@Param("id") int id);

    /**
     * 根据权限ID删除权限
     * @param id
     */
    void deletePermissionById(@Param("id") int id);
}
