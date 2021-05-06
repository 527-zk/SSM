package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.pojo.Role;
import com.ssm.pojo.UserInfo;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author kneesh
 * @Description 用户控制器
 * @date 2021/4/15-19:52
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 查询所有的用户
     * @param page
     * @param size
     * @return
     */
    @RequestMapping("/queryAllUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryAllUser(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "10") Integer size){
        ModelAndView mv = new ModelAndView();
        List<User> users = userService.queryAllUser(page,size);
        PageInfo pageInfo = new PageInfo(users);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");
        return mv;
    }

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    @RequestMapping("/addUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addUser(UserInfo userInfo){
        //插入前判断是否存在同名用户
        UserInfo info = userService.queryUserByName(userInfo.getUsername());
        if (info!=null && info.getUsername().equals(userInfo.getUsername())){
            return "redirect:queryAllUser.do";
        }
        userService.addUser(userInfo);
        return "redirect:queryAllUser.do";
    }
    @RequestMapping("/deleteUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteUser(Integer id){
        userService.deleteUser(id);
        return "redirect:queryAllUser.do";
    }
    /**
     * 通过Id查询用户
     * @param id
     * @return
     */
    @RequestMapping("/queryUserById.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryUserById(Integer id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.queryUserById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    /**
     * 查询出用户及用户没有的角色信息
     * 以便为用户添加角色
     * @param id
     * @return
     */
    @RequestMapping("/queryUserByIdAndOtherRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryUserByIdAndOtherRole(Integer id){
        ModelAndView mv = new ModelAndView();
        // 先查询出用户的信息
        UserInfo userInfo = userService.queryUserById(id);
        // 再查询出用户没有的角色信息
        List<Role> roles = userService.queryOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-add");
        return mv;
    }

    /**
     * 给用户添加角色
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/addRoleToUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) Integer userId,@RequestParam(name = "ids",required = true) int[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:queryAllUser.do";
    }

    /**
     * 将用户从角色中删除
     * @param id
     * @return
     */
    @RequestMapping("/queryUserByIdAndSelfRole.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView queryUserByIdAndSelfRole(Integer id){
        ModelAndView mv = new ModelAndView();
        // 先查询出用户的信息
        UserInfo userInfo = userService.queryUserById(id);
        // 再获取用户已有的角色信息
        List<Role> roles = userInfo.getRoles();
        mv.addObject("user",userInfo);
        mv.addObject("roleList",roles);
        mv.setViewName("user-role-delete");
        return mv;
    }

    /**
     * 将用户从角色中删除
     * @param userId
     * @param roleIds
     * @return
     */
    @RequestMapping("/deleteRoleFromUser.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteRoleFromUser(@RequestParam(name = "userId",required = true) Integer userId,@RequestParam(name = "ids",required = true) int[] roleIds){
        userService.deleteRoleFromUser(userId,roleIds);
        return "redirect:queryAllUser.do";
    }

}
