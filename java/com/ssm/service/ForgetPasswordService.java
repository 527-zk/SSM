package com.ssm.service;

/**
 * 忘记密码服务接口
 * @author kneesh
 * @date 2021/4/27-9:42
 */
public interface ForgetPasswordService {
    /**
     * 验证邮箱地址是否合法
      * @param address
     * @return
     */
    boolean verifyEmail(String address);

    /**
     * 验证验证码是否正确
     * @param captcha
     * @return
     */
    boolean verifyCaptcha(String captcha);

    /**
     * 发送邮件
     * @return
     */
    void send(String address);

    /**
     * 根据邮箱地址查询用户
     * @param address
     * @return
     */
    int queryUserByAddress(String address);

    /**
     * 更新密码
     * @param password
     * @return
     */
    boolean updatePassword(String captcha, String password);
}
