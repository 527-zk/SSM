package com.ssm.service;

import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;

import java.util.List;

/**
 * @author kneesh
 * @Description 角色服务接口
 * @date 2021/4/16-20:16
 */
public interface RoleService {
    /**
     * 查询全部角色
     * @return
     */
    List<Role> queryAllRoles();
    /**
     * 查询全部角色-分页查询
     * @param page
     * @param size
     * @return
     */
    List<Role> queryAllRole(int page, int size);

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
    Role queryRoleById(int id);

    /**
     * 查询出角色可以添加的权限
     * @param id
     * @return
     */
    List<Permission> queryOtherPermissions(int id);

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     */
    void addPermissionToRole(int roleId, int[] ids);

    /**
     * 删除用户已有的权限
     * @param roleId
     * @param ids
     */
    void deletePermissionFromRole(int roleId, int[] ids);
}
