package com.ssm.dao;

import org.apache.ibatis.annotations.Param;

/**
 * 忘记密码的DAO层
 * @author kneesh
 * @date 2021/4/27-10:01
 */
public interface ForgetPasswordDao {
    /**
     * 根据邮箱地址查询用户
     * @param address
     * @return
     */
    int queryUserByAddress(@Param("email") String address);

    void updatePassword(@Param("email") String address, @Param("password") String newPassword);

}
