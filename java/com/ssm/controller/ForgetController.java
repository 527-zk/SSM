package com.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.ssm.pojo.Message;
import com.ssm.service.ForgetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 忘记密码
 * 通过用户邮箱发送6位验证码，验证成功后可修改密码
 * @author kneesh
 * @date 2021/4/27-9:20
 */

@Controller
@RequestMapping("/forget")
public class ForgetController {
    @Autowired
    public ForgetPasswordService forgetPasswordService;

    @RequestMapping("/getCaptcha.do")
    public void getCaptcha(String address){
        //发送验证码到用户邮箱
        forgetPasswordService.send(address);
    }
    @ResponseBody
    @RequestMapping("/modifyPassword.do")
    public String updatePassword(String captcha, String newPassword){
        Message message = new Message();
        //更新登录密码
        boolean result = forgetPasswordService.updatePassword(captcha,newPassword);
        //构造返回消息实体
        if (result){
            message.setStatus(200);
            message.setMessage("修改成功");
        }
        else{
            message.setStatus(500);
            message.setMessage("修改失败");
        }
        //json格式字符串
        return JSON.toJSONString(message);
    }

}
