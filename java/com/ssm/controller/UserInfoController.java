package com.ssm.controller;


import com.ssm.pojo.UserInfo;
import com.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author kneesh
 * @Description 个人信息的Controller
 * @date 2021/4/16-21:03
 */
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @RequestMapping("/queryUserInfo.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP','ROLE_USER')")
    public ModelAndView queryUserInfo(){
//        通过spring-security提供的UserDetails获取当前登录的用户名
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        UserInfo userInfo = userService.queryUserByName(userName);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-info");
        return mv;
    }

    @RequestMapping("/updateUserInfo.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP','ROLE_USER')")
    public String updateUserInfo(@RequestParam(name = "email")String newEmail,
                               @RequestParam(name = "phoneNum")String newPhoneNum)
    {
        //        通过spring-security提供的UserDetails获取当前登录的用户名
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        userService.updateUserInfo(userName,newEmail,newPhoneNum);
        return "redirect:queryUserInfo.do";
    }

    @RequestMapping("/updatePassword.do")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_MANAGER','ROLE_GROUP','ROLE_USER')")
    public String updatePassword(@RequestParam(name="oldPassword")String oldPassword,
                                 @RequestParam(name="newPassword")String newPassword)
    {
        //  通过spring-security提供的UserDetails获取当前登录的用户名
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = userDetails.getUsername();
        UserInfo userInfo = userService.queryUserByName(userName);
        if(encoder.matches(oldPassword,userInfo.getPassword())){
            userService.updatePassword(userName,newPassword);
            return "redirect:queryUserInfo.do";
        }
        else{
            return "redirect:${pageContext.request.contextPath}/500.jsp";
        }
    }
}
