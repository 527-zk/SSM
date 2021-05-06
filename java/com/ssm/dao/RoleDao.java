package com.ssm.dao;

import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kneesh
 * @Description 角色Dao层
 * @date 2021/4/20-11:04
 */
public interface RoleDao {
    /**
     * 查询全部角色
     * @return
     */
    List<Role> queryAllRole();

    /**
     * 添加角色
     * @param role
     */
    void addRole(Role role);
    /**
     * 删除角色
     * @param id
     */
    void deleteRoleById(Integer id);

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    Role queryRoleById(@Param("id") int id);

    /**
     * 查询出角色可以添加的权限
     * @param id
     * @return
     */
    List<Permission> queryOtherPermissions(@Param("id") int id);

    /**
     * 给角色添加权限
     * @param roleId
     * @param permissionId
     */
    void addPermissionToRole(@Param("roleId") int roleId, @Param("permissionId") int permissionId);

    /**
     * 删除角色已有的权限
     * @param roleId
     * @param permissionId
     */
    void deletePermissionFromRole(@Param("roleId") int roleId, @Param("permissionId") int permissionId);
}
