package com.ssm.service.impl;

import com.ssm.dao.ForgetPasswordDao;
import com.ssm.service.ForgetPasswordService;
import com.ssm.util.MakeCaptcha;
import com.ssm.util.sendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 忘记密码接口的实现类
 * @author kneesh
 * @date 2021/4/27-9:53
 */
@Service
@Transactional
public class ForgetPasswordImpl implements ForgetPasswordService {
    private static String captcha;
    private static String email;
    @Autowired
    public ForgetPasswordDao forgetPasswordDao;
    @Autowired
    public BCryptPasswordEncoder encoder;

    /**
     * 验证邮箱地址是否合法
     *
     * @param address
     * @return
     */
    @Override
    public boolean verifyEmail(String address) {
        if (address==null||address.length()==0)
        {
            return false;
        }
        //正则表达式匹配
        String rule = "^[0-9A-Za-z]+@[0-9A-Za-z]+\\.[0-9A-Za-z]+$";
        Pattern p = Pattern.compile(rule);
        Matcher m = p.matcher(address);
        boolean isLegal = m.matches();
        return isLegal;
    }

    /**
     * 验证验证码是否正确
     *
     * @param capt
     * @return
     */
    @Override
    public boolean verifyCaptcha(String capt) {
        if (capt==null||capt.length()!=6){
            return false;
        }
        if (capt.equals(captcha)){
            return true;
        }
        return false;
    }

    /**
     * 发送邮件
     *
     * @return
     */
    @Override
    public void send(String address) {
        //首先验证邮箱地址是否合法
        //再验证该地址是否存在于数据库中
        if (verifyEmail(address) && queryUserByAddress(address)>0){
            captcha= MakeCaptcha.sixCaptcha();
            String title = "密码恢复邮件";
            try {
                sendEmail.sendCaptchaEmail(address,title,captcha);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        email=address;
    }

    /**
     * 根据邮箱地址查询该用户是否存在
     *
     * @param address
     * @return
     */
    @Override
    public int queryUserByAddress(String address) {
        int count = forgetPasswordDao.queryUserByAddress(address);
        return count;
    }

    /**
     * 更新密码
     *
     * @param password
     */
    @Override
    public boolean updatePassword(String captcha,String password) {
        if (verifyCaptcha(captcha) && email!=null){
            String crypt = encoder.encode(password);
            forgetPasswordDao.updatePassword(email,crypt);
            return true;
        }
        return false;
    }

}
