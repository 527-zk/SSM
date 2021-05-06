package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Permission;
import com.ssm.pojo.Role;
import com.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kneesh
 * @Description 角色控制器
 * @date 2021/4/20-11:09
 */
@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 查询全部角色
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAllRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryAllRole(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "10") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.queryAllRole(page, size);
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("role-list");
        return mv;
    }

    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping("/addRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRole(Role role){
        List<Role> roles = roleService.queryAllRoles();
        //判断已有角色中是否存在同名角色
        for (Role r : roles){
            if (r.getRoleName().equals(role.getRoleName())){
                return "redirect:queryAllRole.do";
            }
        }
        roleService.addRole(role);
        return "redirect:queryAllRole.do";
    }
    /**
     * 删除角色
     * @param id
     * @return
     */
    @RequestMapping("/deleteRoleById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRoleById(Integer id){
        roleService.deleteRoleById(id);
        return "redirect:queryAllRole.do";
    }

    /**
     * 根据ID查询角色
     * @param id
     * @return
     */
    @RequestMapping("/queryRoleById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryRoleById(Integer id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.queryRoleById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }

    /**
     * 查询角色并查询可以添加的权限信息
     * @param id
     * @return
     */
    @RequestMapping("/queryRoleByIdAndOtherPermission.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryRoleByIdAndOtherPermission(Integer id){
        ModelAndView mv = new ModelAndView();
        // 查询出角色
        Role role = roleService.queryRoleById(id);
        List<Permission> permissions = roleService.queryOtherPermissions(id);
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-add");
        // 查询出角色可以添加的权限
        return mv;
    }

    /**
     * 为角色添加权限
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/addPermissionToRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)Integer roleId,@RequestParam(name = "ids",required = true) int[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:queryAllRole.do";
    }
    /**
     * 查询角色并获取角色已有的权限信息
     * @param id
     * @return
     */
    @RequestMapping("/queryRoleByIdAndSelfPermission.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryRoleByIdAndSelfPermission(Integer id){
        ModelAndView mv = new ModelAndView();
        // 查询出角色
        Role role = roleService.queryRoleById(id);
        //获取角色已有的权限信息
        List<Permission> permissions = role.getPermissions();
        mv.addObject("role",role);
        mv.addObject("permissionList",permissions);
        mv.setViewName("role-permission-delete");
        return mv;
    }

    /**
     * 删除角色已有的权限
     * @param roleId
     * @param ids
     * @return
     */
    @RequestMapping("/deletePermissionFromRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePermissionFromRole(@RequestParam(name = "roleId",required = true)Integer roleId,@RequestParam(name = "ids",required = true) int[] ids){
        roleService.deletePermissionFromRole(roleId,ids);
        return "redirect:queryAllRole.do";
    }
}
