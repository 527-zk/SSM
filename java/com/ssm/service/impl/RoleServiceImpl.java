package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.RoleDao;
import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import com.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author kneesh
 * @Description 角色服务接口实现类
 * @date 2021/4/20-10:57
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    /**
     * 查询全部角色
     * @return 角色列表
     */
    @Override
    public List<Role> queryAllRoles() {
        return roleDao.queryAllRole();
    }
    /**
     * 查询所有角色--分页查询
     * @param page
     * @param size
     * @return 角色列表
     */
    @Override
    public List<Role> queryAllRole(int page, int size) {
        PageHelper.startPage(page,size);
        return roleDao.queryAllRole();
    }

    /**
     * 添加角色
     * @param role
     */
    @Override
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteRoleById(Integer id) {
        roleDao.deleteRoleById(id);
    }

    /**
     * 根据角色ID查询角色
     * @param id
     * @return
     */
    @Override
    public Role queryRoleById(int id) {
        return roleDao.queryRoleById(id);
    }

    /**
     * 查询角色不包含的权限
     * @param id
     * @return
     */
    @Override
    public List<Permission> queryOtherPermissions(int id) {
        return roleDao.queryOtherPermissions(id);
    }

    /**
     * 给角色添加权限
     * @param roleId
     * @param ids
     */
    @Override
    public void addPermissionToRole(int roleId, int[] ids) {
        for (int id : ids) {
            roleDao.addPermissionToRole(roleId,id);
        }
    }
    /**
     * 删除用户已有的权限
     * @param roleId
     * @param ids
     */
    @Override
    public void deletePermissionFromRole(int roleId, int[] ids) {
        for (int id : ids) {
            roleDao.deletePermissionFromRole(roleId,id);
        }
    }

}
