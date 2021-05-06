package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Permission;
import com.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kneesh
 * @Description 权限服务控制层
 * @date 2021/4/21-15:56
 */
@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 查询全部资源
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAllPermission.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryAllPermission(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "10") Integer size){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.queryAllPermission(page,size);
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
    /**
     * 通过id查询权限资源
     * @param id
     * @return
     */
    @RequestMapping("queryPermissionById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryPermissionById(Integer id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.queryPermissionById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }
    /**
     * 添加权限资源
     * @param permission
     * @return
     */
    @RequestMapping("/addPermission.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addPermission(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:queryAllPermission.do";
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @RequestMapping("/deletePermissionById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deletePermissionById(Integer id){
        permissionService.deletePermissionById(id);
        return "redirect:queryAllPermission.do";
    }

}
